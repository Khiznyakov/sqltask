package DL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataLayer {
    private final static String createDatabaseQyery =
            "CREATE DATABASE test CHARACTER SET utf8 COLLATE utf8_general_ci";
    Statement stmt = null;
    String sql = null;
    String createUserTableQuery = "CREATE TABLE `User` (" +
            "  `id` int(11) NOT NULL auto_increment," +
            "  `Name` varchar(50) default NULL," +
            "  `Surname` varchar(50) default NULL," +
            "  PRIMARY KEY  (`id`)" +
            ") ENGINE=test DEFAULT CHARSET=utf8;";

    String createOrdTableQuery = "CREATE TABLE `Ord` (" +
            "  `id` int(11) NOT NULL auto_increment," +
            "  `id_User` int(11) default NULL," +
            "  PRIMARY KEY  (`id`)" +
            ") ENGINE=test DEFAULT CHARSET=utf8;";
    String createOrdSTableQuery = "CREATE TABLE `OrdS` (" +
            "  `id_Ord` int(11) NOT NULL auto_increment," +
            "  `id_Goods` int(11) default NULL," +
            "  PRIMARY KEY  (`id_Ord`)" +
            ") ENGINE=test DEFAULT CHARSET=utf8;";

    String createGoodsTableQuery = "CREATE TABLE `Goods` (" +
            "  `id` int(11) NOT NULL auto_increment," +
            "  `NameG` varchar(50) default NULL," +
            "  PRIMARY KEY  (`id`)" +
            ") ENGINE=test DEFAULT CHARSET=utf8;";

    public void Connection() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt = con.createStatement();
            System.out.println("Opened database successfully");

            stmt.execute(createGoodsTableQuery);
            stmt.execute(createOrdSTableQuery);
            stmt.execute(createOrdSTableQuery);
            stmt.execute(createUserTableQuery);
            System.out.println("Tables create successfully");

            stmt.execute("INSERT INTO 'Users' ('Name', 'Surname') VALUES ('Petya', 'Ivanov'); ");
            stmt.execute("INSERT INTO 'Users' ('Name', 'Surname') VALUES ('Ivan', 'Petrov'); ");
            stmt.execute("INSERT INTO 'Goods' ('NameG') VALUES ('topor'); ");
            stmt.execute("INSERT INTO 'Goods' ('NameG') VALUES ('lopata'); ");
            stmt.execute("INSERT INTO 'Ord' ('id_User') VALUES (1); ");
            stmt.execute("INSERT INTO 'Ord' ('id_User') VALUES (2); ");
            stmt.execute("INSERT INTO 'OrdS' ('id_Ord','id_Goods') VALUES (1,1); ");
            stmt.execute("INSERT INTO 'OrdS' ('id_Ord','id_Goods') VALUES (2,2); ");
            System.out.println("Tables fill successfully");




        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }



}

