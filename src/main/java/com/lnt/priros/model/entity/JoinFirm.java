package com.lnt.priros.model.entity;

import com.lnt.priros.model.dto.JoinFirmDto;
import com.lnt.priros.util.SecurityUtil;
import java.io.IOException;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinFirm {
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

    public static JoinFirm fromDto(JoinFirmDto param) throws IOException {
        ModelMapper modelMapper = new ModelMapper();
        JoinFirmDto hashedDto = hashProperty(param);
        JoinFirmDto encryptedDto = encrypt(hashedDto);
        return modelMapper.map(encryptedDto, JoinFirm.class);
    }

    private static JoinFirmDto encrypt(JoinFirmDto hashedDto) throws IOException {
        JoinFirmDto encryptedDto = new JoinFirmDto(hashedDto);

        byte[] securityKeyBytes = SecurityUtil.getNewSecurityKeyBytes();
        String securityKey = SecurityUtil.getNewSecurityKey(securityKeyBytes);

        encryptedDto.setMobile(SecurityUtil.encrypt(encryptedDto.getMobile(), securityKeyBytes));
        encryptedDto.setEmail(SecurityUtil.encrypt(encryptedDto.getEmail(), securityKeyBytes));
        encryptedDto.setPhone(SecurityUtil.encrypt(encryptedDto.getPhone(), securityKeyBytes));
        encryptedDto.setSecurityKey(securityKey);
        return encryptedDto;
    }

    private static JoinFirmDto hashProperty(JoinFirmDto param) {
        JoinFirmDto joinFirmDto = new JoinFirmDto(param);
        joinFirmDto.setEmailHashed(SecurityUtil.hashBySHA256(joinFirmDto.getEmail()));
        joinFirmDto.setMobileHashed(SecurityUtil.hashBySHA256(joinFirmDto.getMobile()));
        return joinFirmDto;
    }


}
