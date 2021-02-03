/**
 *
 */
package com.wanggs.security.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.stereotype.Component;

import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.RateLimitUtils;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.config.properties.RateLimitProperties.Policy;
import com.marcosbarbero.cloud.autoconfigure.zuul.ratelimit.support.DefaultRateLimitKeyGenerator;

/**
 * 自定义限流key生成规则，自定义限流规则
 */
@Component
public class MyKeyGen extends DefaultRateLimitKeyGenerator {

	public MyKeyGen(RateLimitProperties properties,
			RateLimitUtils rateLimitUtils) {
		super(properties, rateLimitUtils);
	}

	@Override
	public String key(HttpServletRequest request, Route route, Policy policy) {
        //可以从route拿出路由信息，自定义key生成规则：https://github.com/marcosbarbero/spring-cloud-zuul-ratelimit#usage
		return super.key(request, route, policy);
	}
}
