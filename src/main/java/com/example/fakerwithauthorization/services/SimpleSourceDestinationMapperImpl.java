package com.example.fakerwithauthorization.services;

import com.example.fakerwithauthorization.generatedSOAP.Geo;
import com.example.fakerwithauthorization.generatedSOAP.SoapUsers;
import com.example.fakerwithauthorization.mapper.SimpleSourceDestinationMapper;
import com.example.fakerwithauthorization.models.Address;
import com.example.fakerwithauthorization.models.Company;
import com.example.fakerwithauthorization.models.Users;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-12-29T11:09:04+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
public class SimpleSourceDestinationMapperImpl implements SimpleSourceDestinationMapper {

    @Override
    public SoapUsers usersToSoapUsers(Users users) {
        if ( users == null ) {
            return null;
        }

        SoapUsers soapUsers = new SoapUsers();

        if ( users.getId() != null ) {
            soapUsers.setId( users.getId() );
        }
        soapUsers.setName( users.getName() );
        soapUsers.setUsername( users.getUsername() );
        soapUsers.setEmail( users.getEmail() );
        soapUsers.setPhone( users.getPhone() );
        soapUsers.setWebsite( users.getWebsite() );
        soapUsers.setAddress( addressToAddress( users.getAddress() ) );
        soapUsers.setCompany( companyToCompany( users.getCompany() ) );

        return soapUsers;
    }

    @Override
    public Users soapUsersToUsers(SoapUsers soapUsers) {
        if ( soapUsers == null ) {
            return null;
        }

        Users users = new Users();

        users.setId( soapUsers.getId() );
        users.setName( soapUsers.getName() );
        users.setUsername( soapUsers.getUsername() );
        users.setEmail( soapUsers.getEmail() );
        users.setPhone( soapUsers.getPhone() );
        users.setWebsite( soapUsers.getWebsite() );
        users.setAddress( addressToAddress1( soapUsers.getAddress() ) );
        users.setCompany( companyToCompany1( soapUsers.getCompany() ) );

        return users;
    }

    protected Geo geoToGeo(com.example.fakerwithauthorization.models.Geo geo) {
        if ( geo == null ) {
            return null;
        }

        Geo geo1 = new Geo();

        if ( geo.getId() != null ) {
            geo1.setId( geo.getId() );
        }
        if ( geo.getLat() != null ) {
            geo1.setLat( geo.getLat() );
        }
        if ( geo.getLng() != null ) {
            geo1.setLng( geo.getLng() );
        }

        return geo1;
    }

    protected com.example.fakerwithauthorization.generatedSOAP.Address addressToAddress(Address address) {
        if ( address == null ) {
            return null;
        }

        com.example.fakerwithauthorization.generatedSOAP.Address address1 = new com.example.fakerwithauthorization.generatedSOAP.Address();

        if ( address.getId() != null ) {
            address1.setId( address.getId() );
        }
        address1.setStreet( address.getStreet() );
        address1.setSuite( address.getSuite() );
        address1.setCity( address.getCity() );
        address1.setZipcode( address.getZipcode() );
        address1.setGeo( geoToGeo( address.getGeo() ) );

        return address1;
    }

    protected com.example.fakerwithauthorization.generatedSOAP.Company companyToCompany(Company company) {
        if ( company == null ) {
            return null;
        }

        com.example.fakerwithauthorization.generatedSOAP.Company company1 = new com.example.fakerwithauthorization.generatedSOAP.Company();

        if ( company.getId() != null ) {
            company1.setId( company.getId() );
        }
        company1.setName( company.getName() );
        company1.setBs( company.getBs() );

        return company1;
    }

    protected com.example.fakerwithauthorization.models.Geo geoToGeo1(Geo geo) {
        if ( geo == null ) {
            return null;
        }

        com.example.fakerwithauthorization.models.Geo geo1 = new com.example.fakerwithauthorization.models.Geo();

        geo1.setId( geo.getId() );
        geo1.setLat( geo.getLat() );
        geo1.setLng( geo.getLng() );

        return geo1;
    }

    protected Address addressToAddress1(com.example.fakerwithauthorization.generatedSOAP.Address address) {
        if ( address == null ) {
            return null;
        }

        Address address1 = new Address();

        address1.setStreet( address.getStreet() );
        address1.setSuite( address.getSuite() );
        address1.setCity( address.getCity() );
        address1.setZipcode( address.getZipcode() );
        address1.setGeo( geoToGeo1( address.getGeo() ) );
        address1.setId( address.getId() );

        return address1;
    }

    protected Company companyToCompany1(com.example.fakerwithauthorization.generatedSOAP.Company company) {
        if ( company == null ) {
            return null;
        }

        Company company1 = new Company();

        company1.setId( company.getId() );
        company1.setName( company.getName() );
        company1.setBs( company.getBs() );

        return company1;
    }
}
