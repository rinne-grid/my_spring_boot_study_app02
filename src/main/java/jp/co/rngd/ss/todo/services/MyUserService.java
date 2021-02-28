package jp.co.rngd.ss.todo.services;

import java.util.Collections;
import java.util.Set;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.rngd.ss.todo.models.AppUserModel;
import jp.co.rngd.ss.todo.models.AppUserModelRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MyUserService implements UserDetailsService {

	private final AppUserModelRepository rep;

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		AppUserModel user = rep.findByUsername(username);
//		Set<AppUserRoles> roles;
		if(user != null) {
			
			return new User(username, user.getPassword(),
					Collections.emptySet());
		} else {
			throw new UsernameNotFoundException("User does not exist");
		}
//		if(username.equals("admin")) {
//			return new User(username,
//			"$2a$10$G5crbmvW44AKypkFIKtVeeaV7tNbPbaFT2ccBozQRzcGaQ5Yc2t.i",
//			Collections.emptySet());
//		} else {
//			throw new UsernameNotFoundException("User is not found.");
//		}
	}


}
