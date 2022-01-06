package com.tweeter.elastic.service;

import com.tweeter.elastic.domain.SearchUserList;
import com.tweeter.elastic.domain.UserData;
import com.tweeter.elastic.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDataService {

    @Autowired
    private UserDataRepository userDataRepository;

    public UserData save(UserData userData){

        return userDataRepository.save(userData);
    }

    public void remove(String userId){

        userDataRepository.deleteByUserId(userId);
    }

    public Page<UserData> get(String name){

        Pageable pageable = PageRequest.of(0, 100);
        return userDataRepository.findAllByName(name, pageable);
    }

    public SearchUserList getSearchList(List<UserData> userDataList){

        SearchUserList searchUserLists = new SearchUserList();

        try {
            for (UserData user : userDataList) {
                if (searchUserLists.getUserDataList() == null) {
                    List<UserData> userData = new ArrayList<>();
                    userData.add(user);
                    searchUserLists.setUserDataList(userData);
                } else {
                    searchUserLists.getUserDataList().add(user);
                }
            }
            return searchUserLists;
        }
        catch (Exception e){
            return null;
        }
    }

}
