package com.kiwi.cn.backend.service.impl;

import com.kiwi.cn.backend.dao.CarouselDao;
import com.kiwi.cn.backend.dao.NewsDao;
import com.kiwi.cn.backend.service.api.CarouselService;
import com.kiwi.cn.backend.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    CarouselDao dao;

    @Override
    public Page<NewsVO> findPageList(Pageable pageable) {
        return dao.findAll(pageable);
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
}
