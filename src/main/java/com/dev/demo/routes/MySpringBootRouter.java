package com.dev.demo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * RedHat Openshift & Middleware Practice
 * @author José Henrique Paiva
 */
@Component
public class MySpringBootRouter extends RouteBuilder {

    @Override
    public void configure() {

        /* Timer: Envia a Mensagem para a fila */
        from("timer:hello?period=30000").routeId("timer")
                .autoStartup("{{route.timer.enabled}}")
                .transform(simple("Hello World"))
            .to("ibmMQ:queue:{{nameOfYourQueue}}");

        from("ibmMQ:queue:{{nameOfYourQueue}}").routeId("consume")
                .autoStartup("{{route.consume.enabled}}")
                .log("${body}");

    }

}
