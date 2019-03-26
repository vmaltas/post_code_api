package com.postcodedistance.post.code.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostCodeDto {

    private Long id;

    private String postalCode;

    private Double latitude;

    private Double longitude;

}
