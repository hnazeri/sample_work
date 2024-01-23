package nazeri.sample.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import jakarta.validation.Valid;
import nazeri.sample.config.CustomUserDetails;
import nazeri.sample.jwt.JwtTokenUtil;

@RestController
public class AuthApi {
	@Autowired AuthenticationManager authManager;
	@Autowired JwtTokenUtil jwtUtil;
	
	@PostMapping("/auth/signin")
	public ResponseEntity<?> login(@RequestBody @Valid String req) {
		
		Gson objectMapper = new Gson();
		AuthRequest request=null;
		
		request=objectMapper.fromJson(req, AuthRequest.class);
		
		
		try {
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							request.getEmail(), request.getPassword())
			);
			
			/*User user =  (User) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getEmail(), accessToken);*/
			
			CustomUserDetails user =  (CustomUserDetails) authentication.getPrincipal();
			String accessToken = jwtUtil.generateAccessToken(user);
			AuthResponse response = new AuthResponse(user.getUsername(), accessToken);
			
			return ResponseEntity.ok().body(response);
			
		} catch (BadCredentialsException ex) {
			System.out.println(ex.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
}
