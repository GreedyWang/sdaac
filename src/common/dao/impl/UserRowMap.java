package common.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import app.entity.vave.Count;

public class UserRowMap implements RowMapper {

	public Object mapRow(ResultSet rs, int index) throws SQLException {
		// TODO Auto-generated method stub
		/**
		 * OR mapping
		 */
		Count item=new Count();
		item.setDepartmentName(rs.getString(0));
		item.setProposalNum(rs.getInt(1));
		item.setFinishNum(rs.getInt(2));
		item.setDoNum(rs.getInt(3));
	//	item.setMoney(rs.getFloat(4));
		return item;
	}

}
