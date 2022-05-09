package com.cruznobre.rest.shared.util;

import lombok.Getter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Schema(name = "Link")
public class LinkBag {
    private final String uri;
    private final String rel;

    public LinkBag(String uri, String rel){
        this.uri = uri;
        this.rel = rel;
    }
}
