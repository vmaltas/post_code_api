package com.postcodedistance.post.code.api.service;

import com.postcodedistance.post.code.api.entity.PostCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface PostCodeService {

    Optional<PostCode> findByPostCodeName(String postCodeName);

    Optional<PostCode> findById (Long id);

    void save (PostCode postCode);

    Page<PostCode> findAll(Pageable pageAndLimit);


}
