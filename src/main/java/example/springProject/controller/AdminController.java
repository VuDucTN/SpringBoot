package example.springProject.controller;

import java.util.List;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Payload.ProductRequest;
import example.springProject.models.Product;
import example.springProject.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("importFileProduct")
	private Job job;
	
	 @Autowired
	 private ProductService productService;
	 
	 @GetMapping("/list")
	 public ResponseEntity<List<Product>> getAllProducts() {
	      List<Product> productList = productService.getAllProducts();
	      return ResponseEntity.ok(productList);
	 }
	 
	 @GetMapping("/detail/{productId}")
	 public ResponseEntity<Product> getProductDetail(@PathVariable Long productId) {
	      Product productDetail = productService.getProductById(productId);
	      return ResponseEntity.ok(productDetail);
	 }
	 
	 @GetMapping("/search")
	 public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
	      List<Product> searchResult = productService.searchProducts(keyword);
	      return ResponseEntity.ok(searchResult);
	 }
	 
	 @PostMapping("/add")
	 @PreAuthorize("hasRole('ADMIN')")
	 public ResponseEntity<String> addProduct(@RequestBody ProductRequest productRequest) {
	    productService.addProduct(productRequest);
	    return ResponseEntity.ok("Product added successfully");
	 }
	 
	@PutMapping("/edit/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> editProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest) {
	    productService.editProduct(productId, productRequest);
	    return ResponseEntity.ok("Product updated successfully");
	}
	
	@DeleteMapping("/delete/{productId}")
	@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully");
    }
	
	@PostMapping("/importProducts")
	@PreAuthorize("hasRole('ADMIN')")
	public void importCsvToDBJob() {
		JobParameters jobParameters = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis()).toJobParameters();
		try {
			jobLauncher.run(job, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}


}
