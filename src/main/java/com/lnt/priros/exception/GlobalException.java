package com.lnt.priros.exception;

import com.lnt.priros.resource.ErrorCode;

public class GlobalException extends RuntimeException {

	private final ErrorCode errorCode;

	public GlobalException(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}        
    
}
