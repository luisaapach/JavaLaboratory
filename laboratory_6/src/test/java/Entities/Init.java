/*
 * Copyright (c) 17.2020 - Owned by lapachitei
 */

package Entities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Init {
    protected static EntityManagerFactory emf;
    protected static EntityManager em;
    @BeforeAll
    public static void init() {
        emf = Persistence.createEntityManagerFactory("custom_persistence");
        em = emf.createEntityManager();
    }
    @AfterAll
    public static void tearDown() {
        em.close();
        emf.close();
    }
}
