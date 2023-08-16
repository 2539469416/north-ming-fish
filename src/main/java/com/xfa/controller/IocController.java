package com.xfa.controller;

import com.xfa.handler.BusinessException;
import com.xfa.service.impl.IocServiceImpl;
import com.xfa.util.UrlUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author 北冥有鱼
 */
@RestController
public class IocController {
    @Resource
    private IocServiceImpl iocServiceImpl;
    @GetMapping
    public ResponseEntity queryBaseIoc(String query) {
        if (UrlUtils.isIPv4(query)) {

        } else {
            String url = UrlUtils.getDomainFromUrl(query);
            if (url == null) {
                new BusinessException("非法查询字符");
            }
        }
        return ResponseEntity.ok(1);
    }
}
