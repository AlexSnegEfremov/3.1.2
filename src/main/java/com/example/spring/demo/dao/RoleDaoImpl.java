package com.example.spring.demo.dao;

import com.example.spring.demo.model.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        return entityManager.createQuery("select role from  Role role where role.name =:name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Role> getListRole() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Set<Role> getSetRoleById(Long[] ids) {
        return entityManager.createQuery("select role from Role role where role.id in (:ids)", Role.class)
                .setParameter("ids", Arrays.asList(ids))
                .getResultStream()
                .collect(Collectors.toSet());
    }
}
