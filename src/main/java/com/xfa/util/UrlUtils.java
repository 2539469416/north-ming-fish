package com.xfa.util;

import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 北冥有鱼
 */
@Component
public class UrlUtils {
    public static String getDomainFromUrl(String source) {
        String fullDomain = "";
        try {
            if (!source.contains("http://")){
                source = "http://" + source;
            }
            URL url = new URL(source);
            String host = url.getHost();         // 获取域名，例如 "www.example.com"
            fullDomain = host;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fullDomain;
    }

    public static boolean isIPv4(String input) {
        String ipv4Pattern = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        Pattern pattern = Pattern.compile(ipv4Pattern);
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
