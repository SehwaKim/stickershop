package com.boot.stickershop.service.impl;

import com.boot.stickershop.domain.*;
import com.boot.stickershop.dto.OrderSearch;
import com.boot.stickershop.repository.BasketProductRepository;
import com.boot.stickershop.repository.OrderProductRepository;
import com.boot.stickershop.repository.OrderRepository;
import com.boot.stickershop.repository.ProductRepository;
import com.boot.stickershop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderProductRepository orderProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Order> getOrderList(OrderSearch orderSearch) {
        PageRequest pageRequest = null;

        if("RECENT".equals(orderSearch.getSort().toUpperCase())){
            //pageRequest = PageRequest.of(orderSearch.getPage() - 1, 10, new Sort(Sort.Direction.DESC, "regtime"));
            pageRequest = PageRequest.of(orderSearch.getPage() - 1, 10);
        }else if("OLD".equals(orderSearch.getSort().toUpperCase())){
            pageRequest = PageRequest.of(orderSearch.getPage() - 1, 10, new Sort(Sort.Direction.ASC, "regtime"));
        }
        return orderRepository.getOrdersByDSL(orderSearch, pageRequest);
    }

    @Override
    public Order getOrder(Long userId) {
        return orderRepository.findFirstByUserIdOrderByRegtimeDesc(userId);
    }

    @Override
    public Order insertOrder(Order order) {
        Order savedOrder = orderRepository.save(order);

        // 상품 sales 업데이트
        for(OrderProduct orderProduct : order.getOrderProducts()){
            Product product = orderProduct.getProduct();
            Integer quantity = orderProduct.getQuantity();
            Long sales = product.getSales() + quantity;
            product.setSales(sales);
            productRepository.save(product);
        }

        // 주문번호 생성
        String orderNo;
        Random random = new Random(savedOrder.getId());
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String dateStr = format.format(new Date());
        orderNo = "S" + dateStr + random.nextInt(100000);
        savedOrder.setOrderNo(orderNo);

        return orderRepository.saveAndFlush(savedOrder);
    }
}
