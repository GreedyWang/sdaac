package sdaac.wym.app.Dao.plm;

public class MBomDao extends BomDao {

	public MBomDao(String className) throws InstantiationException,
			IllegalAccessException {
		super(className, "MBom");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "cpart";
	}

}
