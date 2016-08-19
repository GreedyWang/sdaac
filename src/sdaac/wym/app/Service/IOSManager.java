package sdaac.wym.app.Service;

import java.util.List;

import app.entity.Tempolyee;

import sdaac.wym.app.entity.hr.OrganizationStructure;

public interface IOSManager {
	public  List<Integer[]>  getPostion();
	public List<String[]> getName();
	public Integer add(OrganizationStructure item);
	public boolean update(OrganizationStructure item);
	public Integer[] selectEmpsByUid(String uid);
}
