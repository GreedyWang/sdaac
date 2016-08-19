package app.common;

import sdaac.wym.app.entity.plm.Bom;
import sdaac.wym.app.entity.plm.Epart;
import sdaac.wym.app.entity.plm.MPart;
import sdaac.wym.app.entity.plm.MyTree;
import sdaac.wym.app.entity.plm.Part;
import common.util.WritetoExcel;

public class PlmExcel extends WritetoExcel {

	public PlmExcel(String outputFile) throws Exception {
		super(outputFile);
		// TODO Auto-generated constructor stub
	}

	protected String level = "1";
	protected Integer j = 1;

	private void setTitle() {
		createNewSheet("PLM");
		String[] title = { "级号", "客户号", "零件号", "图号", "零件名（中文", "零件名（英文)", "数量",
				"单位", "材料规格", "材料名称", "供应尺寸（mm）", "可制坯料数", "工艺定额", "工艺责任组",
				"生产责任车间", "外购外协供应商", "零件状态", "备注" };
		creatNewRow(title);
	}

	protected void writeDetails(MyTree<Bom> item) {
		if (item.getNode() != null) {
			item.getNode().getCpart().setLevel(level);
			Part d = item.getNode().getCpart();
			// out.println("<a target='detail'
			// href='plm.do?actionType=showDetail&mpartId="+item.getNode().getId()+"'>"+item.getNode().getLevel()+":"+item.getNode().getName()+"</a><br>");
			String[] temp = { d.getLevel(), d.getCustomerID(), d.getNo(),
					d.getDrawingNO(), d.getName(), d.getEname(), item.getNode().getBnum()+"",
					d.getUnit(), d.getSpecificationNO(),
					d.getSpecificationName(), d.getSupplyDimension(), "",
					d.getGYDE(), "", "", d.getSupply(), d.getSource(), "" };
			creatNewRow(temp);
		}

		if (item.getChildren() != null) {
			level = item.getNode().getCpart().getLevel() + "," + j;

			for (int k = 0; k < item.getChildren().size(); k++) {
				if (k == 0) {
					j = 0;
				}
				j++;
				level = level.substring(0, level.length()
						- level.split(",")[level.split(",").length - 1]
								.length() - 1);
				level += "," + j;
				item.getNode().getCpart().setLevel(level);
				writeDetails(item.getChildren().get(k));
			}
			level = level.substring(0, level.length()
					- level.split(",")[level.split(",").length - 1].length()
					- 1);
			j = Integer.parseInt(level.split(",")[level.split(",").length - 1]);

		} else {
			// j++;
			if (level.length() > 2) {
				// level=level.substring(0, level.length()-2);
				level = level.substring(0, level.length()
						- level.split(",")[level.split(",").length - 1]
								.length() - 1);
				level += "," + j;
				item.getNode().getCpart().setLevel(level);
			}

		}
	}

	public void writeToExcel(MyTree<Bom> item) {
		setTitle();
		writeDetails(item);
		close();
	}

	// private void writeMpart(MyTree<MPart> item)
	// {
	// if(item.getNode()!=null)
	// {
	// item.getNode().setLevel(level);
	// MPart d=item.getNode();
	// //out.println("<a target='detail'
	// href='plm.do?actionType=showDetail&mpartId="+item.getNode().getId()+"'>"+item.getNode().getLevel()+":"+item.getNode().getName()+"</a><br>");
	// String[] temp={d.getLevel(),"",d.getNo(),d.getDrawingNO(),d.getName(),
	// d.getEname(),"",d.getUnit(),d.getSpecificationNO(),d.getSpecificationName(),
	// d.getSupplyDimension(),"",d.getGYDE(),"","",d.getSupply(),
	// d.getSource(),""};
	// creatNewRow(temp);
	// }
	//		
	// if(item.getChildren()!=null)
	// {
	// level=item.getNode().getLevel()+","+j;
	//			
	// for(int k=0;k<item.getChildren().size();k++)
	// {
	// if(k==0)
	// {
	// j=0;
	// }
	// j++;
	// level=level.substring(0,
	// level.length()-level.split(",")[level.split(",").length-1].length()-1);
	// level+=","+j;
	// item.getNode().setLevel(level);
	// writeMpart(item.getChildren().get(k));
	// }
	// level=level.substring(0,
	// level.length()-level.split(",")[level.split(",").length-1].length()-1);
	// j=Integer.parseInt(level.substring(level.length()-1, level.length()));
	//			
	// }
	// else
	// {
	// //j++;
	// if(level.length()>2)
	// {
	// //level=level.substring(0, level.length()-2);
	// level=level.substring(0,
	// level.length()-level.split(",")[level.split(",").length-1].length()-1);
	// level+=","+j;
	// item.getNode().setLevel(level);
	// }
	//
	// }
	// }

	// private void writeMpart2(MyTree<Epart> item)
	// {
	// if(item.getNode()!=null)
	// {
	// item.getNode().setLevel(level);
	// Epart d=item.getNode();
	// //out.println("<a target='detail'
	// href='plm.do?actionType=showDetail&mpartId="+item.getNode().getId()+"'>"+item.getNode().getLevel()+":"+item.getNode().getName()+"</a><br>");
	// String[] temp={d.getLevel(),"",d.getNo(),d.getDrawingNO(),d.getName(),
	// d.getEname(),"",d.getUnit(),d.getSpecificationNO(),d.getSpecificationName(),
	// d.getSupplyDimension(),"",d.getGYDE(),"","",d.getSupply(),
	// d.getSource(),""};
	// creatNewRow(temp);
	// }
	//		
	// if(item.getChildren()!=null)
	// {
	// level=item.getNode().getLevel()+","+j;
	//			
	// for(int k=0;k<item.getChildren().size();k++)
	// {
	// if(k==0)
	// {
	// j=0;
	// }
	// j++;
	// //level=level.substring(0, level.length()-2);
	// level=level.substring(0,
	// level.length()-level.split(",")[level.split(",").length-1].length()-1);
	// level+=","+j;
	// item.getNode().setLevel(level);
	// writeMpart2(item.getChildren().get(k));
	// }
	// //level=level.substring(0, level.length()-2);
	// level=level.substring(0,
	// level.length()-level.split(",")[level.split(",").length-1].length()-1);
	// j=Integer.parseInt(level.substring(level.length()-1, level.length()));
	//			
	// }
	// else
	// {
	// //j++;
	// if(level.length()>2)
	// {
	// level=level.substring(0,
	// level.length()-level.split(",")[level.split(",").length-1].length()-1);
	// level+=","+j;
	// item.getNode().setLevel(level);
	// }
	//
	// }
	// }

}
