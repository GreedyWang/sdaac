package common.util;

import java.sql.Types;

import org.hibernate.Hibernate;
import org.hibernate.dialect.DB2Dialect;
import org.hibernate.dialect.SQLServerDialect;

public class PmDb2Dialect extends SQLServerDialect {
	public PmDb2Dialect()
	{
	     super();
	     registerHibernateType(Types.DECIMAL, Hibernate.BIG_DECIMAL.getName());
	}
}
