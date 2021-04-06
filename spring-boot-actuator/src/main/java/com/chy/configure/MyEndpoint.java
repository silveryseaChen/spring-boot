package com.chy.configure;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * my end point
 * Created by chy on 21/4/6.
 */
@Component
@Endpoint(id = "myEndpoint")
public class MyEndpoint {

    private List<String> endpointList = Collections.emptyList();

    @ReadOperation
    public List<String> get(){
        return endpointList;
    }

    @WriteOperation
    public boolean add(String endpoint){
        return endpointList.add(endpoint);
    }

    @DeleteOperation
    public boolean delete(String endpoint){
        return endpointList.remove(endpoint);
    }

}
