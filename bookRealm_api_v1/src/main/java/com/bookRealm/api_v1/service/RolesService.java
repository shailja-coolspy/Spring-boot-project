package com.bookRealm.api_v1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookRealm.api_v1.dao.RoleRepository;
import com.bookRealm.api_v1.entity.Roles;
import com.bookRealm.api_v1.entity.User;
import com.bookRealm.api_v1.exception.CustomException;

@Service
public class RolesService implements RolesInt{
	
	
	private RoleRepository roleRepository;
	
	
	@Autowired
	public RolesService(RoleRepository roleRepository) {
		super();
		this.roleRepository = roleRepository;
	}

	@Override
	public List<Roles> finalAll() {
		// TODO Auto-generated method stub
		return roleRepository.findAll();
	}

	@Override
	public Roles findById(int id) {
		// TODO Auto-generated method stub
		Optional<Roles> result=roleRepository.findById(id);
		
		Roles theRoles =null;
		
		if(result.isPresent()) {
			theRoles=result.get();
		}else {
			throw new CustomException("Did not find role id-"+id);
		}
		return theRoles;	
		
	}

	@Override
	public Roles save(Roles roles) {
		// TODO Auto-generated method stub
		return roleRepository.save(roles);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
		roleRepository.deleteById(id);
		
	}

	@Override
	public Roles createRole(String roleName) {
		// TODO Auto-generated method stub
		Roles roles=new Roles();
		
		roles.setRoleName(roleName);
		
		return roleRepository.save(roles);
	}

	@Override
	public Roles updateRole(Integer id, String roleName) {
		// TODO Auto-generated method stub
		
		Roles theRoles=findById(id);
		
		theRoles.setRoleName(roleName);
		return roleRepository.save(theRoles);
	}

	@Override
	public List<User> findByRoleName(String roleName) {
		// TODO Auto-generated method stub
		Roles roles=roleRepository.findByRoleName(roleName);
		
		if(roles==null)
		{
			
			throw new CustomException("No Role with role name found="+roleName);
		}
		return roles.getUsers();
	}

}
