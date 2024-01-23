package nazeri.sample.config;

import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import nazeri.sample.role.Role;
import nazeri.sample.user.User;
 
public class CustomUserDetails implements UserDetails {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
     
    public CustomUserDetails(User user) {
        this.user = user;
    }
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
 
    @Override
    public String getPassword() {
        return user.getPassword();
    }
 
    @Override
    public String getUsername() {
        return user.getEmail();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public Integer getId() {
    	return user.getId();
    }
    
    public Set<Role> getRoles() {
    	return user.getRoles();
    }
     
    public String getFullName() {
        return user.getFirstName() + " " + user.getLastName();
    }
 
}
