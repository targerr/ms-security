package com.wanggs.security.order;

import com.wanggs.security.server.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

	/**
	 * 第一版本
	 * @param info
	 * @param username
	 * @return
	 */
//	@PostMapping
//	public OrderInfo create(@RequestBody OrderInfo info, @AuthenticationPrincipal String username) {
//		log.info("user is " + username);
//		return info;
//	}

	/**
	 * 第二版本
	 * @param info
	 * @param user
	 * @return
	 */
	@PostMapping
	public OrderInfo create(@RequestBody OrderInfo info, @AuthenticationPrincipal User user) {
		log.info("user is " + user);
		return info;
	}



	@GetMapping("/{id}")
	public OrderInfo getInfo(@PathVariable Long id) {
		log.info("orderId is " + id);
		return new OrderInfo();
	}

}
