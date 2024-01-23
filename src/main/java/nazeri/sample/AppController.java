package nazeri.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import nazeri.sample.user.User;
import nazeri.sample.user.UserRepository;

@Controller
public class AppController {

	 @Autowired
	    private UserRepository userRepo;
	     
	 @GetMapping("/")
	    public String viewHomePage() {
	    	
	        return "index";
	    }
	    
	    @GetMapping("/login")
	    public String viewHomePage02() {
	        return "login";
	    }
	    
	    @GetMapping("/register")
	    public String showRegistrationForm(Model model) {
	        model.addAttribute("user", new User());
	         
	        return "signup_form";
	    }
	    
	    @PostMapping("/process_register")	    
	    public String processRegister(User user) { 
	    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        String encodedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword); 
	        userRepo.save(user);  
	         
	        return "register_success";
	    }
	    
	    @GetMapping("/users")
	    public String listUsers(Model model) {
	        /*List<User> listUsers = userRepo.findAll();
	        String email=userDetails.getFullName();
	        model.addAttribute("user",email);
	        model.addAttribute("listUsers", listUsers);*/
	         
	        return "users";
	    }
	    
	    @PostMapping("/process_login")
	    public String processLogin(/*@AuthenticationPrincipal CustomUserDetails userDetails ,*/Model model) {
	    	
	        return "users";
	    }
	    
	  //@PreAuthorize("hasAnyRole('admin','user')")//prePost
	    //@RolesAllowed({"admin","user"})//jsr250
	    //@Secured("update")
	    @Secured({"admin","user"})
		@PostMapping("/dashboard")
		public String getDashboard() {
	    	
			return "dashboard";
		}
	   
}