package com.haust.back.entity;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

@Data
public class Comment {
    @TableId(type = IdType.AUTO)
    private Integer cId;
    private Integer userId;
    private Integer newId;
    private String cCnt;
    private Timestamp cDate;
    private String userName;
}
