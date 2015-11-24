package BL;
import DL.DataLayer;
import java.util.*;
import java.util.Scanner;

/**
 * Created by contest on 17.11.2015.
 */
public class BusinessLogic {
    public DataLayer dl;
    public void Con()
    {
        dl=new DataLayer();
        dl.Connection();
    }
}
