package com.vaddi.spring_graphql.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public Order createOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	public Order getUserId(Long orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(ExceptionHandler::throwResourceNotFoundException );
		return order;
	}
	
	public boolean deleteOrder(Long orderId) {
		orderRepository.findById(orderId).orElseThrow(ExceptionHandler::throwResourceNotFoundException );
		orderRepository.deleteById(orderId);
		return true;
	}
}
