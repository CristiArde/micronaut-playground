package com.bonobo.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.ApplicationConfiguration;
import io.micronaut.runtime.Micronaut;
import io.micronaut.runtime.server.EmbeddedServer;
import javax.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext applicationContext = Micronaut.run(Application.class, args);
        logApplicationStartup(applicationContext);
    }

    private static void logApplicationStartup(ApplicationContext context) {
        EmbeddedServer server = context.getBean(EmbeddedServer.class);
        ApplicationConfiguration application = context.getBean(ApplicationConfiguration.class);
        String protocol = server.getScheme();
        int serverPort = server.getPort();
        String hostAddress = server.getHost();

        log.info("""
                    ----------------------------------------------------------
                    \tApplication '{}' is running! Access URLs:
                    \tLocal: \t\t\t{}://localhost:{}
                    \tExternal: \t\t{}://{}:{}
                    ----------------------------------------------------------
                """,
                application.getName().orElse(null),
                protocol,
                serverPort,
                protocol,
                hostAddress,
                serverPort
        );
    }
}
