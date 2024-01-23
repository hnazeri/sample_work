package nazeri.sample.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;


public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>> {

	@Override
	public EntityModel<Product> toModel(Product entity) {
		EntityModel<Product> productModel = EntityModel.of(entity);

		//productModel.add(linkTo(methodOn(ProductApi.class).getRoleById(entity.getId())).withSelfRel());
		//productModel.add(linkTo(methodOn(ProductApi.class).getRoleByName(entity.getName())).withSelfRel());
		/*accountModel.add(linkTo(methodOn(RoleApi.class).deposit(entity.getId(), null)).withRel("deposits"));
		accountModel.add(linkTo(methodOn(RoleApi.class).withdraw(entity.getId(), null)).withRel("withdrawls"));*/
		//productModel.add(linkTo(methodOn(ProductApi.class).getRoles()).withRel(IanaLinkRelations.COLLECTION));
		return productModel;
	}

}
