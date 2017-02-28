package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by jhcorea on 2017. 2. 28..
 */
public class AddStatement implements StatementStrategy {
    private User user;

    public AddStatement(User user){
        this.user = user;
    }

    public PreparedStatement makePreparedStatement(Connection c) throws SQLException {
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, pwd, name) values(?, ?, ?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());
        return ps;
    }
}
