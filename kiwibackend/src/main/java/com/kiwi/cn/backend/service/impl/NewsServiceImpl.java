package com.kiwi.cn.backend.service.impl;

import com.kiwi.cn.backend.dao.INewsDao;
import com.kiwi.cn.backend.service.api.INewsService;
import com.kiwi.cn.backend.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    INewsDao dao;

    @Override
    public Page<NewsVO> findPageList(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public Page<NewsVO> findPageListByType(Pageable pageable, String type) {
        return dao.findPageListByType(pageable, type);
    }

    @Override
    public Optional<NewsVO> findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public NewsVO save(NewsVO newsVO) {
        return dao.save(newsVO);
    }

    @Override
    public void deleteById(Integer id) {
        dao.deleteById(id);
    }

    @Override
    public List<NewsVO> findCarousel() {
        return dao.findCarousel();
    }

    @Override
    public Page<NewsVO> findNews(Pageable pageable, String type) {
        return dao.findNews(pageable, type);
    }
}
