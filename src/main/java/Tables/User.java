package Tables;

/**
 * Created by contest on 17.11.2015.
 */
public class User {
    public int id;
    public String Name;
    public User(){}
    public User(String Name){
        this.Name=Name;
    }
    public User(int id,String Name){
        this.id=id;
        this.Name=Name;
    }
}
