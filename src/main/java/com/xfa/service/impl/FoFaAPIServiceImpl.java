package com.xfa.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.xfa.handler.BusinessException;
import com.xfa.response.FoFaResponse;
import com.xfa.response.WhoisDomainResponse;
import com.xfa.util.CryptoUtil;
import com.xfa.util.OkHttpUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 北冥有鱼
 */
@Service
public class FoFaAPIServiceImpl {
    private static final String FOFA_KEY = "cde38cd31ed3f42445249a1b46735f64";

    private static final String FOFA_SEARCH_URL = "https://fofa.info/api/v1/search/all";

    private static final String FOFA_EMAIL = "z583985166@gmail.com";

    public FoFaResponse getDomainInfo(String source) {
        String base64Code = CryptoUtil.base64Encode(source);
        String res = OkHttpUtils.builder().url(FOFA_SEARCH_URL)
                .addParam("key", FOFA_KEY)
                .addParam("email",FOFA_EMAIL)
                .addParam("qbase64", base64Code)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .post(false)
                .sync();
        JSONObject jsonObject = JSON.parseObject(res);
        FoFaResponse foFaResponse = convertJsonToResponse(jsonObject);
     return null;
    }

    public FoFaResponse convertJsonToResponse(JSONObject jsonObject) {
        String reason = jsonObject.getString("error");
        if (!"false".equals(reason)) {
            throw new BusinessException(500, "API异常");
        }
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        List<FoFaResponse> foFaResponses = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONArray innerArray = jsonArray.getJSONArray(i);
            String host = innerArray.getString(0);
            String ip = innerArray.getString(1);
            String port = innerArray.getString(2);
        }
      return null;
    }
}
