package com.postcodedistance.post.code.api.dto;

import lombok.Data;

@Data
public class UpdatePostCodeRequestDto {

    private Long id;

    private String postCode;

    private Double latitude;

    private Double longitude;

}
