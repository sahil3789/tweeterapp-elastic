package com.tweeter.elastic.domain;

import java.util.List;

public class SearchPostList {

    List<PostData> postDataList;

    public List<PostData> getPostDataList() {
        return postDataList;
    }

    public void setPostDataList(List<PostData> postDataList) {
        this.postDataList = postDataList;
    }
}
