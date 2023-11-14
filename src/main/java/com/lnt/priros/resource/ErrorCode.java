package com.lnt.priros.resource;

public enum ErrorCode {

	USER_NOT_FOUND(403, "A001", "아이디 또는 비밀번호가 일치하지 않습니다."),
	USER_NOT_APPROVE(403, "A002", "가입승인처리 후 사용 가능합니다."),
	USER_EXPIRED(403, "A003", "휴면계정입니다."),
	USER_NOT_USEABLE(403, "A004", "이용 정지된 아이디입니다."),
	USER_DROP_OUT(403, "A005", "탈퇴한 아이디입니다."),
	PASSWORD_NOT_MATCH(403, "A006", "기존 비밀번호가 일치하지 않습니다."),
	PASSWORD_EXPIRED(403, "A007", "비밀번호를 변경하여야 합니다."),	

	USER_AUTHENTICATION_EXCEPT(401, "A008", "사용권한 인증 처리에 실패했습니다."),
	USER_AUTHENTICATION_EXPIRED(401, "A009", "인증 기간이 만료되었습니다."),
	USER_ACCESS_TOKEN_EXPIRED(401, "A010", "인증기간이 만료된 토큰입니다."),
	USER_ACCESS_TOKEN_ENABLED(401, "A011", "실효처리된 토큰입니다."),
	USER_AUTHENTICATION_ROLE(401, "A012", "이용 권한이 없습니다."),
	REGIST_EXIST_USER_ID(403, "A012", "아이디가 이미 존재합니다."),
	REGIST_EXIST_MOBILE(403, "A013", "이미 존재하는 휴대전화입니다."),
	USER_AUTHENTICATION_REQUIRED(401, "A014", "본인인증 정보가 없습니다. 휴대폰 본인인증을 진행하세요."),
	USER_TERM_AGREE_REQUIRED(401, "A015", "필수 약관에 동의가 필요합니다."),
	JOIN_FIRM_STATE_COMPLETE(401, "A016", "등록하신 휴대전화로 이미 가입완료되었습니다."),


	NONE_MOBILE(403, "A014", "휴대전화는 필수입니다."),
	NONE_PASSWORD(403, "A015", "비밀번호는 필수입니다."),
	NONE_USER_ID(403, "A016", "아이디는 필수입니다."),
	NONE_USER_NAME(403, "A017", "사용자 이름은 필수입니다."),
	USER_IN_VALID(403, "A018", "사용자 정보가 일치하지 않습니다."),
	USER_IS_NONE(403, "A019", "일치하는 사용자가 없습니다."),
	USER_IN_VALID_GROUP(403, "A020", "권리분석 사용자가 아님니다."),
	
	METHOD_NOT_ALLOWED(405, "S002", "잘못된 요청입니다."),

	SERVICE_ERROR(418, "S001", "서비스 오류입니다."),
	DATA_IS_NONE(418, "S002", "요청 데이타가 존재하지 않습니다."),
	DATA_IS_CANCEL(418, "S003", "취소된 사건입니다."),
	FILE_DATA_IS_NONE(418, "S004", "업로드할 파일의 필수 정보가 없습니다."),
	FILE_SAVE_ERROR(418, "S005", "파일 저장 중 에러가 발생했습니다."),
	FILE_DOWN_ERROR(418, "S006", "파일 다운로드 처리 중 에러 가 발생했습니다."),
	FILE_IS_NONE(418, "S007", "파일이 존재하지 않습니다."),
	APPLY_CASE_IS_EXIST(418, "S008", "이미 의뢰한 사건입니다."),
	EXTERNAL_KEY_IS_NONE(418, "S009", "법무인 사건키가 없습니다."),
	LEGAL_STAFF_IS_NONE(418, "S010", "자격자명은 필수정보입니다."),
	TODO_CART_IS_EXIST(418, "S011", "이미 바구니에 등록된 사건입니다."),
	FILE_PROFILE_IS_NONE(418, "S012", "프로필 파일이 없습니다."),
	GROUP_IS_NONE(418, "S013", "등록된 그룹이 없습니다.."),
	
	CHAT_ROOM_IS_NONE(418, "S900", "대화방이 존재하지 않습니다."),
	AVATAR_IS_NONE(418, "S901", "아바타 이미지가 존재하지 않습니다."),

	CIPHER_ENCRYPT_ERROR(500, "S801", "암호화 처리중 에러가 발생했습니다."),
	CIPHER_DECRYPT_ERROR(500, "S801", "복호화 처리중 에러가 발생했습니다."),

	DATA_IS_OTHER_USE(418, "S100", "다른 사용자가 사용 중 입니다."),

	;



	private final String code;
	private final String message;
	private int status;

	ErrorCode(final int status, final String code, final String message) {
		this.status = status;
		this.message = message;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public int getStatus() {
		return status;
	}
	
	public String toString() {
		return String.format("code:%s message:%s", code,  message);
	}    
    
}
