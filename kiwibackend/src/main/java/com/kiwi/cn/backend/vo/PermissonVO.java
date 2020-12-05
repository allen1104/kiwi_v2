package com.kiwi.cn.backend.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity
public class PermissonVO extends BaseVO{

    @Id
    @ApiModelProperty("主键")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @ApiModelProperty("用户姓名")
    @Column(name = "user_name", nullable = false, length = 20, unique = true)
    private String userName;
}
