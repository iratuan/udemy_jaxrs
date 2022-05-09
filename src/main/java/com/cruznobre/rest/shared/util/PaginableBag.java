package com.cruznobre.rest.shared.util;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PaginableBag {
    private List<Object> resources = new ArrayList<>();
    private List<LinkBag> links;
    private Long total;
    private int page;
    private int pageSize;

    public PaginableBag(
            List<Object> resources,
            List<LinkBag> links,
            Long total,
            int page,
            int pageSize){
        this.resources = resources;
        this.links = links;
        this.total = total;
        this.pageSize = pageSize;
        this.page = page;
    }
}
