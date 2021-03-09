package com.kiwi.cn.backend.security.repository;

import com.kiwi.cn.backend.security.model.RolePermission;
import com.kiwi.cn.backend.security.model.unionkey.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * 角色-权限 DAO
 * </p>
 *
 */
public interface RolePermissionDao extends JpaRepository<RolePermission, RolePermissionKey>, JpaSpecificationExecutor<RolePermission> {
}
