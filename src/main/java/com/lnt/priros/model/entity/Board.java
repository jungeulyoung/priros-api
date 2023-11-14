package com.lnt.priros.model.entity;


import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private long boardId;
    private long parentBoardId;
    private String boardType;
    private String contentType;
    private String title;
    private String content;
    private String boardNoticeType;
    private String helpCenter;
    private LocalDateTime noticeStartDt;
    private LocalDateTime noticeEndDt;
    private String noticePopupFlag;
    private String adminUserFlag;
    private long boardOrder;
    private String userId;
    private long hits;
    private String useFlag;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updated;
}
