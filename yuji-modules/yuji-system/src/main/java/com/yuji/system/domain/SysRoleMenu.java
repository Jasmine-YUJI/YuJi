package com.yuji.system.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色和菜单关联 sys_role_menu
 * 
 * @author Liguoqiang
 */
@Getter
@Setter
public class SysRoleMenu
{
    /** 角色ID */
    private Long roleId;
    
    /** 菜单ID */
    private Long menuId;

}
