package com.teq.address;

public interface IAddressBuilder {
    public IAddressBuilder setId(int id);

    public IAddressBuilder setPostalCode(String postalCode);

    public IAddressBuilder setUnitNumber(int number);

    public IAddressBuilder setStreetNumber(int number);

    public IAddressBuilder setStreetName(String name);

    public IAddressBuilder setStreetType(String type);

    public IAddressBuilder setStreetDirection(String direction);

    public IAddressBuilder setCity(String city);

    public IAddressBuilder setProvince(String province);

    public Address create();
}
