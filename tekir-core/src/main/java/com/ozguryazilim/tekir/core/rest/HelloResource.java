package com.ozguryazilim.tekir.core.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("hello")
public class HelloResource {

    @GET
    public String getMessage() {
        return "hello";
    }
}
