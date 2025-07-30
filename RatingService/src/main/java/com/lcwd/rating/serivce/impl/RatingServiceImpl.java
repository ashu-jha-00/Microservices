package com.lcwd.rating.serivce.impl;

import com.lcwd.rating.entity.Rating;
import com.lcwd.rating.repository.RatingRepository;
import com.lcwd.rating.serivce.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository repository;

    @Override
    public Rating create(Rating rating) {
        return repository.save(rating);
    }

    @Override
    public List<Rating> getRating() {
        return repository.findAll();
    }

    @Override
    public List<Rating> getRatingByUserId(String id) {
        return repository.findByUserId(id);
    }

    @Override
    public List<Rating> getRatingByHotelId(String id) {
        return repository.findByHotelId(id);
    }
}
