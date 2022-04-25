package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetail 타입의 오브젝트를
// 스프링 시큐리티의 고유 세션을 저장한다.
public class PrincipalDetail implements UserDetails{
	private User user; // 컴포지션

	public PrincipalDetail(User user) {
		this.user = user;
	}
	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않은지 확인
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// 계정이 잠겨있지 않은지 확인
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않은지 여부 확인
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화 여부
	@Override
	public boolean isEnabled() {
		return true;
	}
	
	// 계정이 갖고 있는 권한 목록을 return 해준다.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();

		collectors.add(()->{
			return "ROLE_"+user.getRole();
		});
		return collectors;
	}
}
