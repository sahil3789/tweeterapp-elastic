package com.tweeter.elastic.controller;

import com.tweeter.elastic.service.UserDataService;
import com.tweeter.elastic.domain.SearchUserList;
import com.tweeter.elastic.domain.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/tweeter/user")
public class UserController {

    @Autowired
    private UserDataService userDataService;

    @PostMapping("/save")
    public ResponseEntity<UserData> save(@RequestBody UserData userData){
        return new ResponseEntity<>(userDataService.save(userData), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> remove(@PathVariable String userId){
        userDataService.remove(userId);
        return new ResponseEntity<>("deleted", HttpStatus.OK);
    }

    @GetMapping("/search/{userName}")
    public ResponseEntity<SearchUserList> get(@PathVariable String userName){

        List<UserData> userDataList = userDataService.get(userName).get().collect(Collectors.toList());

        SearchUserList searchUserList = userDataService.getSearchList(userDataList);

        if(searchUserList==null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(searchUserList, HttpStatus.OK);
    }

}
