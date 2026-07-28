package com.zerox;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ZeroXApplication {

    private static final Logger log = LoggerFactory.getLogger(ZeroXApplication.class);

    public static void main(String[] args) {
        startup(args);
    }

    public static void startup(String... args) {
        long start = System.currentTimeMillis();

        SpringApplication app = new SpringApplication(ZeroXApplication.class);
        app.setApplicationStartup(new BufferingApplicationStartup(2048));
        ConfigurableApplicationContext context = app.run(args);

        long cost = System.currentTimeMillis() - start;
        String name = context.getEnvironment().getProperty("spring.application.name", "");
        String port = context.getEnvironment().getProperty("server.port", "");
        String profile = context.getEnvironment().getProperty("spring.profiles.active", "");
        String finishedAt = OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String banner = """
        
                ------------------------------------------------------------------------
                STARTUP SUCCESS
                ------------------------------------------------------------------------
                Application : {}
                Port        : {}
                Profile     : {}
                GitHub      : https://github.com/VirtualGemini/zero-x
                ------------------------------------------------------------------------
                Total time  : {} ms
                Finished at : {}
                ------------------------------------------------------------------------
                """;

        log.info(banner, name, port, profile, cost, finishedAt);
    }
}

