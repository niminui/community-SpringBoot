package com.nmh.community_nmh.dto;

import lombok.Data;

import java.util.List;

/**
 * @author niminui
 * @date 2020/3/7 14:57
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private Boolean hasPreviousPage;
    private Boolean showFirstPage;
    private Boolean showEndPage;
    private Boolean hasNextPage;

    private Integer currentPage;
    private List<Integer> pages;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        Integer totalPage = 0;
        if(totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        //是否展示上一页
        if(page == 1) {
            hasPreviousPage = false;
        } else {
            hasPreviousPage =true;
        }

        //是否展示下一页
        if(page == (totalPage)) {
            hasNextPage = false;
        } else {
            hasNextPage = true;
        }

        //是否展示第一页
        if(pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
