package com.cruznobre.rest.shared.util;

import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;

@Getter
@Schema(name = "Paginable")
public class PaginableBag {
    private List<Object> items = new ArrayList<>();
    private List<LinkBag> links;
    private Long total;
    private int page;
    private int pageSize;

    private PaginableBag(){}

    public PaginableBag(
            List<Object> items,
            List<LinkBag> links,
            Long total,
            int page,
            int pageSize) {
        this.items = items;
        this.links = links;
        this.total = total;
        this.pageSize = pageSize;
        this.page = page;
    }


}
