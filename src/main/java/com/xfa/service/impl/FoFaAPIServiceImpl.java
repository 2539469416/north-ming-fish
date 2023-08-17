package com.xfa.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.xfa.bo.FofaBO;
import com.xfa.handler.BusinessException;
import com.xfa.response.FoFaResponse;
import com.xfa.response.WhoisDomainResponse;
import com.xfa.util.CryptoUtil;
import com.xfa.util.OkHttpUtils;
import com.xfa.util.UrlUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 北冥有鱼
 */
@Service
public class FoFaAPIServiceImpl {
    private static final String FOFA_KEY = "cde38cd31ed3f42445249a1b46735f64";

    private static final String FOFA_SEARCH_URL = "https://fofa.info/api/v1/search/all";

    private static final String FOFA_EMAIL = "z583985166@gmail.com";

    public List<FofaBO> getDomainInfo(String source, boolean isIp) {
        String base64Code = CryptoUtil.base64Encode(source);
        String res = OkHttpUtils.builder().url(FOFA_SEARCH_URL)
                .addParam("key", FOFA_KEY)
                .addParam("email", FOFA_EMAIL)
                .addParam("qbase64", base64Code)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addParam("fields", "host,ip,port,city,os,icp,cert")
                .post(false)
                .sync();
        JSONObject jsonObject = JSON.parseObject(res);
        List<FoFaResponse> foFaResponseList = convertJsonToResponse(jsonObject, isIp);
        List<FofaBO> fofaBOList = new ArrayList<>();
        Map<String, Long> countMap;
        //按照ip查询
        if (isIp) {
            countMap = foFaResponseList.stream().collect(Collectors.groupingBy(FoFaResponse::getHost, Collectors.counting()));
            countMap.forEach((domain, count) -> {
                //排除host为ipv4
                if (!UrlUtils.isIPv4(domain)) {
                    FofaBO fofaBO = new FofaBO();
                    fofaBO.setAccuracy((double) count / (double) foFaResponseList.size());
                    fofaBO.setHost(domain);
                    fofaBO.setIp(source);
                    fofaBO.setSize((long) foFaResponseList.size());
                    foFaResponseList.forEach(item -> {
                        if (StringUtils.isNoneBlank(domain) && domain.equals(item.getDomain())) {
                            if (StringUtils.isNoneBlank(item.getCity())) {
                                fofaBO.setCity(item.getCity());
                            }
                            if (StringUtils.isNoneBlank(item.getIcp())) {
                                fofaBO.setIcp(item.getIcp());
                            }
                            if (StringUtils.isNoneBlank(item.getOs())) {
                                fofaBO.setOs(item.getOs());
                            }
                            if (StringUtils.isNoneBlank(item.getCert())) {
                                fofaBO.setCert(item.getCert());
                            }
                        }
                    });
                    fofaBOList.add(fofaBO);
                }
            });
        } else {
            countMap = foFaResponseList.stream().collect(Collectors.groupingBy(FoFaResponse::getIp, Collectors.counting()));
            countMap.forEach((ip, count) -> {
                //排除host为ipv4
                FofaBO fofaBO = new FofaBO();
                fofaBO.setAccuracy((double) count / (double) foFaResponseList.size());
                fofaBO.setIp(ip);
                fofaBO.setHost(source);
                fofaBO.setSize((long) foFaResponseList.size());
                foFaResponseList.forEach(item -> {
                    if (StringUtils.isNoneBlank(ip) && ip.equals(item.getIp())) {
                        if (StringUtils.isNoneBlank(item.getCity())) {
                            fofaBO.setCity(item.getCity());
                        }
                        if (StringUtils.isNoneBlank(item.getIcp())) {
                            fofaBO.setIcp(item.getIcp());
                        }
                        if (StringUtils.isNoneBlank(item.getOs())) {
                            fofaBO.setOs(item.getOs());
                        }
                        if (StringUtils.isNoneBlank(item.getCert())) {
                            fofaBO.setCert(item.getCert());
                        }
                    }
                });
                fofaBOList.add(fofaBO);

            });
        }
        return fofaBOList;
    }

    public List<FoFaResponse> convertJsonToResponse(JSONObject jsonObject, boolean isIp) {
        String reason = jsonObject.getString("error");
        if (!"false".equals(reason)) {
            throw new BusinessException(500, "API异常");
        }
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        List<FoFaResponse> foFaResponses = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONArray innerArray = jsonArray.getJSONArray(i);
            String host = innerArray.getString(0);
            if (StringUtils.isNoneBlank(host) && host.contains(":")) {
                host = host.split(":")[0];
            }
            String ip = innerArray.getString(1);
            Integer port = innerArray.getInteger(2);
            String city = innerArray.getString(3);
            String os = innerArray.getString(4);
            String icp = innerArray.getString(5);
            String cert = innerArray.getString(6);
            FoFaResponse foFaResponse = new FoFaResponse();
            foFaResponse.setHost(host);
            foFaResponse.setIp(ip);
            foFaResponse.setPort(port);
            foFaResponse.setCity(city);
            foFaResponse.setOs(os);
            foFaResponse.setIcp(icp);
            foFaResponse.setCert(cert);
            foFaResponses.add(foFaResponse);
        }
        return foFaResponses;
    }
}
