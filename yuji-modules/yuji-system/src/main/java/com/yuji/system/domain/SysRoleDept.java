package com.yuji.system.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色和部门关联 sys_role_dept
 * 
 * @author Liguoqiang
 */
@Getter
@Setter
public class SysRoleDept
{
    /** 角色ID */
    private Long roleId;
    
    /** 部门ID */
    private Long deptId;

}
