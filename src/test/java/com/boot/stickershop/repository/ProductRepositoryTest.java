package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.domain.ProductFile;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.repository.ProductRepository;
import com.boot.stickershop.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest // spring boot slice test
public class ProductRepositoryTest {
	@Autowired
	ProductRepository productRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getProducts() throws Exception{

		List<Product> products = productRepository.fetchProducts();
		for(Product p : products){
			System.out.println("===============================");
			System.out.println(p.getName());
			System.out.println("--");
			System.out.println(p.getProductCategory().getName());
			System.out.println("**");
			List<ProductFile> productFiles = p.getProductFiles();
			for(ProductFile pf : productFiles){
				System.out.println(pf.getFileName());
			}
			System.out.println("===============================");
		}

	}

}
