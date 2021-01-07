package com.kiwi.cn.backend.common.service.impl;

import com.kiwi.cn.backend.common.config.QiniuConfig;
import com.kiwi.cn.backend.common.service.QiniuService;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Allen
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    @Autowired
    private QiniuConfig qiniuConfig;

    @Override
    public String getQiniuToken() {
        String accessKey = qiniuConfig.getAccessKey();
        String secretKey = qiniuConfig.getSecretKey();
        String bucket = qiniuConfig.getBucket();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        return upToken;
    }
}
