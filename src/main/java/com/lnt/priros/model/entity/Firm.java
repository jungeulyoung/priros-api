package com.lnt.priros.model.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Firm {
	private String firmCode;
 	private String mainFirmCode;
 	private String firmKind;
 	private String firmStyle;
 	private String firmName;
 	private String postNo;
 	private String sido;
 	private String gugun;
 	private String addr;
 	private String restAddr;
 	private String mobile;
 	private String mobileHashed;
 	private String phone;
 	private String fax;
 	private String delegater;
 	private String staff;
 	private String taxNo;
 	private String taxNoHashed;
 	private String email;
 	private String emailHashed;
 	private String part;
 	private String venderInsCode;
 	private String useFlag;
 	private String securityKey;
 	private LocalDateTime created;
 	private LocalDateTime updated;
 	private String licenseFilePath;
 	private String licenseFileName;
 	private String licenseFileExt;
 	private long licenseFileSize;
 	private String termsOfMemberAgree;
 	private String privacyPolicyAgree;
 	private String termsOfServiceAgree;
 	private LocalDateTime agreeTime;
 	private String securityKeyForFile;
 	private String settingTabYn;
 	private String insurancePassYn;
 	private LocalDateTime lastLoginDate;
 	private String marketVenderCode;
 	private String lat;
 	private String lng;
 	private int botYn;
 	private String expirationDate;
 	private String serviceCode;
 	private int insuranceUpdate;
 	private String introduction;
 	private String acceptBlcokYn;
 	private String acceptBlcokReason;        
}
