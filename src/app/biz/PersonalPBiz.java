package app.biz;
import java.util.List;
import java.util.Map;

import sdaac.wym.app.entity.hr.BounceFigure;
import common.util.Page;
import app.entity.IP;
import app.entity.TemployeeProduct;
import app.entity.Test;

public interface PersonalPBiz {
public void doInsert(TemployeeProduct item);//�Ǽ�Ա������״��
public List<Test> selectAll(String myDate,String endTime,String uids,Page page);//�����λ���ￄ1�7
public List<TemployeeProduct> getPostSalary();//�����λ���ￄ1�7
public Map<String ,List<IP>> insertIP(String myDate) ;//ji suan ge ren ji xiao
public void FlushInsert(List list);
public List<TemployeeProduct> doSelectDailyRecord(boolean flage,TemployeeProduct item,String beginTime,String endTime,Page page);
public void doUpdate(TemployeeProduct item);
public void delete(Integer id);
public TemployeeProduct doSelectByid(int id);
public List<Object[]> selectEachTypeTime(String myDate);
//public List<TemployeeProduct> selectFigureAllTime(TemployeeProduct item);
//public void selectFigureAllTime(TemployeeProduct item,List<BounceFigure> BounceFigures);
public Float[] selectFigureAllTime(TemployeeProduct item,BounceFigure pBf,List<BounceFigure> BounceFigures);
public void test();
public List<TemployeeProduct> getLast(String teamLeaderId);
}
