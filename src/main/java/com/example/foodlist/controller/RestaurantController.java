package com.example.foodlist.controller;

import com.example.foodlist.restaurant.dto.RestaurantDto;
import com.example.foodlist.service.RestaurantService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/restaurant")
@RestController
@RequiredArgsConstructor//autowired사용 없이 타 클래스 주입
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/search")
    public RestaurantDto search(@RequestParam String query){
        return restaurantService.search(query);
    }

    @PostMapping("/addWish")
    public void addWish(@RequestBody RestaurantDto restaurantDto){

        restaurantService.addWish(restaurantDto);
    }

    @DeleteMapping("{index}")
    public void deleteWish(@PathVariable int index){

        restaurantService.deleteWish(index);
    }

}
