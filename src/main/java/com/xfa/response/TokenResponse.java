package com.xfa.response;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author 北冥有鱼
 */
@Data
public class TokenResponse {
    @JSONField(name = "access_token")
    private String accessToken;

    @JSONField(name = "expired_time")
    private String expiredTime;
}
