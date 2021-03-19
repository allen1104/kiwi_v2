package com.kiwi.cn.backend.controller;

import com.kiwi.cn.backend.common.service.impl.ServiceResult;
import com.kiwi.cn.backend.common.vo.RequestVO;
import com.kiwi.cn.backend.constant.KiwiCommenConstants;
import com.kiwi.cn.backend.service.api.INewsService;
import com.kiwi.cn.backend.vo.NewsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "新闻search接口")
@RestController
@RequestMapping("/news/search")
public class NewsController {

    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    INewsService service;

    @Operation(summary = "按id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "新闻主键id", required = true, dataType = "Integer"),
    })
    @GetMapping(path = "/{id}")
    public ServiceResult<Optional<NewsVO>, String> findById(@PathVariable Integer id) {
        return ServiceResult.success(service.findById(id));
    }

    @Operation(summary = "首页展示查询")
    @GetMapping("/index/{type}/{size}")
    public ServiceResult getIndex(@PathVariable String type, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by("pubdate").descending());
        Page<NewsVO> newsPage = service.findNews(pageable, type);
        return ServiceResult.success(newsPage.getContent());
    }

    @Operation(summary = "获取轮播")
    @GetMapping("/getCarousel")
    public ServiceResult getCarousel() {
        return ServiceResult.success(service.findCarousel());
    }

    @PostMapping(path = "/findPageList/{type}")
    public ServiceResult<Page<NewsVO>, String> findPageList(@PathVariable String type, @RequestBody RequestVO requestVO) {
        Pageable pageable = requestVO.getPageVO().build(Sort.by("pubdate").descending());
        return ServiceResult.success(service.findPageListByType(pageable, type));
    }
}
