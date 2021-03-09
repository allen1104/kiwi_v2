package com.kiwi.cn.backend.security.controller;

import cn.hutool.core.collection.CollUtil;
import com.kiwi.cn.backend.security.common.ApiResponse;
import com.kiwi.cn.backend.security.common.PageResult;
import com.kiwi.cn.backend.security.common.Status;
import com.kiwi.cn.backend.security.exception.SecurityException;
import com.kiwi.cn.backend.security.payload.PageCondition;
import com.kiwi.cn.backend.security.service.MonitorService;
import com.kiwi.cn.backend.security.util.PageUtil;
import com.kiwi.cn.backend.security.util.SecurityUtil;
import com.kiwi.cn.backend.security.vo.OnlineUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 监控 Controller，在线用户，手动踢出用户等功能
 * </p>
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/monitor")
public class MonitorController {
    @Autowired
    private MonitorService monitorService;

    /**
     * 在线用户列表
     *
     * @param pageCondition 分页参数
     */
    @GetMapping("/online/user")
    public ApiResponse onlineUser(PageCondition pageCondition) {
        PageUtil.checkPageCondition(pageCondition, PageCondition.class);
        PageResult<OnlineUser> pageResult = monitorService.onlineUser(pageCondition);
        return ApiResponse.ofSuccess(pageResult);
    }

    /**
     * 批量踢出在线用户
     *
     * @param names 用户名列表
     */
    @DeleteMapping("/online/user/kickout")
    public ApiResponse kickoutOnlineUser(@RequestBody List<String> names) {
        if (CollUtil.isEmpty(names)) {
            throw new SecurityException(Status.PARAM_NOT_NULL);
        }
        if (names.contains(SecurityUtil.getCurrentUsername())) {
            throw new SecurityException(Status.KICKOUT_SELF);
        }
        monitorService.kickout(names);
        return ApiResponse.ofSuccess();
    }
}
