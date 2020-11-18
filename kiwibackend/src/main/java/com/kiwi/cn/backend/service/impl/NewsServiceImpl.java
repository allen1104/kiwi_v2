package com.kiwi.cn.backend.service.impl;

import com.kiwi.cn.backend.dao.NewsDao;
import com.kiwi.cn.backend.service.NewsService;
import com.kiwi.cn.backend.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsDao newsDao;

    @Override
    public Page<NewsVO> findPageList(Pageable pageable) {
        return newsDao.findAll(pageable);
    }

    @Override
    public Optional<NewsVO> findById(Integer id) {
        return newsDao.findById(id);
    }

    @Override
    public NewsVO save(NewsVO newsVO) {
        return newsDao.save(newsVO);
    }

    @Override
    public void deleteById(Integer id) {
        newsDao.deleteById(id);
    }
}
