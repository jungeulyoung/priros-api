package com.lnt.priros.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Paging {
    private int linePerPage; // 한페이지에 나오는 사건 수
    private int listPerPage; // 리스트번호 버튼 수
    private int totalCount;
    private int pageNo;
    private String act;
    private String cmd;
    private int totalPage;
    private int numPageGroup;
    private int lastPageGroup;
    private int startPage;
    private int endPage;
    private int prevPageGroup;
    private int nextPageGroup;
    private int startPerPage;
    private int start;
    private int limit;

    public Paging(int pageNo, int totalCount) {
        this.pageNo = pageNo;
        this.totalCount = totalCount;
        this.linePerPage = 5;
        this.listPerPage = 5;
        paging();
    }

    public Paging(int pageNo, int totalCount, int linePerPage, int listPerPage) {
        this.pageNo = pageNo;
        this.totalCount = totalCount;
        this.linePerPage = linePerPage;
        this.listPerPage = listPerPage;
        paging();
    }

    public void paging() {
        pageNo = Math.max(pageNo, 1);

        totalPage = (int) Math.ceil((double) totalCount / (double) linePerPage);

        totalPage = Math.max(totalPage, 1);
        pageNo = Math.min(pageNo, totalPage);

        numPageGroup = (int) Math.ceil((double) pageNo / listPerPage);
        lastPageGroup = (int) Math.ceil((double) totalPage / listPerPage);

        startPage = (numPageGroup - 1) * listPerPage + 1;
        startPage = Math.max(startPage, 1);

        endPage = startPage + listPerPage - 1;
        endPage = Math.max(endPage, 1);
        endPage = Math.min(endPage, totalPage);

        nextPageGroup = endPage == totalPage ? endPage : endPage + 1;
        prevPageGroup = startPage == 1 ? 1 : startPage - 1;

        startPerPage = totalCount - ((pageNo - 1) * linePerPage);

        if (pageNo == 1) {
            start = 0;
        } else {
            start = (pageNo - 1) * linePerPage;
        }
        limit = linePerPage;
    }
}
