/**
 * 
 */
package com.wanggs.security.user;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author wanggs
 *
 */
public interface UserRepository extends JpaSpecificationExecutor<User>, CrudRepository<User, Long> {

	User findByUsername(String username);

}
