package com.haust.back.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class New {
    @TableId(type = IdType.AUTO)
    private Integer newId;
    private Integer newCid;
    private String newCategory;
    private String newCover;
    private String newTitle;
    private String newCnt;
    private String newUrl;
    private String newDate;
}
