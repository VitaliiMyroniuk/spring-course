package beans.services;

import beans.daos.UserDAO;
import beans.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final String ROLE_PREFIX = "ROLE_";
	private static final String DELIMITER = ",";

	@Resource
	private UserDAO userDAO;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDAO.getByEmail(email);
		Set<GrantedAuthority> grantedAuthorities = getGrantedAuthorities(user);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
	}

	private Set<GrantedAuthority> getGrantedAuthorities(User user) {
		String[] roles = user.getRoles().split(DELIMITER);
		return Arrays.stream(roles)
				.map(String::trim)
				.map(role -> ROLE_PREFIX + role)
				.map(SimpleGrantedAuthority::new)
				.collect(toSet());
	}

}
