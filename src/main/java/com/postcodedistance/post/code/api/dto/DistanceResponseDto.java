package com.postcodedistance.post.code.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DistanceResponseDto extends BaseResponseDto {

    private PostCodeDto firstUkPostalCode;

    private PostCodeDto secondUkPostalCode;

    private Double distance;

    private String distanceUnit;
}
