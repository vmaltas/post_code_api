package com.postcodedistance.post.code.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ListPostCodeResponseDto extends BaseResponseDto {

    private List<PostCodeDto> postCodeDtoList;
}
