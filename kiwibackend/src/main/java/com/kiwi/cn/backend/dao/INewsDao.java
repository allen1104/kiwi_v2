package com.kiwi.cn.backend.dao;

import com.kiwi.cn.backend.vo.NewsVO;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INewsDao extends JpaRepository<NewsVO, Integer> {

    @Query(value = "select new NewsVO(newsId, title, titleUrl, description, status, pubdate, bizType, isCarousel) from NewsVO t where t.isCarousel = 1 and t.status = 1")
    List<NewsVO> findCarousel();

    @Query(value = "select new NewsVO(newsId, title, titleUrl, description, status, pubdate, bizType, isCarousel) from NewsVO t where t.status = 1 and t.bizType = ?1")
    Page<NewsVO> findNews(Pageable pageable, String type);

    @Query(value = "select new NewsVO(newsId, title, titleUrl, description, status, pubdate, bizType, isCarousel) from NewsVO t where t.status = 1 and t.bizType = ?1")
    Page<NewsVO> findPageListByType(Pageable pageable, String type);
}
