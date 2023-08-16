package com.xfa.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.xfa.response.ThreatbookIPResponse;
import com.xfa.util.OkHttpUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 北冥有鱼
 */
@Service
public class ThreatbookAPIServiceImpl {
    @Value("${ioc.threatbookKey}")
    private String threatbookKey;

    private static final String THREADBOOK_IP_URL = "https://api.threatbook.cn/v3/ip/query";


    public ThreatbookIPResponse getThreatbookIPIOC(String ip) {
        String res = OkHttpUtils.builder().url(THREADBOOK_IP_URL)
                .addParam("apikey", threatbookKey)
                .addParam("resource", ip)
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .post(false)
                .sync();
        JSONObject jsonObject = JSON.parseObject(res).getJSONObject("data").getJSONObject(ip);
        ThreatbookIPResponse threatbookIPResponse = convertJsonToClass(jsonObject);
        return threatbookIPResponse;
    }
    //todo  rdn , updateTime , sumcurdomains , ips
    public ThreatbookIPResponse convertJsonToClass(JSONObject jsonObject) {
        ThreatbookIPResponse threatbookIPResponse = JSON.parseObject(jsonObject.toJSONString(), ThreatbookIPResponse.class);
        JSONObject basic = jsonObject.getJSONObject("basic");
        JSONObject location = jsonObject.getJSONObject("basic").getJSONObject("location");
        ThreatbookIPResponse.BasicInfo basicInfo = JSON.parseObject(basic.toJSONString(), ThreatbookIPResponse.BasicInfo.class);
        ThreatbookIPResponse.LocationInfo locationInfo = JSON.parseObject(location.toJSONString(), ThreatbookIPResponse.LocationInfo.class);
        JSONArray tagClass = jsonObject.getJSONArray("tags_classes");
        JSONArray samples = jsonObject.getJSONArray("samples");
        JSONObject asn = jsonObject.getJSONObject("asn");
        String updateTime = jsonObject.getString("update_time");
        String sumCurDomains = jsonObject.getString("sum_cur_domains");
        JSONArray ports = jsonObject.getJSONArray("ports");
        JSONArray rdnList = jsonObject.getJSONArray("rdns_list");
        List<ThreatbookIPResponse.TagClass> tagClass1 = JSON.parseArray(tagClass.toJSONString(), ThreatbookIPResponse.TagClass.class);
        List<String> rdnLists = JSON.parseArray(rdnList.toJSONString(), String.class);
        List<ThreatbookIPResponse.SampleInfo> sampleInfo = JSON.parseArray(samples.toJSONString(), ThreatbookIPResponse.SampleInfo.class);
        ThreatbookIPResponse.AsnInfo asnInfo = JSON.parseObject(asn.toJSONString(), ThreatbookIPResponse.AsnInfo.class);
        List<ThreatbookIPResponse.PortInfo> portInfo = JSON.parseArray(ports.toJSONString(), ThreatbookIPResponse.PortInfo.class);
        JSONArray threatbookLab = jsonObject.getJSONObject("intelligences").getJSONArray("threatbook_lab");
        JSONArray xReward = jsonObject.getJSONObject("intelligences").getJSONArray("x_reward");
        JSONArray openSource = jsonObject.getJSONObject("intelligences").getJSONArray("open_source");
        List<ThreatbookIPResponse.ThreatbookLabInfo> threatbookLabs = JSON.parseArray(threatbookLab.toJSONString(),ThreatbookIPResponse.ThreatbookLabInfo.class);
        List<ThreatbookIPResponse.ThreatbookLabInfo> xRewards = JSON.parseArray(xReward.toJSONString(),ThreatbookIPResponse.ThreatbookLabInfo.class);
        List<ThreatbookIPResponse.ThreatbookLabInfo> openSources = JSON.parseArray(openSource.toJSONString(),ThreatbookIPResponse.ThreatbookLabInfo.class);
        ThreatbookIPResponse.Intelligences intelligences = new ThreatbookIPResponse.Intelligences();
        intelligences.setThreatbookLab(threatbookLabs);
        intelligences.setXReward(xRewards);
        intelligences.setOpenSource(openSources);
        basicInfo.setLocation(locationInfo);
        threatbookIPResponse.setIntelligences(intelligences);
        threatbookIPResponse.setRdnsList(rdnLists);
        threatbookIPResponse.setUpdateTime(updateTime);
        threatbookIPResponse.setSumCurDomains(sumCurDomains);
        threatbookIPResponse.setBasic(basicInfo);
        threatbookIPResponse.setTagsClasses(tagClass1);
        threatbookIPResponse.setSamples(sampleInfo);
        threatbookIPResponse.setPorts(portInfo);
        threatbookIPResponse.setAsn(asnInfo);
        return threatbookIPResponse;
    }

}
