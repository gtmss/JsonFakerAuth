package com.example.fakerwithauthorization.endpoints;

import com.example.fakerwithauthorization.generatedSOAP.GetUsersRequest;
import com.example.fakerwithauthorization.generatedSOAP.GetUsersResponse;
import com.example.fakerwithauthorization.generatedSOAP.SoapUsers;
import com.example.fakerwithauthorization.mapper.SimpleSourceDestinationMapper;
import com.example.fakerwithauthorization.models.Users;
import com.example.fakerwithauthorization.repository.UsersRepository;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
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

        SimpleSourceDestinationMapper mapper = Mappers.getMapper(SimpleSourceDestinationMapper.class);
        logger.debug("SOAP User: ", usersRepository.findAll());
        GetUsersResponse response = new GetUsersResponse();

        List<SoapUsers> soapUsersList = new ArrayList<>();
        List<Users> usersList = usersRepository.findAll();

        usersList.forEach(users -> soapUsersList.add(mapper.usersToSoapUsers(users)));

        response.setUsers(soapUsersList);

        return response;
    }


}
