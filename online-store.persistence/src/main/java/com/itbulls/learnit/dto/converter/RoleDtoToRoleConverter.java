package com.itbulls.learnit.dto.converter;

import com.itbulls.learnit.dto.RoleDto;

public class RoleDtoToRoleConverter {
	
	public RoleDto convertRoleNameToRoleDtoWithOnlyRoleName(String roleName) {
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleName(roleName);
		return roleDto;
	}
}
