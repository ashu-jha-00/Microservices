package com.lcwd.user.service.services.impl;

import com.lcwd.user.service.dto.Hotel;
import com.lcwd.user.service.dto.Rating;
import com.lcwd.user.service.dto.UserDto;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.exception.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.external.services.RatingService;
import com.lcwd.user.service.repositories.UserRepository;
import com.lcwd.user.service.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private RatingService ratingService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public User create(User user) {
       String id = UUID.randomUUID().toString();
        user.setUserId(id);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
//        List<User> users = userRepository.findAll();
//
//        // Extract userIds
//        List<String> userIds = users.stream()
//                .map(User::getUserId)
//                .collect(Collectors.toList());
//
//        // create a controller on rating service
////        @PostMapping("/users/batch")
////        public ResponseEntity<List<Rating>> getRatingsForUsers(@RequestBody List<String> userIds) {
////            List<Rating> ratings = ratingService.getRatingsByUserIds(userIds);
////            return ResponseEntity.ok(ratings);
////        }
//
//        // Call batch ratings endpoint
//        List<Rating> allRatings = restTemplate.postForObject(
//                "http://localhost:8083/ratings/users/batch",
//                userIds,
//                List.class);
//
//        // Group ratings by userId
//        Map<String, List<Rating>> ratingsByUser = allRatings.stream()
//                .collect(Collectors.groupingBy(r -> ((Map<String, Object>) r).get("userId").toString()));
//
//        // Set ratings on each user
//        for (User user : users) {
//            List<Rating> userRatings = (List<Rating>)(Object) ratingsByUser.getOrDefault(user.getUserId(), Collections.emptyList());
//            user.setRatings((ArrayList<Rating>) userRatings);
//        }
//
//        return users;
//        List<User> users = userRepository.findAll();
//
//        for (User user : users) {
//            String url = "http://localhost:8083/ratings/users/" + user.getUserId();
//
//            ArrayList<Rating> ratings = restTemplate.getForObject(url, ArrayList.class);
//            user.setRatings(ratings);
//
//            logger.info("Called endpoint '{}'", url);
//        }
//
//        return users;
        return userRepository.findAll();
    }

    @Override
    public UserDto getUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Resource with given id not found"));

//        Rating[] ratingsOfUser = restTemplate.getForObject(
//                "http://RATING-SERVICE/ratings/users/" + user.getUserId(),
//                Rating[].class);

        List<Rating> ratingsOfUser = ratingService.getRatingsByUserId(user.getUserId());

        List<Rating> ratingList = ratingsOfUser.stream()
                .map(rating -> {
//                    String hotelUrl = "http://HOTEL-SERVICE/hotels/" + rating.getHotelId();
//                    Hotel hotel = restTemplate.getForObject(hotelUrl, Hotel.class);

                    Hotel hotel = hotelService.getHotel(rating.getHotelId());

                    rating.setHotel(hotel);
                    return rating;
                })
                .collect(Collectors.toList());

//        Best simple way: Add Lombok @Builder to your DTO and use builder pattern to create instances nicely.
//        Alternative: A static factory method inside the DTO.
//        For larger projects: Use ModelMapper or MapStruct for automated mapping.
        UserDto userDto = new UserDto(
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getAbout(),
                ratingList
        );

        return userDto;
    }
}