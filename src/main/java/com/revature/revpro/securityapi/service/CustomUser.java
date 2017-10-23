package com.revature.revpro.securityapi.service;

import java.util.Collection;
import java.util.StringJoiner;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;
import lombok.ToString;

@Data
//@ToString(exclude="authorities",callSuper=false)
public class CustomUser extends User {

	private Long id;
	private String name;
	private String email;
	private String roles;

	public CustomUser(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
			Long userId) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.id = userId;
		this.name = username;
		this.roles = getRoles(authorities);
	}

	public CustomUser(Long userId, String username, String password,
			Collection<? extends GrantedAuthority> createAuthorityList) {
		super(username, password, createAuthorityList);
		this.id = userId;
		this.name = username;
		this.roles = getRoles(createAuthorityList);
	}

	public String getRoles(Collection<? extends GrantedAuthority> createAuthorityList) {
		StringJoiner sj = new StringJoiner(",");
		for (GrantedAuthority grantedAuthority : createAuthorityList) {
			sj.add(grantedAuthority.getAuthority());
		}
		return sj.toString();

	}

	@Override
	public String toString() {
		return "CustomUser [id=" + id + ", name=" + name + ", email=" + email + ", roles=" + roles + "]";
	}

}
