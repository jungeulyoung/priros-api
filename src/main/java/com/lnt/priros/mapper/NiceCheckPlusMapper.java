package com.lnt.priros.mapper;

import com.lnt.priros.model.dto.JoinFirmDto;
import com.lnt.priros.model.entity.NiceCheckPlus;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NiceCheckPlusMapper {
    NiceCheckPlus selectOne(JoinFirmDto joinFirmDto);
}
