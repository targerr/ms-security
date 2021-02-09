package com.wanggs.security.order;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
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
     * 创建订单
     *
     * @param info
     * @param username
     * @return
     */

    //注解生效需在启动类配置@EnableGlobalMethodSecurity(prePostEnabled = true)
    //@PreAuthorize("#oauth2.hasScope('write')")

    @SentinelResource(value = "createOrder",blockHandler = "doOnBlock") //通过注解指定sentinel资源
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public OrderInfo create(@RequestBody OrderInfo info, @AuthenticationPrincipal String username) throws InterruptedException {
        Thread.sleep(50);
        //查询价格
        //PriceInfo price = restTemplate.getForObject("http://localhost:9080/prices/"+info.getProductId(),PriceInfo.class);
        //log.info("price is "+price.getPrice());
        return info;
    }


    //降级处理逻辑，和原方法在一个类里，参数一致，多一个BlockException，名字自定义
    public OrderInfo doOnBlock(@RequestBody OrderInfo info,@AuthenticationPrincipal String username,BlockException exception) {
        log.info("BlockException:{}",exception.getClass().getSimpleName());
        info.setId(999L);
        info.setProductId(999L);
        return info;
    }

    @GetMapping("/{id}")
    public OrderInfo getInfo(@PathVariable Long id, @AuthenticationPrincipal String username) {
        log.info("getInfo: id is " + id + " , and username is " + username);
        OrderInfo info = new OrderInfo();
        info.setId(id);
        info.setProductId(id * 10);
        return info;
    }

}
