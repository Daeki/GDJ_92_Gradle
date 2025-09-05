package com.winter.app.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="members")
@Setter
@Getter
@ToString
public class MemberVO implements UserDetails {
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		ArrayList<GrantedAuthority> list = new ArrayList<>();
		this.list.forEach(l -> {
			SimpleGrantedAuthority g = new SimpleGrantedAuthority(l.getRoleVO().getRoleName());
			list.add(g);
		});
		
		
		return list;
	}

	@Id
	private String username;
	
	private String password;
	private String name;
	private String email;
	@Temporal(TemporalType.DATE)
	private LocalDate birth;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "memberVO", cascade = CascadeType.ALL)
	private List<MemberRoleVO> list;
	
	
	

}
