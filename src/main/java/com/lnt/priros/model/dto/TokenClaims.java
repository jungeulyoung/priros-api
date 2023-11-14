package com.lnt.priros.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenClaims {
	private String userId;
	private String firmCode;
	private String part; // TL 동시, LS 수임, TR 요청
	private String role;
}

