package com.xfa.response;

import lombok.Data;

/**
 * @author 北冥有鱼
 */
@Data
public class TokenResponse {
    private String accessToken;

    private String expiredTime;
}
