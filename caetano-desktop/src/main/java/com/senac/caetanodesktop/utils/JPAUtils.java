package com.senac.caetanodesktop.utils;

import jakarta.persistence.EntityManager;

public class JPAUtils {
    public static EntityManager getEntityManager() {

        return new EntityManager();
    }
}