package com.pyramid.app.dao;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.pyramid.app.entity.RoleEntity;

@Repository("roleDAO")
public class RoleDAO extends ServiceDAO<RoleEntity> {

    @PersistenceContext
    EntityManager entityManger;

    private static final Log log = LogFactory.getLog(RoleDAO.class);

    public List<RoleEntity> getRoles() {
        log.info("Getting RoleEntity Instance ");
        List<RoleEntity> roleList = Collections.emptyList();
        try {
            roleList = entityManager.createQuery("select s from RoleEntity s", RoleEntity.class).getResultList();
            log.info("get successfull");
        } catch (Exception e) {
            log.error("Failed : " + e);
        }
        return roleList;
    }

}
