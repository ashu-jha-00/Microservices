package com.lcwd.user.service.external.services;


import com.lcwd.user.service.dto.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

    // we can get put post delete any type of mapping , conditional it is defined in that service
    @GetMapping("/ratings/users/{userId}")
    public List<Rating> getRatingsByUserId(@PathVariable String userId);

    //Post mapping
    @PostMapping("/ratings/{ratingId}")
    public Rating createRating(Rating values);


}
