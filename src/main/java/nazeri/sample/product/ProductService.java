package nazeri.sample.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
	
@Autowired ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Integer id) {
		return productRepository.findById(id).get();
	}
	
	public Product save(Product product) {	
		return productRepository.save(product);
	}
	
	public Product update(Product product) {
		Product r=productRepository.getReferenceById(product.getId());
		return productRepository.save(r);
	}
	
	public void delete(Product product) {		
		productRepository.delete(product);
	}

}
