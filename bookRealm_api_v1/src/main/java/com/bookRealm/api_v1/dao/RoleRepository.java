package com.bookRealm.api_v1.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.bookRealm.api_v1.entity.Roles;

public interface RoleRepository extends JpaRepository<Roles, Integer> {
	
	Roles findByRoleName(String roleName);
}
