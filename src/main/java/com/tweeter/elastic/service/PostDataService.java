package com.tweeter.elastic.service;

import com.tweeter.elastic.domain.PostData;
import com.tweeter.elastic.repository.PostDataRepository;
import com.tweeter.elastic.domain.SearchPostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostDataService {

    @Autowired
    private PostDataRepository postDataRepository;

    public PostData save(PostData postData){
       return postDataRepository.save(postData);
    }

    public void remove(String postId){
         postDataRepository.deleteByPostId(postId);
    }

    public Page<PostData> get(String tag){

        Pageable pageable = PageRequest.of(0, 10);
        return postDataRepository.findAllByTag(tag, pageable);
    }

    public SearchPostList getSearchList(List<PostData> postDataList){

        SearchPostList searchPostLists = new SearchPostList();

        try {
            for (PostData post : postDataList) {
                if (searchPostLists.getPostDataList() == null) {
                    List<PostData> postData = new ArrayList<>();
                    postData.add(post);
                    searchPostLists.setPostDataList(postData);
                } else {
                    searchPostLists.getPostDataList().add(post);
                }
            }
            return searchPostLists;
        }
        catch (Exception e){
            return null;
        }
    }
}
