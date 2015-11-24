package BL;
import DL.DataLayer;
import java.util.*;
import java.util.Scanner;

/**
 * Created by contest on 17.11.2015.
 */
public class BusinessLogic {
    public DataLayer dl=new DataLayer();
    public void Con(){
        dl.Connection();
    }

    public Object Select(String table, String column, String item) {
        return dl.Select(table, column, item);
    }

    public void Update(Object object){
        dl.Update(object);
    }

    public Object Insert(Object object){
        return dl.Insert(object);
    }

    public void Delete(String table, String column, int id){
        dl.Delete(table, column, id);
    }


}
