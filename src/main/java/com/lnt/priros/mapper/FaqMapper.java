package com.lnt.priros.mapper;

import com.lnt.priros.model.dto.Paging;
import com.lnt.priros.model.entity.Board;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FaqMapper {
    List<Board> selectList(Paging paging);

    int selectTotCnt();
}
