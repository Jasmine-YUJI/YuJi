package com.yuji.common.core.exception;


import com.yuji.common.core.domain.R;

public interface ErrorCode {

	/**
	 * 错误信息编码，对应国际化文件key
	 */
	String value();
	
	/**
	 * 错误码
	 */
	default int code() {
		return R.FAIL;
	}
//TODO 待解决
	default GlobalException exception(Object... args) {
		return new GlobalException(this.value());
	}
}
