package app.biz;

import java.util.List;

import app.entity.Test;
import app.entity.Tuser;

public interface UserBiz {
public Tuser checkUser(String uid);//����ݿ����û�,�ٰ�Ȩ�޵���Ϣ��ӽ�ȥ
public void doInsert(String uid,int rights);
}
