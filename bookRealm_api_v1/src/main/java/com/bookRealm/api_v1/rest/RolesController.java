package com.bookRealm.api_v1.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bookRealm.api_v1.entity.Roles;
import com.bookRealm.api_v1.entity.User;
import com.bookRealm.api_v1.exception.CustomException;
import com.bookRealm.api_v1.service.RolesService;

@RestController
@RequestMapping("/api/v1")
public class RolesController {
	
	private RolesService rolesService;
	
	@Autowired
	public RolesController(RolesService rolesService) {
		super();
		this.rolesService = rolesService;
	}
	
	
	@GetMapping("/roles")
	public List<Roles> getAllRoles() {
		return rolesService.finalAll();
	}
	
	@GetMapping("/roles/{id}")
	public Roles getRoleById(@PathVariable Integer id) {
		return rolesService.findById(id);
	}
	
	
	@PostMapping("/roles")
	public Roles createRole(@RequestParam String roleName) {
		return rolesService.createRole(roleName);
	}
	
	@PutMapping("/roles/{id}")
	public Roles updateRole(@PathVariable Integer id, @RequestParam String roleName)
	{
		return rolesService.updateRole(id, roleName);
	}
	
	@DeleteMapping("/roles/{id}")
	public String DeleteRoleById(@PathVariable Integer id)
	{
		if(rolesService.findById(id)==null) {
			throw new CustomException("Role with id not found="+id);
		}
		
		rolesService.deleteById(id);
		
		return "Role with id deleted="+id;
	}
	
	
	@GetMapping("users/roles/{roleName}")
	public List<User> getUserByRoleName(@PathVariable String roleName){
		
		return rolesService.findByRoleName(roleName);
	}
}
