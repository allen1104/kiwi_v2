package com.kiwi.cn.backend.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("kiwi.qiniu")
public class QiniuConfig {
    private String accessKey;
    private String secretKey;
    private String bucket;
}
