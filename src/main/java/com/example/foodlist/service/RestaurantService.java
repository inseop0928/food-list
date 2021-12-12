package com.example.foodlist.service;

import com.example.foodlist.naver.NaverClient;
import com.example.foodlist.naver.dto.SearchImgReqDto;
import com.example.foodlist.naver.dto.SearchLocalReqDto;
import com.example.foodlist.naver.dto.SearchLocalResDto;
import com.example.foodlist.restaurant.dto.RestaurantDto;
import com.example.foodlist.restaurant.entity.RestaurantEntity;
import com.example.foodlist.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final NaverClient naverClient;
    private final RestaurantRepository restaurantRepository;

    public RestaurantDto search(String query){

        SearchLocalReqDto searchLocalReqDto = new SearchLocalReqDto();
        searchLocalReqDto.setQuery(query);

        SearchLocalResDto searchLocalResDto = naverClient.searchLocal(searchLocalReqDto);

        if(searchLocalResDto.getTotal() > 0){
           var localItem =  searchLocalResDto.getItems().stream().findFirst().get();

           String imageQuery  = localItem.getTitle().replaceAll("<[^>]*>","");

           SearchImgReqDto searchImgReqDto = new SearchImgReqDto();
           searchImgReqDto.setQuery(imageQuery);

           var searchImg = naverClient.searchImg(searchImgReqDto);

           if(searchImg.getTotal() >0){
               var image = searchImg.getItems().stream().findFirst().get();

               RestaurantDto restaurantDto = new RestaurantDto();
               restaurantDto.setTitle(localItem.getTitle());
               restaurantDto.setCategory(localItem.getCategory());
               restaurantDto.setAddress(localItem.getAddress());
               restaurantDto.setRoadAddress(localItem.getRoadAddress());
               restaurantDto.setHomePageLink(localItem.getLink());
               restaurantDto.setImageLink(image.getLink());

               return restaurantDto;
           }
        }
        return new RestaurantDto();//없으면 빈값
    }

    public void addWish(RestaurantDto restaurantDto){

        restaurantRepository.save(this.dtoToEntity(restaurantDto));
    }

    public void deleteWish(int idx){

        restaurantRepository.findById(idx);
    }

    public RestaurantEntity dtoToEntity(RestaurantDto dto){

        RestaurantEntity restaurantEntity = new RestaurantEntity();

        for(Method method : dto.getClass().getMethods()){

            if(method.getName().indexOf("get") == 0){

                String methodName = method.getName();

                try {
                   //Method entityMethod = RestaurantEntity.class.getMethod(targetMethod,String.class);
                    Method sourceMethod = dto.getClass().getMethod(method.getName());

                    String targetName = methodName.replace("get","set");
                    for(Method method1 : restaurantEntity.getClass().getMethods()){
                        if(targetName.equals(method1.getName())){
                            Method entityMethod = restaurantEntity.getClass().getMethod(targetName,method1.getParameterTypes());
                            entityMethod.invoke(restaurantEntity,sourceMethod.invoke(dto));
                            System.out.println(targetName);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
        return restaurantEntity;
    }
}
