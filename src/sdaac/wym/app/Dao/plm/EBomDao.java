package sdaac.wym.app.Dao.plm;

public class EBomDao extends BomDao {

	public EBomDao(String className) throws InstantiationException,
			IllegalAccessException {
		super(className, "Ebom");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String getTableName() {
		// TODO Auto-generated method stub
		return "cpart";
	}

}
