package com.example.foodlist.naver.dto;


import lombok.Data;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Data
public class SearchImgReqDto {

    private String query = "";
    private int display = 10;
    private int start = 1;
    private String sort = "sim";
    private String filter ="medium";//all


    public MultiValueMap<String,String> multiValueMap(){

        MultiValueMap multiValueMap = new LinkedMultiValueMap<String,String>();

        multiValueMap.add("query",query);
        multiValueMap.add("display",String.valueOf(display));
        multiValueMap.add("start",String.valueOf(start));
        multiValueMap.add("sort",sort);
        multiValueMap.add("filter",filter);

        return multiValueMap;
    }
}
