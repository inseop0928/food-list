package com.example.foodlist.naver;


import com.example.foodlist.naver.dto.SearchImgReqDto;
import com.example.foodlist.naver.dto.SearchImgResDto;
import com.example.foodlist.naver.dto.SearchLocalReqDto;
import com.example.foodlist.naver.dto.SearchLocalResDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.net.URI;

@Component
public class NaverClient {

    //yaml파일에서 가져온 환경설정
    @Value("${naver.client.id}")
    private String id;

    @Value("${naver.client.secret}")
    private String secret;

    @Value("${naver.url.search.local}")
    private String localUrl;

    @Value("${naver.url.search.image}")
    private String imageUrl;

    public SearchLocalResDto searchLocal(SearchLocalReqDto reqDto){

        URI uri = UriComponentsBuilder.fromUriString(localUrl)
                .queryParams(reqDto.multiValueMap())
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id",id);
        headers.set("X-Naver-Client-Secret",secret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchLocalResDto>(){};

        ResponseEntity<SearchLocalResDto> responseEntity = new  RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType);

        return responseEntity.getBody();
    }

    public SearchImgResDto searchImg(SearchImgReqDto searchImgReqDto){
        URI uri = UriComponentsBuilder.fromUriString(imageUrl)
                .queryParams(searchImgReqDto.multiValueMap())
                .build()
                .encode()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id",id);
        headers.set("X-Naver-Client-Secret",secret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchImgResDto>(){};

        ResponseEntity<SearchImgResDto> responseEntity = new  RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType);

        return responseEntity.getBody();

    }
}
