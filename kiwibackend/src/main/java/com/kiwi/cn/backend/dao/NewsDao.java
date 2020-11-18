package com.kiwi.cn.backend.dao;

import com.kiwi.cn.backend.vo.NewsVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsDao extends JpaRepository<NewsVO, Integer> {
}
