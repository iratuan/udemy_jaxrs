package com.cruznobre.rest.shared.util;

import java.util.ArrayList;
import java.util.List;

public class PaginableBuilder {
        private List<Object> items = new ArrayList<>();
        private List<LinkBag> links;
        private Long total;
        private int page;
        private int pageSize;

        public PaginableBuilder(List<Object> items) {
            this.items = (List<Object>) items.get(0);
        }

        public PaginableBuilder withLinks(List<LinkBag> links){
            this.links = links;
            return  this;
        }

        public PaginableBuilder withTotalOfRecords(Long total){
            this.total = total;
            return  this;
        }

        public PaginableBuilder inPage(int page){
            this.page = page;
            return  this;
        }

        public PaginableBuilder withPageSize(int pageSize){
            this.pageSize = pageSize;
            return  this;
        }

        public PaginableBag build(){
            return new PaginableBag(
                    items,
                    links,
                    total,
                    page,
                    pageSize);
        }
    }