package com.sdaac.pr;

import java.util.List;

import org.jbpm.api.identity.Group;
import org.jbpm.api.identity.User;
import org.jbpm.pvm.internal.identity.spi.IdentitySession;



public class MyIdentityImpl implements IdentitySession {

	@Override
	public String createGroup(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createMembership(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String createUser(String arg0, String arg1, String arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteGroup(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMembership(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Group findGroupById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> findGroupsByUser(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Group> findGroupsByUserAndGroupType(String arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserById(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsersByGroup(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findUsersById(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
