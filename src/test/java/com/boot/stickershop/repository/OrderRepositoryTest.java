package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Order;
import com.boot.stickershop.domain.Product;
import com.boot.stickershop.dto.OrderSearch;
import com.boot.stickershop.dto.ProductSearch;
import com.boot.stickershop.service.OrderService;
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
@DataJpaTest
public class OrderRepositoryTest {
	@Autowired
	OrderRepository orderRepository;

	@Test
	public void getOrdersByDSL() throws Exception{
		OrderSearch orderSearch = new OrderSearch();
		orderSearch.setUserId(1L);
		Pageable pageable = PageRequest.of(0, 100);
        Page<Order> ordersByDSL = orderRepository.getOrdersByDSL(orderSearch, pageable);
        ordersByDSL.forEach(System.out::println);

    }

}
