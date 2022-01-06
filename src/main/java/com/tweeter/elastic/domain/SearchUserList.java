package com.tweeter.elastic.domain;

import java.util.List;

public class SearchUserList {

    List<UserData> userDataList;

    public List<UserData> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }
}
