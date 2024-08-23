package com.chocolatestudios.ahorrapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AhorrAppApplication {

  public static void main(String[] args) {
    SpringApplication.run(AhorrAppApplication.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void afterStartup() {
    // String version = org.hibernate.Version.getVersionString();
    // System.out.println("--------------------------\n\nVersión de Hibernate: " + version);
  }
}
