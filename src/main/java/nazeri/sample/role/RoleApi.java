package nazeri.sample.role;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleApi {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleModelAssembler assembler;
	
	@GetMapping
	@Secured("admin")
	public CollectionModel<EntityModel<Role>> getRoles(){	
		
		List<EntityModel<Role>> roles = roleService.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
		return CollectionModel.of(roles,linkTo(methodOn(RoleApi.class).getRoles()).withRel("roles"));
	}
	
	@GetMapping("/{id}")
	@Secured("admin")
	public HttpEntity<EntityModel<Role>> getRoleById(@PathVariable Integer id){
		Role role = roleService.findById(id);
		return new ResponseEntity<>(assembler.toModel(role), HttpStatus.OK);
	}
	
	/*@GetMapping("/{name}")
	@Secured("admin")
	public HttpEntity<EntityModel<Role>> getRoleByName(@PathVariable String name){
		Role role = roleService.findByName(name);
		return new ResponseEntity<>(assembler.toModel(role), HttpStatus.OK);
	}*/
	
	@PostMapping
	@Secured("admin")
	public HttpEntity<EntityModel<Role>> addRole(@RequestBody @Valid Role r) {
		Role savedRole = roleService.save(r);
		EntityModel<Role> model = assembler.toModel(savedRole);		
		return ResponseEntity.created(linkTo(methodOn(RoleApi.class).getRoleById(savedRole.getId())).toUri()).body(model);
	}
	
	@PutMapping
	@Secured("admin")
	public HttpEntity<EntityModel<Role>> updateRole(@RequestBody @Valid Role r) {
		Role savedRole = roleService.save(r);	
		return new ResponseEntity<>(assembler.toModel(savedRole), HttpStatus.OK);
	}
	
	@DeleteMapping
	@Secured("admin")
	public ResponseEntity<?> deleteRole(@RequestBody @Valid Role r) {
		roleService.delete(r);
		return ResponseEntity.noContent().build();
	}
}
