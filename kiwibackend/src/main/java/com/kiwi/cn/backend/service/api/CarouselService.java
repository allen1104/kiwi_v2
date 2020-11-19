package com.kiwi.cn.backend.service.api;

import com.kiwi.cn.backend.vo.NewsVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CarouselService {
    Page<NewsVO> findPageList(Pageable pageable);

    Optional<NewsVO> findById(Integer id);

    NewsVO save(NewsVO newsVO);

    void deleteById(Integer id);
}
