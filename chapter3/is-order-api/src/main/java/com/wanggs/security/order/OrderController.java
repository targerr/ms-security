package com.wanggs.security.order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


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
     *
     * @param info
     * @param username
     * @return
     */
    @PostMapping
    public OrderInfo create(@RequestBody OrderInfo info, @RequestHeader String username) {
        log.info("user is " + username);
        return info;
    }


    @GetMapping("/{id}")
    public OrderInfo getInfo(@PathVariable Long id) {
        log.info("orderId is " + id);
        return new OrderInfo();
    }

}
