package sdaac.wym.app.Service.PCL.MainData;
/**
 * 申请 公用接口
 * @author SA1KV5
 *
 */
public interface IApprove {
	
	public BaseForm doApprove(ApproveForm item);
	public BaseForm doAgree(ApproveForm item);
	public BaseForm doRefuse(ApproveForm item);
	public BaseForm doNeedExplain(ApproveForm item);
	public BaseForm doBack(ApproveForm item);
}
