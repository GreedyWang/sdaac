package ldapBiz;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.DirContext;

import app.entity.Tempolyee;
import app.entity.Tuser;

public interface ILdap {

	public  DirContext getConnect()throws IOException, NamingException;
	public String searchByFilter(String filter,String ou);

}
