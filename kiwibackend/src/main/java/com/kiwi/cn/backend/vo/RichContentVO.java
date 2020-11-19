package com.kiwi.cn.backend.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "kiwi_rich_content_t")
@Getter
@Setter
public class RichContentVO extends BaseVO{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer richContentId;

    @Column(name = "content", length = 65536)
    private String content;

    @Override
    public String toString() {
        return "RichContentVO{" +
                "Id=" + richContentId +
                ", content='" + content + '\'' +
                '}' + super.toString();
    }
}
