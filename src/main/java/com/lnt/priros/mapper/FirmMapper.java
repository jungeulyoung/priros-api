package com.lnt.priros.mapper;

import com.lnt.priros.model.entity.Firm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FirmMapper {

    Firm findByFirmCode(@Param("firmCode") String firmCode);

}
