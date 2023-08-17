package com.xfa.vo;

import com.xfa.response.WhoisDomainResponse;
import lombok.Data;

import java.util.List;

/**
 * @author 北冥有鱼
 */
@Data
public class BaseInfoVO {
    private Boolean isIp;

    private String city;

    private List<String> domain;

    private String ip;

    private List<ThreatbookVo.PortInfo> ports;

    private WhoisDomainResponse whoisVo;

    private List<String> labelList;

    /**
     * 研判
     */
    private String judged;
}
