package com.kiwi.cn.backend.security.repository;

import com.kiwi.cn.backend.security.model.UserRole;
import com.kiwi.cn.backend.security.model.unionkey.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * 用户角色 DAO
 * </p>
 *
 */
public interface UserRoleDao extends JpaRepository<UserRole, UserRoleKey>, JpaSpecificationExecutor<UserRole> {

}
