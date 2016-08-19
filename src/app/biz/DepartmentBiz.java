package app.biz;

import java.util.List;

import app.entity.Tdepartment;
import app.entity.Tempolyee;

public interface DepartmentBiz {
public Object[] getDepartmentName();
public List<Tdepartment> getDepart(Integer id);
public List<Tdepartment> getAll();
public Tdepartment doSelectManagerByDepartID(Integer departmentID);
public String[] doSelectVaves(Integer departmentID);
public boolean isManager(String uid);
public List<Tempolyee>  SelectAllPM();
public List<Tdepartment> selectByManagerId(String uid);
public List<Tdepartment> getAll2();
}
