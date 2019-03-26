package com.postcodedistance.post.code.api.service.impl;

import com.postcodedistance.post.code.api.entity.PostCode;
import com.postcodedistance.post.code.api.repository.PostCodeRepository;
import com.postcodedistance.post.code.api.service.PostCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PostCodeServiceImpl implements PostCodeService {

    private PostCodeRepository postCodeRepository;

    @Autowired
    public void setClientRepository(PostCodeRepository postCodeRepository){
        this.postCodeRepository=postCodeRepository;
    }

    @Override
    public Optional<PostCode> findByPostCodeName(String postCodeName){
        return postCodeRepository.findByPostCodeName(postCodeName);
    }

    @Override
    public Optional<PostCode> findById (Long id){
        return postCodeRepository.findById(id);
    }

    @Override
    @Transactional
    public void save (PostCode postCode){
        postCodeRepository.save(postCode);
    }

    @Override
    public Page<PostCode> findAll(Pageable pageAndLimit){
        return postCodeRepository.findAll(pageAndLimit);
    }
}
