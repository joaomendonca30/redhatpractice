package com.dev.demo.bean;

import com.ibm.mq.jakarta.jms.MQConnectionFactory;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;

@Configuration
public class JmsComponentConfig {

    @Value("${camel.springboot.name}")
    private String appName;

    @Value("${ibm.mq.host:''}")
    private String host;

    @Value("${ibm.mq.port}")
    private Integer port;

    @Value("${ibm.mq.user:''}")
    private String user;

    @Value("${ibm.mq.password:''}")
    private String password;

    @Value("${ibm.mq.queueManager}")
    private String queueMng;

    @Value("${ibm.mq.channel}")
    private String channel;

    @Autowired
    CamelContext camelContext;

    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            @Override
            public void beforeApplicationStart(CamelContext context) {

                ConnectionFactory connectionFactory = (ConnectionFactory) new MQConnectionFactory();
                try {
                    ((MQConnectionFactory) connectionFactory).setQueueManager(queueMng);
                    ((MQConnectionFactory) connectionFactory).setTransportType(1);
                    ((MQConnectionFactory) connectionFactory).setPort(port);
                    ((MQConnectionFactory) connectionFactory).setHostName(host);
                    ((MQConnectionFactory) connectionFactory).setChannel(channel);
                    ((MQConnectionFactory) connectionFactory).setAppName(appName);
                } catch (JMSException e) {
                    e.printStackTrace();
                }

                UserCredentialsConnectionFactoryAdapter connectionFactoryAdapter=new UserCredentialsConnectionFactoryAdapter();
                connectionFactoryAdapter.setTargetConnectionFactory(connectionFactory);
                connectionFactoryAdapter.setUsername(user);
                connectionFactoryAdapter.setPassword(password);

                context.addComponent("ibmMQ", JmsComponent.jmsComponentAutoAcknowledge(connectionFactoryAdapter));

                System.setProperty("hawtio.authenticationEnabled", "false");
            }

            @Override
            public void afterApplicationStart(CamelContext camelContext) {
                // Do nothing
            }
        };
    }

}
