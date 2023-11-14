package com.lnt.priros.model.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NiceCheckPlus {
    private long id;
    private String sessionId;
    private String mobileHashed;
    private String appName;
    private String state;
    private String message;
    private String respondNumber;
    private Date created;
    private Date updated;
}
