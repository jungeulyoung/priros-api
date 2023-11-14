package com.lnt.priros.model.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class SearchResult<T> {
    List<T> list;
    Paging paging;

    public static <E> SearchResult<E> create(int pageNo, int totCnt) {
        SearchResult<E> searchResult = new SearchResult<>();
        searchResult.paging = new Paging(pageNo, totCnt);
        if (totCnt == 0)
            searchResult.list = new ArrayList<>();
        return searchResult;    
    }

    public SearchResult<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public static <E> SearchResult<E> create(List<E> list) {
        SearchResult<E> searchResult = new SearchResult<>();
        searchResult.list = list;
        return searchResult;    
    }
}
