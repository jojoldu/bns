package com.jojoldu.bns;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AdminApplication {
    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:aws.yml,"
            + "classpath:bitly.yml,"
            + "classpath:facebook.yml,"
            + "classpath:application.yml,"
            + "/app/config/dev-beginner-group-common/real-db-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(AdminApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }
}
