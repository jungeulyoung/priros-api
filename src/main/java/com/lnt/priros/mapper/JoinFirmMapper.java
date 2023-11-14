package com.lnt.priros.mapper;


import com.lnt.priros.model.entity.JoinFirm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JoinFirmMapper {
    JoinFirm selectByMobileHashed(String mobileHashed);

    void insertOne(JoinFirm joinFirm);
}
