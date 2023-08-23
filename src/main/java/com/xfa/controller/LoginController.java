package com.xfa.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 北冥有鱼
 */
@RestController
public class LoginController {
    @PostMapping("/login")
    public String login(@RequestBody Object o){
        return "{\n" +
                "  \"code\": 200,\n" +
                "  \"msg\": \"登录成功\",\n" +
                "  \"data\": {\n" +
                "    \"nickName\": \"超级管理员\",\n" +
                "    \"userName\": \"admin\",\n" +
                "    \"userId\": 1,\n" +
                "    \"roleId\": 1,\n" +
                "    \"token\": \"C9A2O0TaZL9A08rnMriBSuiK0QPyonMyEuYG6BaDvTeaw2EgegffJmEt5kWS18lhbE7Y2UX3ptRDCVyKcrQtQl1SJQhplOKSrfuL\",\n" +
                "    \"roles\": [\n" +
                "      {\n" +
                "        \"roleCode\": \"ROLE_admin\",\n" +
                "        \"roleId\": 1,\n" +
                "        \"roleName\": \"超级管理员\"\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
    }
}
