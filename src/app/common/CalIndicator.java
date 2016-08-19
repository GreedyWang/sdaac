package app.common;

import app.entity.TempIndex;
import app.entity.TcompanyIndex;

public class CalIndicator {
	public static Float cal(TempIndex item)
	{
		Float[] type_abc=new Float[3];
		
		Float output=0.0f;
			try {
				output=Float.parseFloat(item.getAct_output());
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
		Float score=0.0f;	
		try {
			type_abc[0]=Float.parseFloat(item.getIndex().getA());
			type_abc[1]=Float.parseFloat(item.getIndex().getB());
			type_abc[2]=Float.parseFloat(item.getIndex().getC());
			if(output<type_abc[0])
			{			
				score=0.0f;
			}
			if(output>=type_abc[0]&&output<type_abc[1])
			{			
				score=(output-type_abc[0])*(30/(type_abc[0]-type_abc[1]))+100;
			}
			if(output>=type_abc[1]&&output<=type_abc[2])
			{			
				score=((output-type_abc[2])*(50/(type_abc[2]-type_abc[1]))+200);
			}
		} catch (NumberFormatException e) {
			score=0f;
		}
		return score*item.getPercentage();
	}
	public static Float cal(TcompanyIndex item)
	{
		Float[] type_abc=new Float[3];
		Float output=item.getAct_output();
		Float score=0.0f;	
		try {
			type_abc[0]=Float.parseFloat(item.getTindexTarget().getA());
			type_abc[1]=Float.parseFloat(item.getTindexTarget().getB());
			type_abc[2]=Float.parseFloat(item.getTindexTarget().getC());
			if(output<type_abc[0])
			{			
				score=0.0f;
			}
			if(output>=type_abc[0]&&output<type_abc[1])
			{			
				score=(output-type_abc[0])*(30/(type_abc[0]-type_abc[1]))+100;
			}
			if(output>=type_abc[1]&&output<=type_abc[2])
			{			
				score=((output-type_abc[2])*(50/(type_abc[2]-type_abc[1]))+200);
			}
		} catch (NumberFormatException e) {
			score=0f;
		}
		return score;
	}
}
