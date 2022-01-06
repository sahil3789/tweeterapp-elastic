package com.tweeter.elastic.controller;

import com.tweeter.elastic.domain.PostData;
import com.tweeter.elastic.service.PostDataService;
import com.tweeter.elastic.domain.SearchPostList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/tweeter/post")
public class PostController {

    @Autowired
    private PostDataService postDataService;

    @PostMapping("/save")
    public ResponseEntity<PostData> save(@RequestBody PostData postData){
            return new ResponseEntity<>(postDataService.save(postData), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<String> remove(@PathVariable String postId){
            postDataService.remove(postId);
            return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @GetMapping("/search/{tag}")
    public ResponseEntity<SearchPostList> get(@PathVariable String tag){

        List<PostData> postDataList = postDataService.get(tag).get().collect(Collectors.toList());

        SearchPostList searchPostList = postDataService.getSearchList(postDataList);

        if(searchPostList==null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(searchPostList, HttpStatus.OK);

    }
}
