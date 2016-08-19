package sdaac.wym.app.Service.SAP.BaseDataMaintance;
/**
 * maintain the SAP Reporting Base Data
 * @author SA1KV5
 *
 */
public interface Imanager {
	
	public void doUpload(BaseData data);
	/**
	 * 上传数据通过sql命令
	 * @param url
	 */
	public void doUpload(String url,String type);
}
