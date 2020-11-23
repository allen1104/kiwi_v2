package com.kiwi.cn.backend.controller;

import com.kiwi.cn.backend.common.service.impl.ServiceResult;
import com.kiwi.cn.backend.constant.KiwiCommenConstants;
import com.kiwi.cn.backend.service.api.NewsService;
import com.kiwi.cn.backend.vo.NewsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = "新闻接口")
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService service;

    @Operation(summary = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "size", value = "每页大小", required = true, dataType = "Integer"),
    })
    @PostMapping(path = "findPageList/{size}/{page}")
    public ServiceResult<Page<NewsVO>, String> findPageList(@PathVariable Integer page, @PathVariable Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("newsId").descending());
        return ServiceResult.success(service.findPageList(pageable));
    }

    @Operation(summary = "按id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "新闻主键id", required = true, dataType = "Integer"),
    })
    @GetMapping(path = "/{id}")
    public ServiceResult<Optional<NewsVO>, String> findById(@PathVariable Integer id) {
        return ServiceResult.success(service.findById(id));
    }

    @Operation(summary = "保存/更新")
    @PostMapping(path = "/save")
    public ServiceResult<NewsVO, String> save(@RequestBody NewsVO newsVO) {
        return ServiceResult.success(service.save(newsVO));
    }

    @Operation(summary = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "新闻主键id", required = true, dataType = "Integer", paramType = "path", example = "1"),
    })
    @DeleteMapping("/{id}")
    public ServiceResult delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ServiceResult.success();
    }

    @Operation(summary = "首页展示查询")
    @GetMapping("/index")
    public ServiceResult getIndex() {
        Pageable pageable = PageRequest.of(0, KiwiCommenConstants.INDEX_SHOW_COUNT, Sort.by("newsId").descending());
        Page<NewsVO> newsPage = service.findPageList(pageable);
        return ServiceResult.success(newsPage.getContent());
    }

    @Operation(summary = "获取轮播")
    @GetMapping("/getCarousel")
    public ServiceResult getCarousel() {
        return ServiceResult.success(service.findCarousel());
    }
}
