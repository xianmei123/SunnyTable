package com.evigel.sunnytable.dto;

import lombok.Data;

@Data
public class AccessToken {

    private String access_token;

    private Long expires_in;
}
