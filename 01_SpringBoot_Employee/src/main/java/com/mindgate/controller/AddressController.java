package com.mindgate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.pojo.Address;
import com.mindgate.service.AddressServiceImpl;

@RestController
@RequestMapping("/employee/address")
public class AddressController {

	@Autowired
	private AddressServiceImpl addressService;

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public boolean addNewAddress(@RequestBody Address address) {
		return addressService.addNewAddress(address);
	}

	@RequestMapping(value = "/{addressId}", method = RequestMethod.PUT)
	public boolean updateAddress(@PathVariable("addressId") int addressId, @RequestBody Address address) {
		return addressService.updateAddress(addressId, address);
	}

	@RequestMapping(value = "/{addressId}", method = RequestMethod.DELETE)
	public boolean deleteAddress(@PathVariable("addressId") int addressId) {
		return addressService.deleteAddress(addressId);
	}

	@RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
	public Address getAddress(@PathVariable("addressId") int addressId) {
		return addressService.getAddress(addressId);
	}

	@RequestMapping(value = "/getAllAddresses", method = RequestMethod.GET)
	public List<Address> getAddresses() {
		return addressService.getAllAddresses();
	}
}
