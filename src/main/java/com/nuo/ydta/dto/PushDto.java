package com.nuo.ydta.dto;


import lombok.Data;

@Data
public class PushDto {

    private String title;
    private String content;
    private int roleId;
    private String sender;

}
