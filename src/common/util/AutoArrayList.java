package common.util;

import java.util.ArrayList;
public class AutoArrayList extends ArrayList {
    private Class itemClass;
    public AutoArrayList(Class itemClass) {
        this.itemClass = itemClass;
    }
    public Object get(int index) {
        try {
        	//if(index<0)index*=-1;
            while (index >= size()) {
                add(itemClass.newInstance());
            }
        } catch (Exception e) {
            e.printStackTrace();	
        }
       
        return super.get(index);
        //return null;
    }
}

