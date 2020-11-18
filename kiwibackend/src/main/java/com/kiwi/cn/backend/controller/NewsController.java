package com.kiwi.cn.backend.controller;

import com.kiwi.cn.backend.service.NewsService;
import com.kiwi.cn.backend.vo.NewsVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Optional;

@Api(tags = "新闻接口")
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @ApiOperation(value = "分页查询")
    @PostMapping(path = "findPageList")
    public Page<NewsVO> findPageList() {
        Pageable pageable = PageRequest.of(0,0);
        return newsService.findPageList(pageable);
    }

    @ApiOperation(value = "按id查询")
    @GetMapping(path = "/{id}")
    public Optional<NewsVO> findById(@RequestParam(name = "id") Integer id) {
        return newsService.findById(id);
    }

    @ApiOperation(value = "保存/更新")
    @PostMapping(path = "/save")
    public NewsVO save(@RequestBody NewsVO newsVO) {
        return newsService.save(newsVO);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public void delete(@RequestParam(name = "id") Integer id) {
        newsService.deleteById(id);
    }
}
