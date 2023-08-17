package com.xfa.service.impl;

import com.xfa.bo.FofaBO;
import com.xfa.handler.BusinessException;
import com.xfa.response.ThreatbookIPResponse;
import com.xfa.response.WhoisDomainResponse;
import com.xfa.util.UrlUtils;
import com.xfa.vo.BaseInfoVO;
import com.xfa.vo.ThreatbookVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 北冥有鱼
 */
@Service
public class IocServiceImpl {
    @Resource
    private FoFaAPIServiceImpl foFaAPIServiceImpl;

    @Resource
    private ThreatbookAPIServiceImpl threatbookAPIServiceImpl;

    @Resource
    WhoisAPIServiceImpl whoisAPIServiceImpl;

    public BaseInfoVO IocDispatch(String query) {
        BaseInfoVO baseInfoVO;
        if (UrlUtils.isIPv4(query)) {
            baseInfoVO = ipv4Dispatch(query);
        } else {
            String url = UrlUtils.getDomainFromUrl(query);
            if (url == null) {
                new BusinessException("非法查询字符");
            }
            baseInfoVO = domainDispatch(query);
        }
        return baseInfoVO;
    }

    /**
     * ipv4调度器
     *
     * @param source todo 基础查询  域名查询
     */
    public BaseInfoVO ipv4Dispatch(String source) {
        BaseInfoVO baseInfoVO = new BaseInfoVO();
        ThreatbookIPResponse threatbookIPIOC = threatbookAPIServiceImpl.getThreatbookIPIOC(source);
        ThreatbookVo threatbookVo = convertThreatbookVo(threatbookIPIOC);
        List<FofaBO> fofaBOS = domainOrIpPegging(foFaAPIServiceImpl.getDomainInfo(source, true));
        String domain = "";
        WhoisDomainResponse domainInfo = null;
        if (fofaBOS.size() != 0) {
            domain = fofaBOS.get(0).getHost();
            domainInfo = whoisAPIServiceImpl.getDomainInfo(domain);
        }
        List<String> list = new ArrayList<>();
        list.add(domain);
        baseInfoVO.setIsIp(true);
        baseInfoVO.setCity(threatbookVo.getLocation());
        baseInfoVO.setDomain(list);
        baseInfoVO.setIp(source);
        baseInfoVO.setPorts(threatbookVo.getPorts());
        baseInfoVO.setWhoisVo(domainInfo);
        List<String> labelList = new ArrayList<>();
        labelList.add(threatbookVo.getCarrier());
        baseInfoVO.setLabelList(labelList);
        baseInfoVO.setJudged("暂无更新");
        return baseInfoVO;
    }

    /**
     * 域名调度器
     *
     * @param source
     */
    public BaseInfoVO domainDispatch(String source) {
        BaseInfoVO baseInfoVO = new BaseInfoVO();
        WhoisDomainResponse domainInfo = whoisAPIServiceImpl.getDomainInfo(source);
        List<FofaBO> fofaBOS = domainOrIpPegging(foFaAPIServiceImpl.getDomainInfo(source, false));
        String ip = "";
        ThreatbookIPResponse threatbookIPIOC = null;
        if (fofaBOS.size() != 0) {
            ip = fofaBOS.get(0).getIp();
            threatbookIPIOC = threatbookAPIServiceImpl.getThreatbookIPIOC(ip);
        }
        ThreatbookVo threatbookVo = convertThreatbookVo(threatbookIPIOC);
        List<String> list = new ArrayList<>();
        list.add(source);
        baseInfoVO.setIsIp(false);
        baseInfoVO.setCity(threatbookVo.getLocation());
        baseInfoVO.setDomain(list);
        baseInfoVO.setIp(ip);
        baseInfoVO.setPorts(threatbookVo.getPorts());
        baseInfoVO.setWhoisVo(domainInfo);
        List<String> labelList = new ArrayList<>();
        labelList.add(threatbookVo.getCarrier());
        baseInfoVO.setLabelList(labelList);
        baseInfoVO.setJudged("暂无更新");
        return baseInfoVO;
    }

    /**
     * 域名反查
     *
     * @param fofaBOS
     * @return
     */
    public List<FofaBO> domainOrIpPegging(List<FofaBO> fofaBOS) {
        if (fofaBOS.size() == 0) {
            return new ArrayList<>();
        }
        List<FofaBO> list = new ArrayList<>();
        double target = 0.00;
        FofaBO fofaBO = null;
        for (int i = 0; i < fofaBOS.size(); i++) {
            if (target < fofaBOS.get(i).getAccuracy()) {
                fofaBO = fofaBOS.get(i);
                target = fofaBOS.get(i).getAccuracy();
            }
        }
        list.add(fofaBO);
        return list;
    }

    public ThreatbookVo convertThreatbookVo(ThreatbookIPResponse response) {
        ThreatbookVo target = new ThreatbookVo();
        ThreatbookIPResponse.BasicInfo baseInfo = response.getBasic();
        ThreatbookIPResponse.LocationInfo info = baseInfo.getLocation();
        target.setCarrier(baseInfo.getCarrier());
        target.setLocation(info.getCountry() + "-" + info.getProvince() + "-" + info.getCity());
        List<ThreatbookVo.PortInfo> list = new ArrayList<>();
        response.getPorts().forEach(item -> {
            ThreatbookVo.PortInfo portInfo = new ThreatbookVo.PortInfo();
            portInfo.setPort(Integer.valueOf(item.getPort()));
            portInfo.setProduct(item.getProduct());
            portInfo.setVersion(item.getVersion());
            portInfo.setDetail(item.getDetail());
            list.add(portInfo);
        });
        target.setPorts(list);
        return target;
    }


}
