package com.example.foodlist.restaurant.repository;

import com.example.foodlist.restaurant.entity.RestaurantEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantRepositoryTest {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RestaurantRepository restaurantRepository;

    RestaurantEntity restaurantEntity;

    private RestaurantEntity createEntity(){
        restaurantEntity = new RestaurantEntity();
        restaurantEntity.setTitle("제목");
        restaurantEntity.setAddress("주소");
        restaurantEntity.setCategory("중식");
        restaurantEntity.setHomePageLink("www.naver.com");
        restaurantEntity.setVisit(false);
        restaurantEntity.setLastVisitDate(null);
        restaurantEntity.setVisitCount(0);
        return restaurantEntity;
    }

    @Test
    public void saveTest(){
        createEntity();
        RestaurantEntity returnEntity = restaurantRepository.save(restaurantEntity);
        Assertions.assertNotNull(returnEntity);
        Assertions.assertEquals(1,returnEntity.getIndex());
    }

    @Test
    public void updateTest(){
        RestaurantEntity  createEntity = createEntity();
        RestaurantEntity returnEntity = restaurantRepository.save(createEntity);

        returnEntity.setTitle("setTitle updating");
        restaurantRepository.save(returnEntity);
        Assertions.assertEquals("setTitle updating",returnEntity.getTitle());
        Assertions.assertEquals(1,restaurantRepository.listAll().size());
    }

    @Test
    public void findByIdTest(){

        createEntity();
        RestaurantEntity returnEntity = restaurantRepository.save(restaurantEntity);

        Optional<RestaurantEntity> optionalRestaurantEntity=  restaurantRepository.findById(1);

        Assertions.assertEquals(true,optionalRestaurantEntity.isPresent());
        Assertions.assertEquals(1,optionalRestaurantEntity.get().getIndex());
    }

    @Test
    public void deleteTest(){

        createEntity();
        restaurantRepository.save(restaurantEntity);
        restaurantRepository.deleteById(1);
        int count = restaurantRepository.listAll().size();
        Assertions.assertEquals(0,count);
    }

    @Test
    public void listAllTest(){

        RestaurantEntity createRestaurantEntity = null;
        for(int i = 0; i<2 ;i++){
             createRestaurantEntity = createEntity();
             restaurantRepository.save(createRestaurantEntity);
        }
        Assertions.assertEquals(2,restaurantRepository.listAll().size());

    }
}