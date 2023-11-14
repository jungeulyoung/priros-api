package com.lnt.priros.model.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
 	private String userId;
 	private String password;
 	private String position;
 	private String userName;
 	private String mobile;
 	private String email;
 	private String phone;
 	private String profileFilePath;
 	private String profileFileName;
 	private String firmCode;
 	private String roleCode;
 	private LocalDateTime passwordUpdate;
 	private String useFlag;
 	private String emailRecvAgree;
 	private String smsRecvAgree;
 	private LocalDateTime emailRecvAgreeDate;
 	private LocalDateTime smsRecvAgreeDate;
 	private LocalDateTime created;
 	private LocalDateTime updated;
 	private String profileFileExt;
 	private long profileFileSize;
 	private String securityKeyForFile;
 	private String securityKey;
 	private String mobileHashed;
 	private String emailHashed;
 	private String privacyPolicyAgree;
 	private String termsOfServiceAgree;
 	private LocalDateTime agreeTime;
 	private String refreshToken;
 	private String quizPassYn;
 	private String inspectionStatus;
 	private String alimTalkYn;
 	private String expertLicenseFileName;
 	private String expertLicenseFileExt;
 	private long expertLicenseFileSize;
 	private String expertLicenseStatus;
 	private int passwordErrorCount;
 	private String did;
}
