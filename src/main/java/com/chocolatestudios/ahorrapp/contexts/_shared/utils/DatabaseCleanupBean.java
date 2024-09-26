package com.chocolatestudios.ahorrapp.contexts._shared.utils;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManagerFactory;

@Component
public class DatabaseCleanupBean implements DisposableBean {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void destroy() throws Exception {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            System.out.println("Database connection closed.");
        }
    }
}
