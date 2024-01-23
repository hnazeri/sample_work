package nazeri.sample.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserApi {

	@Autowired private UserService userService;
     
    
	
	/*@PostMapping("/register")
	public String registerUser(@RequestBody @Valid User user) {
		this.userService.save(user);
		return "register_success";
	}*/
}
