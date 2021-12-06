package com.example.foodlist.naver.dto;


import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
public class SearchLocalReqDto {

    private String query = "";
    private int display = 1;
    private int start = 1;
    private String sort = "random";

    public MultiValueMap<String,String> multiValueMap(){

        MultiValueMap multiValueMap = new LinkedMultiValueMap<String,String>();

        multiValueMap.add("query",query);
        multiValueMap.add("display",String.valueOf(display));
        multiValueMap.add("start",String.valueOf(start));
        multiValueMap.add("sort",sort);

        return multiValueMap;
    }
}
