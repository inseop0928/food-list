package com.example.foodlist.naver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchLocalResDto {

    private Date lastBuildDate;
    private int total;
    private int start;
    private int display;
    private String category;
    private List<SearchItems> items;

    
    //리스트로 받는 경우는 내부클래스 생성
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchItems{

        private String title;
        private String link;
        private String description;
        private String telephone;
        private String address;
        private String roadAddress;
        private int mapx;
        private int mapy;

    }
}
