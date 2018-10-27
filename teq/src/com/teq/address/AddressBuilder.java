package com.teq.address;

public class AddressBuilder implements IAddressBuilder {
    
    private Address address;
    
    public AddressBuilder() {
        address = new Address();
    }

    @Override
    public IAddressBuilder setId(int id) {
        address.id = id;
        return this;
    }

    @Override
    public IAddressBuilder setPostalCode(String postalCode) {
        address.postalCode = postalCode;
        return this;
    }

    @Override
    public IAddressBuilder setStreetNumber(int number) {
        address.streetNumber = number;
        return this;
    }

    @Override
    public IAddressBuilder setStreetName(String name) {
        address.streetName = name;
        return this;
    }

    @Override
    public IAddressBuilder setStreetDirection(String direction) {
        address.streetDirection = direction;
        return this;
    }

    @Override
    public IAddressBuilder setCity(String city) {
        address.city = city;
        return this;
    }

    @Override
    public IAddressBuilder setProvince(String province) {
        address.province = province;
        return this;
    }

    @Override
    public Address create() {
        return address;
    }
    
}
