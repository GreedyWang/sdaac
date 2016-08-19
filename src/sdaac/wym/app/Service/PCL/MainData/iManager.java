package sdaac.wym.app.Service.PCL.MainData;
/**
 * <tr>
 * 	<td>
 *  	PCL 物料主数据申请 业务管理类 接口类
 *  </td>
 * </tr>
 * @version 1.0
 * @author SA1KV5
 * @since 2011-11-9
 */
import java.util.List;
public interface iManager {
	//apply 申请
	void doApply(MDForm item);
	List<BaseForm> doShow(MDForm item);
	BaseForm doShowDetails(int id);
	//cancel 取消
	void doCancelById(int formId);
	void doCancel(Integer[] ids);
	//approve 审批
	void doApprove(MDForm item);
	//更新
	void doUpdate(MDForm item);
}
