package com.xfa.vo;

import com.xfa.response.ThreatbookIPResponse;
import lombok.Data;

import java.util.List;

/**
 * @author 北冥有鱼
 */

@Data
public class ThreatbookVo {
    private String carrier;
    private String location;
    private List<PortInfo> ports;

    @Data
    public static class PortInfo{
        private Integer port;

        private String product;

        private String detail;

        private String version;
    }
}
