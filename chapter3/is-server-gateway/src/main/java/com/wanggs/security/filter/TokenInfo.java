/**
 * 
 */
package com.wanggs.security.filter;

import java.util.Date;

import lombok.Data;

/**
 * 包装从认证服务器获取token信息响应对象
 */
@Data
public class TokenInfo {

	//token是否可用
	private boolean active;

	//令牌发给那个客户端应用的 客户端id
	private String client_id;

	//令牌scope
	private String[] scope;

	//用户名
	private String user_name;

	//令牌能访问哪些资源服务器，资源服务器的id
	private String[] aud;
	//令牌过期时间
	private Date exp;
	/**
	 *  用户对应的所有的权限 令牌对应的user的 权限集合 UserDetailsService里loadUserByUsername()返回的User的权限集合
	 */
	private String[] authorities;
 	
}
