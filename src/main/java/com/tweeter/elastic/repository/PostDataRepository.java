package com.tweeter.elastic.repository;

import com.tweeter.elastic.domain.PostData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostDataRepository extends ElasticsearchRepository<PostData, Long> {

    @Query("{\"match\":{\"tag\":{\"query\":\"abc\"}}}")
    Page<PostData> findAllByTag(String tag, Pageable pageable);

    @Query("{\"match\":{\"postId\":{\"query\":\1}}}")
    void deleteByPostId(String bookId);

}
