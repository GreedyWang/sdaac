package app.biz;

import java.util.List;

import app.entity.Tpost;
import app.entity.TpostType;

public interface PostTypeBiz {
public  List<TpostType> doSelect();
public void doUpdate(TpostType item);
}
