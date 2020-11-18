package com.kiwi.cn.backend.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author aa
 */
@Entity
@Table(name = "kiwi_news_t")
@Getter
@Setter
public class NewsVO extends BaseVO{

    @ApiModelProperty("主键")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ApiModelProperty("标题")
    @Column(name = "title", length = 20)
    private String title;

    @ApiModelProperty("标题图片地址")
    @Column(name = "title_url", length = 20)
    private String titleUrl;

    @ApiModelProperty("详细说明")
    @Column(name = "description", length = 20)
    private String description;

    @ApiModelProperty("状态")
    @Column(name = "status", length = 20)
    private String status;

    @ApiModelProperty("是否滚动")
    @Column(name = "is_carousel", length = 20)
    private Boolean isCarousel;

    @ApiModelProperty("富文本")
    @OneToOne
    private RichContentVO richContent;

    @Override
    public String toString() {
        return "NewsVO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", titleUrl='" + titleUrl + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", isCarousel=" + isCarousel +
                ", richContent=" + richContent +
                '}'+ super.toString();
    }
}
