package com.boot.stickershop.util;

public class Pagination {
    private int totalPageCnt; // 총 페이지 수
    private int pageSize; // 한 화면에 보일 페이지 수
    private int totalCnt; // 총 게시물 수
    private int postSize; // 페이지 당 게시물 수
    private int startIdx; // 페이지 당 시작 게시물 인덱스
    private int endIdx; // 페이지 당 마지막 게시물 인덱스
    private int startPage;
    private int endPage;
    private int page;

    public Pagination(int totalCnt, int postSize, int page, int pageSize) {
        this.pageSize = pageSize;
        this.totalCnt = totalCnt;
        this.postSize = postSize;
        this.page = page;
        init();
    }

    public void init(){
        this.totalPageCnt = (int) Math.ceil(totalCnt*1.0/postSize);
        this.startPage = (page/(pageSize+1))*pageSize+1;
        this.endPage = startPage+pageSize-1;
        if(endPage > totalPageCnt){
            endPage = totalPageCnt;
        }
        this.startIdx = (this.page-1)*postSize;
        this.endIdx = startIdx + postSize-1;
        if(endIdx >= totalCnt){
            endIdx = totalCnt-1;
        }
    }

    public boolean hasPrev() {
        return (startPage-1) > 0;
    }

    public boolean hasNext() {
        return (endPage+1) <= totalPageCnt;
    }

    public int getTotalPageCnt() {
        return totalPageCnt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public int getPostSize() {
        return postSize;
    }

    public int getStartIdx() {
        return startIdx;
    }

    public int getEndIdx() {
        return endIdx;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "totalPageCnt=" + totalPageCnt +
                ", pageSize=" + pageSize +
                ", totalCnt=" + totalCnt +
                ", postSize=" + postSize +
                ", startIdx=" + startIdx +
                ", endIdx=" + endIdx +
                ", startPage=" + startPage +
                ", endPage=" + endPage +
                ", page=" + page +
                '}';
    }
}