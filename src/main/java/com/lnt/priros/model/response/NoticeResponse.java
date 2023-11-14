package com.lnt.priros.model.response;

import com.lnt.priros.model.dto.Paging;
import com.lnt.priros.model.entity.Board;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class NoticeResponse {
	private final List<Board> noticeList;
	private final Paging paging;
}
