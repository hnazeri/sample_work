package nazeri.sample.role;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;


@Service
public class RoleModelAssembler implements RepresentationModelAssembler<Role, EntityModel<Role>> {

	@Override
	public EntityModel<Role> toModel(Role entity) {
		EntityModel<Role> roleModel = EntityModel.of(entity);

		roleModel.add(linkTo(methodOn(RoleApi.class).getRoleById(entity.getId())).withSelfRel());
		//roleModel.add(linkTo(methodOn(RoleApi.class).getRoleByName(entity.getName())).withSelfRel());
		/*accountModel.add(linkTo(methodOn(RoleApi.class).deposit(entity.getId(), null)).withRel("deposits"));
		accountModel.add(linkTo(methodOn(RoleApi.class).withdraw(entity.getId(), null)).withRel("withdrawls"));*/
		roleModel.add(linkTo(methodOn(RoleApi.class).getRoles()).withRel(IanaLinkRelations.COLLECTION));
		
		/*
		//You can add an arbitrary link to an entity model object like this:
		entityModel.add(Link.of("https://company.xyz", "Ref"));
		collectionModel.add(Link.of("http://company.com/api/docs", "docs"));
		collectionModel.add(Link.of("http://company.com/namhaminh", IanaLinkRelations.AUTHOR));
		*/
		
		return roleModel;
	}

}