package app.biz.impl.epor;

/**
 * Doa实体类
 * 2010-8-3
 * @author sa1kv5
 *
 */
public class DoaEntity {

		private int isPlane;
		private int isCaptral;
		private int money;
		private String roleName;
		private boolean flag;
		
		
		public DoaEntity(int isPlane, int isCaptral, int money,
				String roleName, boolean flag) {
			super();
			this.isPlane = isPlane;
			this.isCaptral = isCaptral;
			this.money = money;
			this.roleName = roleName;
			this.flag = flag;
		}
		
		public int getIsPlane() {
			return isPlane;
		}
		public void setIsPlane(int isPlane) {
			this.isPlane = isPlane;
		}
		public int getIsCaptral() {
			return isCaptral;
		}
		public void setIsCaptral(int isCaptral) {
			this.isCaptral = isCaptral;
		}
		public int getMoney() {
			return money;
		}
		public void setMoney(int money) {
			this.money = money;
		}
		public String getRoleName() {
			return roleName;
		}
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		public boolean isFlag() {
			return flag;
		}
		public void setFlag(boolean flag) {
			this.flag = flag;
		}

}
