package com.haust.back.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userPwd;
    private String userUrl;
    private String userCnt;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date userBirthday;
    private Integer userGender;
    private String userPhone;
    private String userEmail;
}
