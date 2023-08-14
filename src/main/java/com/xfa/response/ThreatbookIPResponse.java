package com.xfa.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author 北冥有鱼
 */
@Data
public class ThreatbookIPResponse {

    // 基础信息，JSON对象，详细字段见下方的BasicInfo类
    private BasicInfo basic;

    // 从威胁情报中分析的综合判定威胁类型
    private List<String> judgments;

    // 相关攻击团伙或安全事件信息列表，详细字段见下方的TagClass类
    private List<TagClass> tagsClasses;

    // 威胁情报，详细字段见下方的Intelligences类
    private Intelligences intelligences;

    // 相关样本列表，最多返回20条，详细字段见下方的SampleInfo类
    private List<SampleInfo> samples;

    // ASN信息，详细字段见下方的AsnInfo类
    private AsnInfo asn;

    // IP开放的相关端口信息列表，详细字段见下方的PortInfo类
    private List<PortInfo> ports;

    // SSL相关证书信息列表，详细字段见下方的SslCertificateInfo类
    private List<SslCertificateInfo> cas;

    // Rdns记录列表
    private List<String> rdnsList;

    // 情报的最近更新时间
    private String updateTime;

    // 反查当前域名数量
    private String sumCurDomains;

    // 应用场景，如企业专线、数据中心等
    private String scene;

    // 内部类省略，具体字段和注释参考前述内容
    // ...

    // Getter和Setter方法省略，根据实际情况添加
    @Data
    public static class IpInfo {
        // Fields for IP information, refer to point 3 in the description
    }

    @Data
    public static class BasicInfo {
        private String carrier;
        private LocationInfo location;
    }

    @Data
    public static class LocationInfo {
        private String country;
        private String countryCode;
        private String province;
        private String city;
        private String lng;
        private String lat;
    }

    @Data
    public static class TagClass {
        private String tagsType;
        private String tags;
    }

    @Data
    public static class Intelligences {
        private List<ThreatbookLabInfo> threatbookLab;
        private List<ThreatbookLabInfo> xReward;
        private List<ThreatbookLabInfo> openSource;
    }

    @Data
    public static class ThreatbookLabInfo {
        private String source;
        private String findTime;
        private String updateTime;
        private int confidence;
        private boolean expired;
        private List<String> intelTypes;
        private List<String> intelTags;


    }

    @Data
    public static class SampleInfo {
        private String sha256;
        private String scanTime;
        private String ratio;
        private String malwareType;
        private String malwareFamily;


    }

    @Data
    public static class AsnInfo {
        private String number;
        private String info;
        private int rank;


    }

    @Data
    public static class PortInfo {
        private String port;
        private String module;
        private String product;
        private String version;
        private String detail;


    }

    @Data
    public static class SslCertificateInfo {
        private String protocol;
        private String port;
        private String period;
        private String digitalCertificate;
    }
}
