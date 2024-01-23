package nazeri.sample.product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductApi {

	@Autowired private ProductRepository productService;
	@Autowired private ProductModelAssembler assembler;
	
	@GetMapping
	//@Secured("admin")
	//@RolesAllowed("ROLE_EDITOR")
	public CollectionModel<EntityModel<Product>> getProducts(){	
		
		List<EntityModel<Product>> products = productService.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
		return CollectionModel.of(products,linkTo(methodOn(ProductApi.class).getProducts()).withRel("products"));
	}
	
}
