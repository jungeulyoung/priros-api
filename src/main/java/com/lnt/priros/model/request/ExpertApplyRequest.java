package com.lnt.priros.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpertApplyRequest {
    private String firmType;
    private String mobile;
    private String phone;
    private String email;
    private String charge;
    private String position;
    private String state;
    private String message;
    private String joinAgree;
    private String firmCode;
    private String securityKey;
    private String firmKind;

    private MultipartFile taxFile;
    private MultipartFile licenseFile;
    private MultipartFile insureFile;
    private String fileName;
    private String filePath;
    private String fileExt;
    private String fileSecurityKey;
    private String docName;
    private String respondNumber;

}
