package com.yuji.system.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户和岗位关联 sys_user_post
 * 
 * @author Liguoqiang
 */
@Getter
@Setter
public class SysUserPost
{
    /** 用户ID */
    private Long userId;
    
    /** 岗位ID */
    private Long postId;


}
