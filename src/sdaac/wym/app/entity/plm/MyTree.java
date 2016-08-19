package sdaac.wym.app.entity.plm;

import java.io.PrintWriter;
import java.util.List;

public class MyTree<T> {
	private T node;
	private List<MyTree<T>> children;
	public T getNode() {
		return node;
	}
	public void setNode(T node) {
		this.node = node;
	}
	public List<MyTree<T>> getChildren() {
		return children;
	}
	public void setChildren(List<MyTree<T>> children) {
		this.children = children;
	}
	String blank="";
	String level="1";
	private Integer j=1;
	private String kind;
	public MyTree()
	{
		
	}
	public MyTree(String kind)
	{
		this.kind=kind;
	}
	public void show2(MyTree<Part> item,PrintWriter out)
	{

		if(item.getNode()!=null)
		{
			item.getNode().setLevel(level);
			
			out.println("<a style='font-size: 15' target='detail' href='plm.do?actionType=showDetail&kind="+kind+"&mpartId="+item.getNode().getId()+"'>"+item.getNode().getLevel()+":"+item.getNode().getName()+"/"+item.getNode().getNo()+"——"+item.getNode().getQuant()+"</a><br>");
		}else{
			out.println("top<br>");
		}
		
		if(item.getChildren()!=null)
		{
			blank+="&nbsp&nbsp&nbsp&nbsp";
			
			
			level=item.getNode().getLevel()+","+j;
			
			for(int k=0;k<item.getChildren().size();k++)
			{
				if(k==0)
				{
					j=0;
				}
				out.print("<label>"+blank+"|__</label>");
				j++;
				level=level.substring(0, level.length()-level.split(",")[level.split(",").length-1].length()-1);
				level+=","+j;
				item.getNode().setLevel(level);
				show2(item.getChildren().get(k), out);
			}
			blank=blank.substring(0, blank.length()-20);
			level=level.substring(0, level.length()-level.split(",")[level.split(",").length-1].length()-1);
			
			j=Integer.parseInt(level.split(",")[level.split(",").length-1]  );
			
		}
		else
		{
			//j++;
			if(level.length()>2)
			{
				level=level.substring(0, level.length()-level.split(",")[level.split(",").length-1].length()-1);
				level+=","+j;
			//	System.out.println(level);
				item.getNode().setLevel(level);
			}				
		}
	}
	
//	public void show22(MyTree<Epart> item,PrintWriter out)
//	{
//
//		if(item.getNode()!=null)
//		{
//			item.getNode().setLevel(level);
//			out.println("<a target='detail' href='plm.do?actionType=showDetail&kind=E&mpartId="+item.getNode().getId()+"'>"+item.getNode().getLevel()+":"+item.getNode().getName()+"</a><br>");
//		}else{
//			out.println("top<br>");
//		}
//		
//		if(item.getChildren()!=null)
//		{
//			blank+="&nbsp&nbsp&nbsp&nbsp";					
//			level=item.getNode().getLevel()+","+j;
//			
//			for(int k=0;k<item.getChildren().size();k++)
//			{
//				if(k==0)
//				{
//					j=0;
//				}
//				out.print("<label>"+blank+"|__</label>");
//				j++;
//				level=level.substring(0, level.length()-level.split(",")[level.split(",").length-1].length()-1);
//				level+=","+j;
//				item.getNode().setLevel(level);
//				show22(item.getChildren().get(k), out);
//			}
//			blank=blank.substring(0, blank.length()-20);
//			level=level.substring(0, level.length()-level.split(",")[level.split(",").length-1].length()-1);	
//			j=Integer.parseInt(level.substring(level.length()-1, level.length()));
//			
//		}
//		else
//		{
//			//j++;
//			if(level.length()>2)
//			{
//				level=level.substring(0, level.length()-level.split(",")[level.split(",").length-1].length()-1);
//				level+=","+j;
//				item.getNode().setLevel(level);
//			}
//			
//			//j=0;o
//		}
//	}

}
