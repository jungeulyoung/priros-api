package com.lnt.priros.mapper;

import com.lnt.priros.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findById(String userId);
    
}
