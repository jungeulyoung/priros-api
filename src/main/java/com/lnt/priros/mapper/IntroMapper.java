package com.lnt.priros.mapper;

import com.lnt.priros.model.dto.IntroDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IntroMapper {
    IntroDto selectOne();
}
