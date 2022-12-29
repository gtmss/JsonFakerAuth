package com.example.fakerwithauthorization.mapper;

import com.example.fakerwithauthorization.generatedSOAP.SoapUsers;
import com.example.fakerwithauthorization.models.Users;
import org.mapstruct.Mapper;

@Mapper
public interface SimpleSourceDestinationMapper {
    SoapUsers usersToSoapUsers (Users users);
    Users soapUsersToUsers (SoapUsers soapUsers);
}
