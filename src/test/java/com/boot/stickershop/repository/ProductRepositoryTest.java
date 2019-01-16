package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Product;
import com.boot.stickershop.dto.ProductSearch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
		assertThat(products.size()).isEqualTo(41);
	}


	@Test
	public void getProductsByDSL() throws Exception{
		ProductSearch productSearch = new ProductSearch();
		Pageable pageable = PageRequest.of(0, 100);
		Page<Product> productsByDSL = productRepository.getProductsByDSL(productSearch, pageable);
//		productsByDSL.forEach(System.out::println);
		assertThat(productsByDSL.getTotalElements()).isEqualTo(41);
	}

	@Test
	public void getMainProductsByDSL() throws Exception{
		List<Product> mainProductsByDSL = productRepository.getMainProductsByDSL();
		mainProductsByDSL.forEach(System.out::println);
	}

}
