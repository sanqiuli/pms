package com.fzy.pms.service;

import com.fzy.pms.entity.dto.RoleDto;
import com.fzy.pms.entity.security.Role;

import java.util.List;

/**
 * @program: RoleService
 * @description:
 * @author: fzy
 * @date: 2019-04-05 11:04
 **/
public interface RoleService {

    /**
     * 创建角色
     * @param role
     * @return
     */
    Role create(Role role);

    /**
     * 更新角色
     * @param role
     */
    void update(Role role);

    /**
     * 根据id 删除 菜单
     * @param id
     */
    void delete(Long id);

    /**
     * 查询全部的角色
     * @return
     */
    List<RoleDto> findAll();

}
