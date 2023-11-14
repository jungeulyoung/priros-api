package com.lnt.priros.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyPageFirm extends MyPage{
    private String firmName;
    private String firmCode;
    private String profileFileName;
    private String roleCode;

}
