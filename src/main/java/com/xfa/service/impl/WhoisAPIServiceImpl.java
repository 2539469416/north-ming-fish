package com.xfa.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.xfa.handler.BusinessException;
import com.xfa.response.WhoisDomainResponse;
import com.xfa.util.OkHttpUtils;
import org.springframework.stereotype.Service;

/**
 * @author 北冥有鱼
 */
@Service
public class WhoisAPIServiceImpl {
    private static final String OLD_WHOIS_KEY = "e763756cd79b49f29e0de83f2fa0b8e3";

    private static final String WHOIS_DOMAIN_URL = "https://apidatav2.chinaz.com/single/whois";

    public WhoisDomainResponse getDomainInfo(String domain) {
        String res = OkHttpUtils.builder().url(WHOIS_DOMAIN_URL)
                .addParam("key", OLD_WHOIS_KEY)
                .addParam("domain", domain)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .post(false)
                .sync();
        JSONObject jsonObject = JSON.parseObject(res);
        WhoisDomainResponse whoisDomainResponse =  convertJsonToResponse(jsonObject);
        return whoisDomainResponse;
    }

    public WhoisDomainResponse convertJsonToResponse(JSONObject jsonObject) {
        String reason = jsonObject.getString("Reason");
        if (!"成功".equals(reason)) {
            throw new BusinessException(500, "API异常");
        }
        WhoisDomainResponse whoisDomainResponse = JSON.parseObject(jsonObject.getJSONObject("Result").toJSONString(), WhoisDomainResponse.class);
        return whoisDomainResponse;
    }
}
