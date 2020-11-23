package com.kiwi.cn.backend.dao;

import com.kiwi.cn.backend.vo.NewsVO;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsDao extends JpaRepository<NewsVO, Integer> {
    public static final String ONE = "1";
    public static final Boolean TRUE = true;

    @Query(value = "from NewsVO t where t.isCarousel = ?1 and t.status = ?2")
    List<NewsVO> findCarousel(Boolean p1,String p2);
}
