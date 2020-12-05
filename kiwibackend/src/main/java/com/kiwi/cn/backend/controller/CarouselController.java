package com.kiwi.cn.backend.controller;

import com.kiwi.cn.backend.common.service.impl.ServiceResult;
import com.kiwi.cn.backend.constant.KiwiCommenConstants;
import com.kiwi.cn.backend.service.api.ICarouselService;
import com.kiwi.cn.backend.vo.NewsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api(tags = "滚动接口")
@RestController
@RequestMapping("/carousel")
public class CarouselController {

    @Autowired
    ICarouselService service;

    @Operation(summary = "分页查询")
    @PostMapping(path = "findPageList")
    public ServiceResult<Page<NewsVO>, String> findPageList() {
        Pageable pageable = PageRequest.of(0, 10);
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
            @ApiImplicitParam(name = "id", value = "主键id", required = true, dataType = "Integer", paramType = "path", example = "1"),
    })
    @DeleteMapping("/{id}")
    public ServiceResult delete(@PathVariable Integer id) {
        service.deleteById(id);
        return ServiceResult.success();
    }

    @Operation(summary = "首页展示查询，展示数量：")
    @GetMapping("/index")
    public ServiceResult getIndex() {
        Pageable pageable = PageRequest.of(0, KiwiCommenConstants.INDEX_SHOW_COUNT);
        return ServiceResult.success(service.findPageList(pageable));
    }
}
