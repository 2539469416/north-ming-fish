package com.xfa.response;

import lombok.Data;

/**
 * @author 北冥有鱼
 */
@Data
public class FoFaResponse {
    /**
     * IP地址
     */
    private String ip;

    /**
     * 端口
     */
    private Integer port;

    /**
     * 协议名
     */
    private String protocol;

    /**
     * 国家代码
     */
    private String country;

    /**
     * 国家名
     */
    private String countryName;

    /**
     * 区域
     */
    private String region;

    /**
     * 城市
     */
    private String city;

    /**
     * 地理位置 经度
     */
    private Double longitude;

    /**
     * 地理位置 纬度
     */
    private Double latitude;

    /**
     * ASN编号
     */
    private Integer asNumber;

    /**
     * ASN组织
     */
    private String asOrganization;

    /**
     * 主机名
     */
    private String host;

    /**
     * 域名
     */
    private String domain;

    /**
     * 操作系统
     */
    private String os;

    /**
     * 网站server
     */
    private String server;

    /**
     * ICP备案号
     */
    private String icp;

    /**
     * 网站标题
     */
    private String title;

    /**
     * JARM指纹
     */
    private String jarm;

    /**
     * 网站header
     */
    private String header;

    /**
     * 协议banner
     */
    private String banner;

    /**
     * 证书
     */
    private String cert;

    /**
     * 基础协议，比如tcp/udp
     */
    private String baseProtocol;

    /**
     * 资产的URL链接
     */
    private String link;
    
}
