package DL;
import Tables.Goods;
import Tables.Ord;
import Tables.OrdS;
import Tables.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataLayer {
    private final static String createDatabaseQyery =
            "CREATE DATABASE test CHARACTER SET utf8 COLLATE utf8_general_ci";
    Statement stmt = null;
    String sql = null;
    /*String createUserTableQuery = "CREATE TABLE User " +
            "  (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  Name TEXT," +
            "  Surname TEXT)";

    String createOrdTableQuery = "CREATE TABLE Ord " +
            "  (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  id_User INTEGER,"+
            " FOREIGN KEY(id_User) REFERENCES User(id))";

    String createOrdSTableQuery = "CREATE TABLE OrdS " +
            "  (id_Ord INTEGER ," +
            "  id_Goods INTEGER,"+
            "FOREIGN KEY(id_Ord) REFERENCES Ord(id),"+
            "FOREIGN KEY(id_Goods) REFERENCES Goods(id))";

    String createGoodsTableQuery = "CREATE TABLE Goods " +
            "  (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "  NameG TEXT)";
            */


    public void Connection() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:test.db");
            stmt = con.createStatement();
            System.out.println("Opened database successfully");

            /*stmt.execute(createUserTableQuery);
            stmt.execute(createGoodsTableQuery);

            stmt.execute(createOrdTableQuery);
            stmt.execute(createOrdSTableQuery);
            */


            System.out.println("Tables create successfully");

            /*stmt.execute("INSERT INTO 'User' ('Name', 'Surname') VALUES ('Petya', 'Ivanov'); ");
            stmt.execute("INSERT INTO 'User' ('Name', 'Surname') VALUES ('Ivan', 'Petrov'); ");
            stmt.execute("INSERT INTO 'Goods' ('NameG') VALUES ('topor'); ");
            stmt.execute("INSERT INTO 'Goods' ('NameG') VALUES ('lopata'); ");
            stmt.execute("INSERT INTO 'Ord' ('id_User') VALUES (1); ");
            stmt.execute("INSERT INTO 'Ord' ('id_User') VALUES (2); ");
            stmt.execute("INSERT INTO 'OrdS' ('id_Ord','id_Goods') VALUES (1,1); ");
            stmt.execute("INSERT INTO 'OrdS' ('id_Ord','id_Goods') VALUES (2,2); ");
            */

            System.out.println("Tables fill successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public Object Select(String table, String column, String item){
        Connection con ;
        Statement st ;
        Object object = null;
        String  name="";
        String nameG ="";

        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:test.db");
            con.setAutoCommit(false);
            st = con.createStatement();
            if (table.equals("User")){
                object = new User();
                PreparedStatement prepareSt= null;
                ResultSet res = null;
                if(column.equals("ID")) {
                    prepareSt = con.prepareStatement("select * from USER where ID = ?");
                    prepareSt.setInt(1, Integer.parseInt(item));
                    res = prepareSt.executeQuery();
                }
                if(column.equals("NAME")) {
                    prepareSt = con.prepareStatement("select * from USER where NAME = ?");
                    prepareSt.setString(1, item);
                    res = prepareSt.executeQuery();
                }
                if(column.equals("ALL")) {
                    List<User> userList = new ArrayList<User>();
                    prepareSt = con.prepareStatement("select * from USER");
                    res = prepareSt.executeQuery();
                    int id=0;
                    while (res.next())
                    {
                        id = res.getInt("id");
                        name = res.getString("Name");
                        userList.add(new User(id, name));
                    }
                    res.close();
                    prepareSt.close();
                    st.close();
                    con.close();
                    return  userList;
                }
                int id=0;
                name="";
                while (res.next())
                {
                    id = res.getInt("id");
                    name = res.getString("Name");
                }
                res.close();
                prepareSt.close();
                object = new User(id,name);
            }
            else
            if (table.equals("Goods")){
                object = new Goods();
                PreparedStatement prepareSt= null;
                ResultSet res = null;
                if(column.equals("ID")) {
                    prepareSt = con.prepareStatement("select * from Goods where ID = ?");
                    prepareSt.setInt(1, Integer.parseInt(item));
                    res = prepareSt.executeQuery();
                }
                if(column.equals("NameG")) {
                    prepareSt = con.prepareStatement("select * from Goods where NAMEG = ?");
                    prepareSt.setString(1, item);
                    res = prepareSt.executeQuery();
                }
                if(column.equals("ALL")) {
                    List<Goods> GoodsL = new ArrayList<Goods>();
                    prepareSt = con.prepareStatement("select * from Goods");
                    res = prepareSt.executeQuery();
                    int id=0;
                    while (res.next())
                    {
                        id = res.getInt("id");
                        nameG = res.getString("NameG");
                        GoodsL.add(new Goods(id,nameG));
                    }
                    res.close();
                    prepareSt.close();
                    st.close();
                    con.close();
                    return  GoodsL;
                }
                int id=0;
                nameG="";
                while (res.next())
                {
                    id = res.getInt("id");
                    nameG = res.getString("NameG");
                }
                res.close();
                prepareSt.close();
                object = new Goods(id,nameG);
            }
            else if (table.equals("Ord")){
                List<Ord> list = new ArrayList<Ord>();
                PreparedStatement prepareSt= null;
                ResultSet res = null;
                if(column.equals("ID")) {
                    prepareSt = con.prepareStatement("select * from Ord where ID = ?");
                    prepareSt.setInt(1, Integer.parseInt(item));
                    res = prepareSt.executeQuery();
                }
                if(column.equals("ID_USER")) {
                    prepareSt = con.prepareStatement("select * from Ord where ID_USER = ?");
                    prepareSt.setInt(1, Integer.parseInt(item));
                    res = prepareSt.executeQuery();
                }
                if(column.equals("ALL")) {
                    List<Ord> OrdL = new ArrayList<Ord>();
                    prepareSt = con.prepareStatement("select * from Ord");
                    res = prepareSt.executeQuery();
                    int id=0;
                    int id_user = 0;
                    while (res.next())
                    {
                        id = res.getInt("id");
                        id_user = res.getInt("id_User");
                        OrdL.add(new Ord(id,id_user));
                    }
                    res.close();
                    prepareSt.close();
                    st.close();
                    con.close();
                    return  OrdL;
                }
                int id=0;
                int  id_user=0;
                while (res.next()){
                    id = res.getInt("id");
                    id_user = res.getInt("id_User");
                    list.add(new Ord(id,id_user));
                }
                res.close();
                prepareSt.close();
                object = list;
            }
            else if (table.equals("OrdS")){
                List<OrdS> list = new ArrayList<OrdS>();
                PreparedStatement prepareSt= null;
                ResultSet res = null;
                if(column.equals("ID_ORD")) {
                    prepareSt = con.prepareStatement("select * from OrdS where ID_ORD = ?");
                    prepareSt.setInt(1, Integer.parseInt(item));
                    res = prepareSt.executeQuery();
                }
                if(column.equals("ID_GOODS")) {
                    prepareSt = con.prepareStatement("select * from OrdS where ID_GOODS = ?");
                    prepareSt.setInt(1, Integer.parseInt(item));
                    res = prepareSt.executeQuery();
                }
                if(column.equals("ALL")) {
                    List<OrdS> OrdSL = new ArrayList<OrdS>();
                    prepareSt = con.prepareStatement("select * from OrdS");
                    res = prepareSt.executeQuery();
                    int id_Ord = 0;
                    int id_Goods = 0;
                    while (res.next())
                    {
                        id_Ord = res.getInt("id_Ord");
                        id_Goods = res.getInt("id_Goods");
                        OrdSL.add(new OrdS(id_Ord,id_Goods));
                    }
                    res.close();
                    prepareSt.close();
                    st.close();
                    con.close();
                    return  OrdSL;
                }
                int id_booking = 0;
                int id_product = 0;
                while (res.next())
                {
                    id_booking = res.getInt("id_Ord");
                    id_product = res.getInt("id_Goods");
                    list.add(new OrdS(id_booking,id_product));
                }
                res.close();
                prepareSt.close();
                object = list;
            }
            st.close();
            con.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return object;

    }

    public void Update(Object object){
        Connection con;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:test.db");
            con.setAutoCommit(false);
            if (object.getClass().getSimpleName().equals("User")) {
                PreparedStatement prepareSt = con.prepareStatement("UPDATE User set NAME = ? where ID=?;");
                prepareSt.setString(1, ((User) object).Name);
                prepareSt.setInt(2, ((User) object).id);
                prepareSt.executeUpdate();
            }
            else if (object.getClass().getSimpleName().equals("Goods")){
                PreparedStatement pst = con.prepareStatement("UPDATE Goods set NAMEG = ? where ID=?;");
                pst.setString(1, ((Goods) object).NameG);
                pst.setInt(2, ((Goods) object).id);
                pst.executeUpdate();
            }
            else if (object.getClass().getSimpleName().equals("Ord")) {
                PreparedStatement pst = con.prepareStatement("UPDATE Ord set ID_USER = ? where ID=?;");
                pst.setInt(1, ((Ord) object).id_User);
                pst.executeUpdate();
            }
            con.commit();
            con.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void Delete(String table, String column, int id){
        Connection con ;
        Statement st ;
         try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:test.db");
            con.setAutoCommit(false);
            st = con.createStatement();
            PreparedStatement prepareSt= null;
            if (table.equals("User")){
                prepareSt = con.prepareStatement("DELETE from User where ID = ?");
                prepareSt.setInt(1, id);
                prepareSt.executeUpdate();
                con.commit();
            }
            else if (table.equals("Goods")){
                prepareSt = con.prepareStatement("DELETE from Goods where ID = ?");
                prepareSt.setInt(1, id);
                prepareSt.executeUpdate();
                con.commit();
            }
            else if (table.equals("Ord")){
                prepareSt = con.prepareStatement("DELETE from Ord where ID = ?");
                prepareSt.setInt(1, id);
                prepareSt.executeUpdate();
                con.commit();
            }
            else if (table.equals("OrdS")){
                if(column.equals("ID_ORD")) {
                    prepareSt = con.prepareStatement("DELETE from OrdS where ID_ORD = ?");
                    prepareSt.setInt(1, id);
                }
                else if(column.equals("ID_GOODS")) {
                    prepareSt = con.prepareStatement("DELETE from OrdS where ID_GOODS = ?");
                    prepareSt.setInt(1, id);
                }
                prepareSt.executeUpdate();
                con.commit();
            }
            prepareSt.close();
            st.close();
            con.close();

        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

    }

    public Object Insert(Object object){
        Connection con;
        ResultSet res;
        int id = 0;
        System.out.println(object.getClass().getSimpleName());
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:test.db");
            con.setAutoCommit(false);//чтобы транзакция не проходила без подтверждения

            if (object.getClass().getSimpleName().equals("User")) {
                PreparedStatement prepareSt = con.prepareStatement("INSERT INTO USER (NAME) VALUES (?);");//хранит в себе объект,предкомп.
                prepareSt.setString(1, ((User) object).Name);
                prepareSt.executeUpdate();//вып обновление
                prepareSt = con.prepareStatement("SELECT last_insert_rowid()");
                res = prepareSt.executeQuery();//вып. запрос
                while (res.next())
                {
                    id = res.getInt(1);
                }
                object = new User(id,((User) object).Name);
                res.close();
            }

            else if (object.getClass().getSimpleName().equals("Goods")){
                //object = (Goods) object;
                PreparedStatement pst = con.prepareStatement("INSERT INTO Goods (NAMEG) VALUES (?);");
                pst.setString(1, ((Goods) object).NameG);
                pst.executeUpdate();
                pst = con.prepareStatement("SELECT last_insert_rowid()");
                res = pst.executeQuery();
                id = 0;
                while (res.next())
                {
                    id = res.getInt(1);
                }
                object = new Goods(id,((Goods) object).NameG);
                res.close();
            }
            else if (object.getClass().getSimpleName().equals("Ord")) {

                PreparedStatement pst = con.prepareStatement("INSERT INTO Ord (ID_USER) VALUES (?);");
                pst.setInt(1, ((Ord) object).id_User);
                pst.executeUpdate();
                pst = con.prepareStatement("SELECT last_insert_rowid()");
                res = pst.executeQuery();
                id = 0;
                while (res.next())
                {
                    id = res.getInt(1);
                }
                object = new Ord(id,((Ord) object).id_User);
                res.close();
            }
            else if (object.getClass().getSimpleName().equals("OrdS")) {
                PreparedStatement pst = con.prepareStatement("INSERT INTO OrdS (ID_ORD,ID_GOODS) VALUES (?,?);");
                pst.setInt(1, ((OrdS) object).id_Ord);
                pst.setInt(2, ((OrdS) object).id_Goods);
                pst.executeUpdate();
            }
            con.commit();
            con.close();
        }
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return object;
    }
}

