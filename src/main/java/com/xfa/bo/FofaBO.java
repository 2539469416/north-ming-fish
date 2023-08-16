package com.xfa.bo;

import lombok.Data;

import java.util.List;

/**
 * @author 北冥有鱼
 */
@Data
public class FofaBO {
    private String host;

    private String ip;

    private String analysisDomain;

    private List<String> ports;

    private List<String> hosts;

    private String city;

    private String os;

    private String icp;

    private String cert;

}
