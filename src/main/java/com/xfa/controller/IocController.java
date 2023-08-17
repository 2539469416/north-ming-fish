package com.xfa.controller;

import com.xfa.service.impl.IocServiceImpl;
import com.xfa.vo.BaseInfoVO;
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
    @GetMapping("/getSearchIoc")
    public ResponseEntity queryBaseIoc(String query) {
        BaseInfoVO baseInfoVO = iocServiceImpl.IocDispatch(query);
        return ResponseEntity.ok(baseInfoVO);
    }
}
