package com.cruznobre.rest.shared.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaginableBuilder {
        private List<Object> resources = new ArrayList<>();
        private List<LinkBag> links;
        private Long total;
        private int page;
        private int pageSize;

        public PaginableBuilder(List<Object> resources) {
            this.resources = resources;
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
                    Collections.singletonList(resources),
                    links,
                    total,
                    pageSize,
                    page);
        }
    }