package com.mindgate.service;

import java.util.List;

import com.mindgate.pojo.Address;

public interface AddressService {
	public boolean addNewAddress(Address address);

	public boolean updateAddress(int addressId, Address address);

	public boolean deleteAddress(int addressId);

	public Address getAddress(int addressId);

	public List<Address> getAllAddresses();
}
