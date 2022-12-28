package com.example.fakerwithauthorization.endpoints;

import com.example.fakerwithauthorization.models.Users;
import com.example.fakerwithauthorization.repository.UsersRepository;
import io.spring.guides.gs_producing_web_service.GetUsersRequest;
import io.spring.guides.gs_producing_web_service.GetUsersResponse;
import io.spring.guides.gs_producing_web_service.Users1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.LinkedList;
import java.util.List;

@Endpoint
public class SoapUsersEndpoint {

    Logger logger = LoggerFactory.getLogger(SoapUsersEndpoint.class);

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private UsersRepository usersRepository;

    @Autowired
    public SoapUsersEndpoint(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsersRequest")
    @ResponsePayload
    public GetUsersResponse getUsersResponse (@RequestPayload GetUsersRequest request) {
        logger.debug("SOAP User: ", usersRepository.findAll());

        List<Users> users = usersRepository.findAll();
        GetUsersResponse response = new GetUsersResponse();
        List<Users1> soapUsers = new LinkedList<>();



        return response;
    }


}
