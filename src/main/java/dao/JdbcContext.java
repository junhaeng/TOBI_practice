package dao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jhcorea on 2017. 2. 28..
 */
public class JdbcContext {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException {
        Connection c = null;
        PreparedStatement ps = null;

        try {
            c = dataSource.getConnection();
            ps = stmt.makePreparedStatement(c);
            ps.executeUpdate();
        } catch(SQLException e) {
            throw e;
        } finally {
            if (ps != null) {
                try {
                    ps.close();;
                } catch (SQLException e ) {
                    System.out.println("ps close error");
                }
            }
            if (c != null){
                try {
                    c.close();
                } catch (SQLException e) {
                    System.out.println("c close error");
                }
            }
        }
        ps.close();
        c.close();
    }

}
