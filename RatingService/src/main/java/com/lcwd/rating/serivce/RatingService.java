package com.lcwd.rating.serivce;

import com.lcwd.rating.entity.Rating;

import java.util.List;

public interface RatingService {

    //create
    Rating create(Rating rating);

    //get all ratings
    List<Rating> getRating();

    // get all by userId
    List<Rating> getRatingByUserId(String id);

    // get all by hotel
    List<Rating> getRatingByHotelId(String id);
}
