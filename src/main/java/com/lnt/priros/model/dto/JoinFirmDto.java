package com.lnt.priros.model.dto;


import com.lnt.priros.model.entity.JoinFirm;
import com.lnt.priros.model.entity.JoinFirmFile;
import com.lnt.priros.model.request.ExpertApplyRequest;
import com.lnt.priros.resource.Constant;
import com.lnt.priros.util.SecurityUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JoinFirmDto {
    private long id;
    private String firmType;
    private String mobile;
    private String mobileHashed;
    private String phone;
    private String email;
    private String emailHashed;
    private String charge;
    private String position;
    private String state;
    private String message;
    private String joinAgree;
    private String firmCode;
    private String securityKey;
    private Date created;
    private Date updated;
    private String firmKind;

    private MultipartFile taxFile;
    private MultipartFile licenseFile;
    private MultipartFile insureFile;
    private String fileName;
    private String filePath;
    private String fileExt;
    private String fileSecurityKey;
    private String docName;
    private String responseNumber;

    private String useFlag;
    private String sendResult;

    public JoinFirmDto(JoinFirmDto source) {
        this.firmType = source.firmType;
        this.mobile = source.mobile;
        this.mobileHashed = source.mobileHashed;
        this.phone = source.phone;
        this.email = source.email;
        this.emailHashed = source.emailHashed;
        this.charge = source.charge;
        this.position = source.position;
        this.state = source.state;
        this.message = source.message;
        this.joinAgree = source.joinAgree;
        this.firmCode = source.firmCode;
        this.securityKey = source.securityKey;
        this.created = source.created;
        this.updated = source.updated;
        this.firmKind = source.firmKind;
        this.taxFile = source.taxFile;
        this.licenseFile = source.licenseFile;
        this.insureFile = source.insureFile;
        this.fileName = source.fileName;
        this.filePath = source.filePath;
        this.fileExt = source.fileExt;
        this.fileSecurityKey = source.fileSecurityKey;
        this.docName = source.docName;
        this.responseNumber = source.responseNumber;

        this.useFlag = source.useFlag;
        this.sendResult = source.sendResult;
    }

    public static JoinFirmDto fromRequest(ExpertApplyRequest e) {
        ModelMapper modelMapper = new ModelMapper();
        JoinFirmDto joinFirmDto = modelMapper.map(e, JoinFirmDto.class);
        joinFirmDto.setState("W");

        return joinFirmDto;
    }

    public static JoinFirmDto fromEntity(JoinFirm joinFirm) throws IOException {
        byte[] securityKeyBytes = SecurityUtil.decryptSecurityKeyBytes(joinFirm.getSecurityKey());
        String mobile = SecurityUtil.decrypt(joinFirm.getMobile(), securityKeyBytes);
        String phone = SecurityUtil.decrypt(joinFirm.getPhone(), securityKeyBytes);
        String email = SecurityUtil.decrypt(joinFirm.getEmail(), securityKeyBytes);

        ModelMapper modelMapper = new ModelMapper();
        JoinFirmDto joinFirmDto = modelMapper.map(joinFirm, JoinFirmDto.class);
        joinFirmDto.setMobile(mobile);
        joinFirmDto.setPhone(phone);
        joinFirmDto.setEmail(email);

        return joinFirmDto;
    }

    public static List<JoinFirmFile> getFileList(JoinFirmDto joinFirmDto) {
        List<JoinFirmFile> result = new ArrayList<>();
        JoinFirmFile businessfile = JoinFirmFile.builder()
                .securityKey(joinFirmDto.getSecurityKey())
                .fileExt(joinFirmDto.getFileExt())
                .fileName(joinFirmDto.getFileName())
                .filePath(joinFirmDto.getFilePath())
                .fileType(Constant.JOIN_FIRM_FILE_NAME_01)
                .joinFirmId(joinFirmDto.getId())
                .build();

        JoinFirmFile expertFile = JoinFirmFile.builder()
                .securityKey(joinFirmDto.getSecurityKey())
                .fileExt(joinFirmDto.getFileExt())
                .fileName(joinFirmDto.getFileName())
                .filePath(joinFirmDto.getFilePath())
                .fileType(Constant.JOIN_FIRM_FILE_NAME_02)
                .joinFirmId(joinFirmDto.getId())
                .build();

        JoinFirmFile insuranceFile = JoinFirmFile.builder()
                .securityKey(joinFirmDto.getSecurityKey())
                .fileExt(joinFirmDto.getFileExt())
                .fileName(joinFirmDto.getFileName())
                .filePath(joinFirmDto.getFilePath())
                .fileType(Constant.JOIN_FIRM_FILE_NAME_03)
                .joinFirmId(joinFirmDto.getId())
                .build();

        result.add(businessfile);
        result.add(expertFile);
        result.add(insuranceFile);
        return result;
    }
}
