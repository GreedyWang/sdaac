package app.biz.vave;

import java.util.List;

import app.entity.vave.VaveInfo;

public interface VaveInfoBiz {
	public List<String> doSelectVehicleByCustomCode(String custom_code);
	public List<String> doSelectCustomCode();
	public Long doSelectVolumeByPK(String custom_code,String Vehicle);
	public void doInsertInfo(VaveInfo item);
	public void doDeleteInfo(VaveInfo item);
	public List<VaveInfo> doSelectAll();
}
