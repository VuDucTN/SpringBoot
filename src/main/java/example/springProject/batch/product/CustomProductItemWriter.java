package example.springProject.batch.product;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import Payload.ProductRequest;
import example.springProject.repository.CategoryRepository;
import example.springProject.service.ProductService;

@Component
public class CustomProductItemWriter implements ItemWriter<ProductRequest> {

    private final ProductService productService;

    public CustomProductItemWriter(ProductService productService) {
        this.productService = productService;
    }

	@Override
	public void write(Chunk<? extends ProductRequest> chunk) throws Exception {
		for(ProductRequest product : chunk) {
			productService.addProduct(product);
		}
	}
				

}
