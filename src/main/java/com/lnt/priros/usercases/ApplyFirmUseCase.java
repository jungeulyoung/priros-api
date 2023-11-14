package com.lnt.priros.usercases;


import com.lnt.priros.exception.GlobalException;
import com.lnt.priros.mapper.JoinFirmMapper;
import com.lnt.priros.mapper.NiceCheckPlusMapper;
import com.lnt.priros.model.dto.JoinFirmDto;
import com.lnt.priros.model.entity.JoinFirm;
import com.lnt.priros.model.entity.JoinFirmFile;
import com.lnt.priros.model.entity.NiceCheckPlus;
import com.lnt.priros.model.response.ExpertApplyResponse;
import com.lnt.priros.resource.CipherUtils;
import com.lnt.priros.resource.ErrorCode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApplyFirmUseCase {
    private final NiceCheckPlusMapper niceCheckPlusMapper;
    private final JoinFirmMapper joinFirmMapper;
    @Value("${firm.license.file.path}")
    public String FIRM_LICENSE_FILE_PATH;

    public ExpertApplyResponse applyFirm(JoinFirmDto joinFirmDto) throws Exception {
        NiceCheckPlus niceCheckPlus = niceCheckPlusMapper.selectOne(joinFirmDto);
        if (niceCheckPlus == null) {
            throw new GlobalException(ErrorCode.USER_AUTHENTICATION_REQUIRED);
        }

        String mobileHashed = CipherUtils.hash(joinFirmDto.getMobile().getBytes());
        if (!StringUtils.equals(mobileHashed, niceCheckPlus.getMobileHashed())) {
            throw new GlobalException(ErrorCode.USER_AUTHENTICATION_REQUIRED);
        }

        if (!StringUtils.equals(joinFirmDto.getCharge(), niceCheckPlus.getAppName())) {
            throw new GlobalException(ErrorCode.USER_AUTHENTICATION_REQUIRED);
        }

        if (!StringUtils.equals(joinFirmDto.getJoinAgree(), "Y")) {
            throw new GlobalException(ErrorCode.USER_TERM_AGREE_REQUIRED);
        }

        if (joinFirmDto.getInsureFile() == null || joinFirmDto.getLicenseFile() == null || joinFirmDto.getTaxFile() == null) {
            throw new GlobalException(ErrorCode.FILE_IS_NONE);
        }

        JoinFirm oldJoinFirm = joinFirmMapper.selectByMobileHashed(mobileHashed);
        if (oldJoinFirm != null && !StringUtils.equals(joinFirmDto.getFirmKind(), "20")) { // 금융기관은 휴대폰 중복 검사 제외
            throw new GlobalException(ErrorCode.JOIN_FIRM_STATE_COMPLETE);
        }

        JoinFirm joinFirm = JoinFirm.fromDto(joinFirmDto);
        joinFirmMapper.insertOne(joinFirm);

        List<JoinFirmFile> joinFirmFileList = JoinFirmDto.getFileList(joinFirmDto);











        return null;
    }
}
