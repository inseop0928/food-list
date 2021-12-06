package com.example.foodlist.restaurant.repository;

import com.example.foodlist.db.MemoryDBRepositoryAbstract;
import com.example.foodlist.restaurant.entity.RestaurantEntity;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantRepository extends MemoryDBRepositoryAbstract<RestaurantEntity> {

}
