package com.mindgate.dao;

import java.util.List;

import com.mindgate.pojo.Address;

public interface AddressDAO {
	public boolean addNewAddress(Address address);

	public boolean updateAddress(int addressId, Address address);

	public boolean deleteAddress(int addressId);

	public Address getAddress(int addressId);

	public List<Address> getAllAddresses();
}
