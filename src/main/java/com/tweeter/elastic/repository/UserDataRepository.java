package com.tweeter.elastic.repository;

import com.tweeter.elastic.domain.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDataRepository extends ElasticsearchRepository<UserData, Long> {


    @Query("{\"match\":{\"name\":{\"query\":\"abc\"}}}")
    Page<UserData> findAllByName(String userName, Pageable pageable);

    @Query("{\"match\":{\"userId\":{\"query\":\1}}}")
    void deleteByUserId(String bookId);
}
