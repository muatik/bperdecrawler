

import java.sql.ResultSet;

public class Vt
{

    MySql mysql;

    public Vt()
    {
        mysql = new MySql("beyazperde", "root", "mhk");
    }

    public ResultSet executeQuery(String sql)
    {
        return mysql.executeQuery(sql);
    }

    public boolean execute(String sql)
    {
        return mysql.execute(sql);
    }

    public int executeAndGetLastID(String sql)
    {
        return mysql.executeAndGetLastID(sql);
    }
}
