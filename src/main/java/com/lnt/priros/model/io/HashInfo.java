package com.lnt.priros.model.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HashInfo {
    private String securityKey;
    private String hash;
    private long size;
}
