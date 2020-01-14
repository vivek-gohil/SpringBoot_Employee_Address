package com.mindgate.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mindgate.pojo.Address;

@Repository
public class AddressDAOImpl implements AddressDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String sql;
	private int count;

	@Override
	public boolean addNewAddress(Address address) {
		sql = "insert into address_master values(address_sequence.nextVal,?,?,?,?,?)";
		Object[] args = new Object[] { address.getAddressLineOne(), address.getAddressLineTwo(), address.getCity(),
				address.getState(), address.getCountry() };

		count = jdbcTemplate.update(sql, args);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateAddress(int addressId, Address address) {
		sql = "update address_master set address_line_one=?,address_line_two=?,city=?,state=?,country=? where address_id=?";
		Object[] args = new Object[] { address.getAddressLineOne(), address.getAddressLineTwo(), address.getCity(),
				address.getState(), address.getCountry(), addressId };

		count = jdbcTemplate.update(sql, args);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteAddress(int addressId) {
		sql = "delete from address_master where address_id=?";
		Object[] args = new Object[] { addressId };

		count = jdbcTemplate.update(sql, args);
		if (count > 0)
			return true;
		return false;
	}

	@Override
	public Address getAddress(int addressId) {
		sql = "select * from address_master where address_id=?";
		Object[] args = new Object[] { addressId };
		Address address = jdbcTemplate.queryForObject(sql, args, new AddressRowMapper());
		return address;
	}

	@Override
	public List<Address> getAllAddresses() {
		sql = "select * from address_master";
		List<Address> addressList = new ArrayList<Address>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);

		for (Map<String, Object> map : rows) {
			Address address = new Address();
			address.setAddressId(Integer.valueOf(map.get("address_id").toString()));
			address.setAddressLineOne(map.get("address_line_one").toString());
			address.setAddressLineTwo(map.get("address_line_Two").toString());
			address.setCity(map.get("city").toString());
			address.setState(map.get("state").toString());
			address.setCountry(map.get("country").toString());
			addressList.add(address);
		}
		return addressList;
	}

	class AddressRowMapper implements RowMapper<Address> {

		@Override
		public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
			Address address = new Address();
			address.setAddressId(rs.getInt("address_id"));
			address.setAddressLineOne(rs.getString("address_line_one"));
			address.setAddressLineTwo(rs.getString("address_line_two"));
			address.setCity(rs.getString("city"));
			address.setState(rs.getString("state"));
			address.setCountry(rs.getString("country"));
			return address;
		}

	}

}
