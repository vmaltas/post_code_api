package com.postcodedistance.post.code.api.controller;

import com.postcodedistance.post.code.api.dto.*;
import com.postcodedistance.post.code.api.entity.PostCode;
import com.postcodedistance.post.code.api.service.PostCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/postCodeApi", produces = "application/json")
@Secured("ROLE_USER")

public class PostCodeApiController {

    @Autowired
    private PostCodeService postCodeService;

    private final static String DISTANCE_UNIT = "km";

    private final static double EARTH_RADIUS = 6371; // radius in kilometers


    @RequestMapping(value = "/getDistance", method = RequestMethod.GET)
    @ResponseBody
    public DistanceResponseDto getDistance(@RequestParam(value = "firstUkPostalCode", required = true) String firstUkPostalCode,
                                           @RequestParam(value = "secondUkPostalCode", required = true) String secondUkPostalCode) {

        UUID uuid = UUID.randomUUID();
        log.info("getDistance started.UUID" + uuid + " firstUkPostalCode:" + firstUkPostalCode + " secondUkPostalCode:" + secondUkPostalCode);
        DistanceResponseDto distanceResponseDto = new DistanceResponseDto();
        Optional<PostCode> firstPostCode = postCodeService.findByPostCodeName(firstUkPostalCode);
        Optional<PostCode> secondPostCode = postCodeService.findByPostCodeName(secondUkPostalCode);

        if (!firstPostCode.isPresent()) {
            log.error(uuid + "First Postcode is not available." + firstUkPostalCode);
            distanceResponseDto.setStatus(-1);
            distanceResponseDto.setMessage("First Postcode is not available.");
        }

        if (!secondPostCode.isPresent()) {
            log.error(uuid + "Second Postcode is not available." + secondUkPostalCode);
            distanceResponseDto.setStatus(-2);
            distanceResponseDto.setMessage("Second Postcode is not available.");
        }

        double distance = calculateDistance(firstPostCode.get().getLatitude(), firstPostCode.get().getLongitude(), secondPostCode.get().getLatitude(), secondPostCode.get().getLongitude());

        PostCodeDto firstPostCodeDto = PostCodeDto.builder()
                .postalCode(firstPostCode.get().getPostCodeName())
                .latitude(firstPostCode.get().getLatitude())
                .longitude(firstPostCode.get().getLongitude())
                .build();


        PostCodeDto secondPostCodeDto = PostCodeDto.builder()
                .postalCode(secondPostCode.get().getPostCodeName())
                .latitude(secondPostCode.get().getLatitude())
                .longitude(secondPostCode.get().getLongitude())
                .build();

        distanceResponseDto = DistanceResponseDto.builder()
                .firstUkPostalCode(firstPostCodeDto)
                .secondUkPostalCode(secondPostCodeDto)
                .distance(distance)
                .distanceUnit(DISTANCE_UNIT)
                .build();

        distanceResponseDto.setStatus(0);
        distanceResponseDto.setMessage("Success");
        log.info("getDistance ended successfully.UUID" + uuid + " firstUkPostalCode:" + firstUkPostalCode + " secondUkPostalCode:" + secondUkPostalCode);
        return distanceResponseDto;

    }

    @RequestMapping(value = "/listPostCode", method = RequestMethod.GET)
    @ResponseBody
    public ListPostCodeResponseDto listPostCode(@RequestParam("page") int page, @RequestParam("start") int start, @RequestParam("limit") int limit) {

        UUID uuid = UUID.randomUUID();
        log.info("listPostCode started.UUID" + uuid + " with page:" + page + " start:" + start + "limit " + limit);
        ListPostCodeResponseDto listPostCodeResponseDto = new ListPostCodeResponseDto();
        List<PostCodeDto> postCodeDtoList = new ArrayList<PostCodeDto>();
        Pageable pageAndLimit = new PageRequest(page - 1, limit, new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
        Page<PostCode> postCodeList = postCodeService.findAll(pageAndLimit);

        if (postCodeList.getSize() == 0) {
            listPostCodeResponseDto.setStatus(-3);
            listPostCodeResponseDto.setMessage("No records found.");
        }
        for (PostCode postCode : postCodeList) {
            PostCodeDto postCodeDto = PostCodeDto.builder()
                    .id(postCode.getId())
                    .postalCode(postCode.getPostCodeName())
                    .latitude(postCode.getLatitude())
                    .longitude(postCode.getLongitude())
                    .build();
            postCodeDtoList.add(postCodeDto);
        }
        listPostCodeResponseDto.setPostCodeDtoList(postCodeDtoList);
        listPostCodeResponseDto.setStatus(0);
        listPostCodeResponseDto.setMessage("Success");
        log.info("listPostCode ended successfully.UUID" + uuid + " with page:" + page + " start:" + start + "limit " + limit);
        return listPostCodeResponseDto;

    }

    @RequestMapping(value = "/updatePostCode", method = RequestMethod.POST)
    @ResponseBody
    public UpdatePostCodeResponseDto updatePostCode(@RequestBody UpdatePostCodeRequestDto updatePostCodeRequestDto) {

        UpdatePostCodeResponseDto updatePostCodeResponseDto = new UpdatePostCodeResponseDto();
        UUID uuid = UUID.randomUUID();
        log.info("updatePostCode started.UUID" + uuid + " updatePostCodeRequestDto:" + updatePostCodeRequestDto.toString());
        Optional<PostCode> postCode = postCodeService.findById(updatePostCodeRequestDto.getId());


        if (postCode.isPresent()) {
            postCode.get().setPostCodeName(updatePostCodeRequestDto.getPostCode());
            postCode.get().setLatitude(updatePostCodeRequestDto.getLatitude());
            postCode.get().setLongitude(updatePostCodeRequestDto.getLongitude());
            postCodeService.save(postCode.get());

        } else {
            log.error(uuid + "Postcode is not available." + updatePostCodeRequestDto.toString());
            updatePostCodeResponseDto.setStatus(-4);
            updatePostCodeResponseDto.setMessage("Postcode is not available.");
        }

        log.info("getDistance ended successfully.UUID" + uuid + " updatePostCodeRequestDto:" + updatePostCodeRequestDto.toString());

        updatePostCodeResponseDto.setStatus(0);
        updatePostCodeResponseDto.setMessage("Success");
        return updatePostCodeResponseDto;

    }


    private double calculateDistance(double latitude, double longitude, double latitude2, double
            longitude2) {
        // Using Haversine formula! See Wikipedia;
        double lon1Radians = Math.toRadians(longitude);
        double lon2Radians = Math.toRadians(longitude2);
        double lat1Radians = Math.toRadians(latitude);
        double lat2Radians = Math.toRadians(latitude2);
        double a = haversine(lat1Radians, lat2Radians)
                + Math.cos(lat1Radians) * Math.cos(lat2Radians) * haversine(lon1Radians, lon2Radians);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (EARTH_RADIUS * c);
    }

    private double haversine(double deg1, double deg2) {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }

    private double square(double x) {
        return x * x;
    }
}
