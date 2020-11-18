package com.kiwi.cn.backend.service;

import com.kiwi.cn.backend.vo.NewsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NewsService {
    Page<NewsVO> findPageList(Pageable pageable);

    Optional<NewsVO> findById(Integer id);

    NewsVO save(NewsVO newsVO);

    void deleteById(Integer id);
}
