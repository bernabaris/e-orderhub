package com.github.bernabaris.orderservice;

import com.github.bernabaris.orderservice.entity.OrderStatus;
import com.github.bernabaris.orderservice.model.Order;
import com.github.bernabaris.orderservice.repository.OrderRepository;
import com.github.bernabaris.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OrderServiceTests {

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;

	private Order testOrder;

	@BeforeEach
	void setUp() {
		testOrder = Order.builder()
				.userId(1L)
				.productId(101L)
				.quantity(2)
				.status(OrderStatus.PENDING)
				.orderDate(LocalDateTime.now())
				.build();
	}

	@Test
	void testCreateOrder() {
		Order savedOrder = orderService.createOrder(testOrder);

		assertThat(savedOrder).isNotNull();
		assertThat(savedOrder.getId()).isNotNull();
		assertThat(savedOrder.getQuantity()).isEqualTo(2);
	}

	@Test
	void testGetOrdersByUserId() {
		orderService.createOrder(testOrder);

		List<Order> orders = orderService.getOrdersByUserId(1L);

		assertThat(orders).isNotEmpty();
		assertThat(orders.get(0).getUserId()).isEqualTo(1L);
	}

	@Test
	void testGetAllOrders() {
		orderService.createOrder(testOrder);
		orderService.createOrder(Order.builder()
				.userId(2L)
				.productId(202L)
				.quantity(1)
				.status(OrderStatus.PENDING)
				.orderDate(LocalDateTime.now())
				.build());

		List<Order> orders = orderService.getAllOrders();

		assertThat(orders).hasSize(2);
	}

}
