package com.example.foodlist.naver;

import com.example.foodlist.naver.dto.SearchImgReqDto;
import com.example.foodlist.naver.dto.SearchImgResDto;
import com.example.foodlist.naver.dto.SearchLocalReqDto;
import com.example.foodlist.naver.dto.SearchLocalResDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class NaverClientTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NaverClient naverClient;

    @Test
    public void search(){
        SearchLocalReqDto searchLocalReqDto = new SearchLocalReqDto();
        searchLocalReqDto.setQuery("중국집");

       // NaverClient naverClient = new NaverClient();
        SearchLocalResDto resDto = naverClient.searchLocal(searchLocalReqDto);
        System.out.println(resDto);
    }

    @Test
    public void searchImg(){
        SearchImgReqDto searchImgReqDto = new SearchImgReqDto();
        searchImgReqDto.setQuery("중국집");

        // NaverClient naverClient = new NaverClient();
        SearchImgResDto resDto = naverClient.searchImg(searchImgReqDto);
        System.out.println(resDto);

    }
}