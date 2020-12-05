package com.kiwi.cn.backend.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "kiwi_user_t")
public class UserVO extends BaseVO {
    @Id
    @ApiModelProperty("主键")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @ApiModelProperty("用户姓名")
    @Column(name = "user_name", nullable = false, length = 20, unique = true)
    private String userName;

    @ApiModelProperty("用户密码")
    @Column(name = "user_password", length = 100)
    private String userPassword;

    @ApiModelProperty("用户电话")
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @ApiModelProperty("邮箱")
    @Column(name = "email_address",length = 40)
    private String emailAddress;

    @ApiModelProperty("权限")
    @ManyToMany
    @JoinTable(name = "user_permisson_t", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permisson_id"))
    private List<PermissonVO> permissonVOList;
}
