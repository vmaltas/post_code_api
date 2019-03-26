package com.postcodedistance.post.code.api.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "post_code")
public class PostCode {


    @Id
    @Column(name = "id")
    @NotNull
    @Min(value = 1)
    private Long id;


    @Column(name = "postcode")
    @NotNull
    private String postCodeName;


    @Column(name = "latitude")
    @NotNull
    private Double latitude;


    @Column(name = "longitude")
    @NotNull
    private Double longitude;

}
