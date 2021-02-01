/**
 * 
 */
package com.wanggs.security.user;

import java.io.IOException;
import java.util.List;

/**
 * @author wanggs
 *
 */
public interface UserService {
	
	UserInfo create(UserInfo user) throws IOException;
	
	UserInfo update(UserInfo user);
	
	void delete(Long id);
	
	UserInfo get(Long id) throws IOException;
	
	List<UserInfo> query(String name);

	UserInfo login(UserInfo user);

}
