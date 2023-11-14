package com.lnt.priros.model.response;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lnt.priros.resource.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime timestamp;
	private int status;
	private String message;
	private String errorCode;    

	public ErrorResponse() {
		timestamp = LocalDateTime.now();
	}

	public ErrorResponse(ErrorCode errorCode) {
		this();
		this.status = errorCode.getStatus();
		this.message = errorCode.getMessage();
		this.errorCode = errorCode.getCode();
	}
}