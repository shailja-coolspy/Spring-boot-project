package com.bookRealm.api_v1.service;

import java.util.List;


import com.bookRealm.api_v1.entity.Roles;
import com.bookRealm.api_v1.entity.User;

public interface RolesInt {
List<Roles> finalAll();
	
Roles findById(int id);
		
Roles save(Roles roles);

Roles createRole(String roleName);

Roles updateRole(Integer id,String roleName);


List<User> findByRoleName(String roleName);
		
		void deleteById(int id);

}
