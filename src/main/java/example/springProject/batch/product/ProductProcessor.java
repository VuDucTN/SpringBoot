package example.springProject.batch.product;

import org.springframework.batch.item.ItemProcessor;

import Payload.ProductRequest;
import example.springProject.models.Product;


public class ProductProcessor implements ItemProcessor<ProductRequest, ProductRequest>{

	@Override
	public ProductRequest process(ProductRequest item) throws Exception {
		return item;
	}

}
