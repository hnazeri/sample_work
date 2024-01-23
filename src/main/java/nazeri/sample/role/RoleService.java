package nazeri.sample.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {

	@Autowired RoleRepository roleRepository;
	
	public List<Role> findAll(){
		return roleRepository.findAll();
	}
	
	public Role findById(Integer id) {
		return roleRepository.findById(id).get();
	}
	
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
	
	public Role save(Role role) {	
		return roleRepository.save(role);
	}
	
	public Role update(Role role) {
		Role r=roleRepository.getReferenceById(role.getId());
		return roleRepository.save(r);
	}
	
	public void delete(Role role) {		
		roleRepository.delete(role);
	}
}
