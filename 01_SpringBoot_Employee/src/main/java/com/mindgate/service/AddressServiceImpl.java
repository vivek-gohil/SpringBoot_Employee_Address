package com.mindgate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.dao.AddressDAOImpl;
import com.mindgate.pojo.Address;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDAOImpl addressDAO;

	@Override
	public boolean addNewAddress(Address address) {
		return addressDAO.addNewAddress(address);
	}

	@Override
	public boolean updateAddress(int addressId, Address address) {
		return addressDAO.updateAddress(addressId, address);
	}

	@Override
	public boolean deleteAddress(int addressId) {
		return addressDAO.deleteAddress(addressId);
	}

	@Override
	public Address getAddress(int addressId) {
		return addressDAO.getAddress(addressId);
	}

	@Override
	public List<Address> getAllAddresses() {
		return addressDAO.getAllAddresses();
	}

}
