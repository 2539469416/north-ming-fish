package com.xfa.response;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author 北冥有鱼
 */
@Data
public class WhoisDomainResponse {

    /**
     * 状态码
     */
    @JSONField(name = "StateCode")
    private int stateCode;

    /**
     * 状态说明
     */
    @JSONField(name = "Reason")
    private String reason;

    /**
     * 域名
     */
    @JSONField(name = "Host")
    private String host;

    /**
     * 联系人
     */
    @JSONField(name = "ContactPerson")
    private String contactPerson;

    /**
     * 注册商
     */
    @JSONField(name = "Registrar")
    private String registrar;

    /**
     * 联系邮箱
     */
    @JSONField(name = "Email")
    private String email;

    /**
     * 联系电话
     */
    @JSONField(name = "Phone")
    private String phone;

    /**
     * 创建时间
     */
    @JSONField(name = "CreationDate")
    private String creationDate;

    /**
     * 过期时间
     */
    @JSONField(name = "ExpirationDate")
    private String expirationDate;

    /**
     * 域名服务器
     */
    @JSONField(name = "WhoisServer")
    private String whoisServer;

    /**
     * DNS
     */
    @JSONField(name = "DnsServer")
    private String dnsServer;

    /**
     * 状态
     */
    @JSONField(name = "DomainStatus")
    private String domainStatus;
}
