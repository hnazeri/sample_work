package nazeri.sample.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {
	
	@Autowired private UserRepository repo;
	@Autowired private PasswordEncoder passwordEncoder;
	
	public User save(User user) {
		String rawPassword = user.getPassword();
		String encodedPassword = passwordEncoder.encode(rawPassword);
		user.setPassword(encodedPassword);
		
		return repo.save(user);
	}
	
	public User update(User user) {
		User u=repo.getReferenceById(user.getId());
		return repo.save(u);
	}
	
	public void delete(User user) {		
		repo.delete(user);
	}
	
}
