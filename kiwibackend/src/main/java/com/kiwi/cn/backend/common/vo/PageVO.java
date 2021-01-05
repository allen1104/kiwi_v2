package com.kiwi.cn.backend.common.vo;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * @author Allen
 */
@Data
public class PageVO {
    private Integer pageSize;
    private Integer currentPage;
    private Integer total;

    public PageRequest build(Sort sort) {
        return PageRequest.of(currentPage - 1, pageSize, sort);
    }

    public PageRequest build() {
        return PageRequest.of(currentPage - 1, pageSize);
    }
}
