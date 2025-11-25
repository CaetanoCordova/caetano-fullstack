package com.senac.caetanodesktop.model.DAO;

import com.senac.caetanodesktop.model.UsuarioAdm;
import jakarta.persistence.EntityManager;

public class UsuarioAdmDAO {

    private EntityManager entityManager;

    public UsuarioAdmDAO(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public void salvar(UsuarioAdm usuarioAdm){
        entityManager.getTransaction().begin();

        entityManager.persist(usuarioAdm);

        entityManager.getTransaction().commit();
    }
}
