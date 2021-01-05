package com.kiwi.cn.backend.common.controller;

import com.kiwi.cn.backend.common.service.QiniuService;
import com.kiwi.cn.backend.common.service.impl.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qiniu")
public class QiniuContorller {

    @Autowired
    private QiniuService qiniuService;

    @RequestMapping("/getToken")
    public ServiceResult<String, String> getToken(){
        return ServiceResult.success(qiniuService.getQiniuToken());
    }
}
