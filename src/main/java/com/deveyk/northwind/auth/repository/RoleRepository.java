package com.deveyk.northwind.auth.repository;

import com.deveyk.northwind.auth.model.entity.Role;
import com.deveyk.northwind.auth.model.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleType name);

}
