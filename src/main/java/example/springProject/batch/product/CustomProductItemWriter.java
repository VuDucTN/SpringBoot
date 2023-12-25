package example.springProject.batch.product;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import Payload.ProductRequest;
import example.springProject.models.Category;
import example.springProject.models.Product;
import example.springProject.repository.CategoryRepository;
import example.springProject.repository.ProductRepository;
import example.springProject.service.ProductService;

@Component
public class CustomProductItemWriter implements ItemWriter<ProductRequest> {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    public CustomProductItemWriter(ProductService productService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

	@Override
	public void write(Chunk<? extends ProductRequest> chunk) throws Exception {
		for(ProductRequest product : chunk) {
			productService.addProduct(product);
		}
	}
				

}
