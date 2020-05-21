package com.mywork.practice.auth.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserDetailsService, UserService {
	
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MyUser user = namedParameterJdbcTemplate.queryForObject(getUserDetails,
				new MapSqlParameterSource("userName", username),
				(rs, rw) -> new MyUser(rs.getString("user_name"), rs.getString("password")));
		if(user == null)
			throw new UsernameNotFoundException(username + " not found");
		
		return User.withUsername(user.getUsername()).password(user.getPassword()).roles("ADMIN").build();
	}

}
