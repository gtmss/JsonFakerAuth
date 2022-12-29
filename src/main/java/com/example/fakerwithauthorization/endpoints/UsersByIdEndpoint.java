package com.example.fakerwithauthorization.endpoints;

import com.example.fakerwithauthorization.generatedSOAP.GetUserByIdRequest;
import com.example.fakerwithauthorization.generatedSOAP.GetUserByIdResponse;
import com.example.fakerwithauthorization.generatedSOAP.SoapUsers;
import com.example.fakerwithauthorization.mapper.SimpleSourceDestinationMapper;
import com.example.fakerwithauthorization.models.Users;
import com.example.fakerwithauthorization.repository.UsersRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Optional;

@Endpoint
public class UsersByIdEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private UsersRepository usersRepository;

    @Autowired
    public UsersByIdEndpoint(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserByIdRequest")
    @ResponsePayload
    public GetUserByIdResponse getUserByIdResponse (@RequestPayload GetUserByIdRequest request) {

        SimpleSourceDestinationMapper mapper = Mappers.getMapper(SimpleSourceDestinationMapper.class);
        GetUserByIdResponse response = new GetUserByIdResponse();

        response.setUsers(mapper.usersToSoapUsers(usersRepository.findById(request.getId()).get()));

        return response;

    }
}
