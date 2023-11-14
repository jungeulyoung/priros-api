package com.lnt.priros.model.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JoinFirmFile {
    private long id;
    private long joinFirmId;
    private String fileType;
    private String fileName;
    private String filePath;
    private String fileExt;
    private String securityKey;
    private LocalDateTime created;
}