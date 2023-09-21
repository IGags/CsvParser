import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnect {
    public static Connection conn;
    public static Statement statmt;
    public static ResultSet resSet;

    public static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:CountryDb.sdb");
    }

    public static void CreateDB() throws SQLException
    {
        statmt = conn.createStatement();
        statmt.execute("CREATE TABLE if not exists 'countries' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'subregion' text, 'region' text, 'internetUsers' INT, 'population' INT);");
    }

    public static void WriteCountries(List<Country> countries) throws SQLException{
        resSet = statmt.executeQuery("SELECT COUNT(1) as ct FROM countries");
        resSet.next();
        if(resSet.getInt("ct") != 0){
            return;
        }
        for (var country:
             countries) {
            var query = String.format("INSERT INTO countries(name, subregion, region, internetUsers, population) values ('%s','%s','%s',%s,%s)",country.getName(), country.getSubregion(), country.getRegion(), country.getInternetUsers(), country.getPopulation());
            statmt.execute(query);
        }
    }
}
