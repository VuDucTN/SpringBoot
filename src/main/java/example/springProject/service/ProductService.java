package example.springProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Payload.ProductRequest;
import example.springProject.models.Category;
import example.springProject.models.Product;
import example.springProject.repository.CategoryRepository;
import example.springProject.repository.ProductRepository;

@Service
public class ProductService {
	 @Autowired
	 private ProductRepository productRepository;
	 
	 @Autowired
	 private CategoryRepository categoryRepository;
	 
	 public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }
	 
	 public Product getProductById(Long productId) {
	        return productRepository.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));
	    }
	 
	 public List<Product> searchProducts(String keyword) {
	        return productRepository.findByNameContainingIgnoreCase(keyword);
	    }
	 
	 public Product addProduct(ProductRequest productRequest) {
		 Category category = categoryRepository.findById(productRequest.getCategoryId())
	                .orElseThrow(() -> new RuntimeException("Category not found"));
		 
		 Product newProduct = new Product(productRequest.getName(), productRequest.getPrice(), category);
	     return productRepository.save(newProduct);
	 }
	 
	 public Product editProduct(Long productId, ProductRequest productRequest) {
	        Product existingProduct = productRepository.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));
	        
	        existingProduct.setName(productRequest.getName());
	        existingProduct.setPrice(productRequest.getPrice());

	        Category category = categoryRepository.findById(productRequest.getCategoryId())
	                .orElseThrow(() -> new RuntimeException("Category not found"));

	        existingProduct.setCategory(category);

	        return productRepository.save(existingProduct);
	    }
	 
	 public void deleteProduct(Long productId) {
	        Product existingProduct = productRepository.findById(productId)
	                .orElseThrow(() -> new RuntimeException("Product not found"));

	        productRepository.delete(existingProduct);
	    }
	 
}
