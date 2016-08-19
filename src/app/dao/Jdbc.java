package app.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.support.JdbcDaoSupport;


public class Jdbc extends JdbcDaoSupport   {   
public Object Call_prLS_OrderByMemberOrNotMember(final String[] parm) {   
    String procedureSql = "{?=call ttt}";   
        return (Object) getJdbcTemplate().execute(procedureSql, new CallableStatementCallback() {   
                    public Object doInCallableStatement(CallableStatement cs)   
                            throws SQLException, DataAccessException {   
                     
                        if (cs.execute()) {   
                            ResultSet rs = cs.getResultSet();   
                            while (rs.next()) {   
                                rs.getString(1);   
                                rs.getString(2);   
                              
                            }   
                            return null;   
                        } else {   
                            return null;  
                        }   
                    }   
                });   
    }   
  
}
