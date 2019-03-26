package com.postcodedistance.post.code.api.repository;

import com.postcodedistance.post.code.api.entity.PostCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostCodeRepository extends JpaRepository<PostCode,Long> {

    Optional<PostCode> findByPostCodeName(String postCodeName);
}
