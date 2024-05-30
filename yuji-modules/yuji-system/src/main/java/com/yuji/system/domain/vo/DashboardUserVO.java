package com.yuji.system.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DashboardUserVO {

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 头像
	 */
	private String avatar;

	/**
	 * 上次登录时间
	 */
	private LocalDateTime lastLoginTime;
	
	/**
	 * 上次登录IP
	 */
	private String lastLoginIp;
	
	/**
	 * 上次登录地区
	 */
	private String lastLoginAddr;
	
	/**
	 * 所属部门
	 */
	private String deptName;
}
