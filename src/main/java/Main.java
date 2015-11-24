import BL.BusinessLogic;
import Tables.Goods;
import Tables.Ord;
import Tables.OrdS;
import Tables.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static BusinessLogic bl=new BusinessLogic();
    static List<User> userList = new ArrayList<User>();
    static Scanner in = new Scanner(System.in);//Для ввода строк
    public static void main(String[] args) {
        Start();
    }
    public static void Con()
    {
        bl.Con();
    }

    public static void Start(){
        System.out.println("Press 1 to Insert");
        System.out.println("Press 2 to Update");
        System.out.println("Press 3 to Select");
        System.out.println("Press 4 to Delete");


        int index = in.nextInt();
        switch (index) {
            case 1:  Insert();
                break;
            case 2:  Update();
                break;
            case 3:  Select();
                break;
            case 4:  Delete();
                break;
            default:
                break;
        }
        Start();
    }


    public static void Insert(){
        System.out.println("Press 1 to Insert new User");
        System.out.println("Press 2 to Insert Goods");
        System.out.println("Press 3 to Insert Ord");
        System.out.println("Press 4 to Insert OrdS");
        int index = in.nextInt();
        switch (index) {
            case 1://Нажали 1,вводим имя нового пользователя и заносим в базу,выводим что получилось
                System.out.println("User insert");
                System.out.println("Enter User Name");
                String name =in.next();
                User user = new User(name);
                user = (User)bl.Insert(user);
                System.out.println("New user add,its parameters:");
                System.out.println("id user  " + user.id);
                System.out.println("User Name  " + user.Name);
                break;
            case 2://Нажали 2, вводим имя товара и заносим в базу,выводим что получилось
                System.out.println("Goods insert");
                System.out.println("Enter NameG");
                String nameG = in.next();
                Goods goods = new Goods(nameG);
                goods= (Goods)bl.Insert(goods);
                System.out.println("New goods add,its parameters:");
                System.out.println("id  " + goods.id);
                System.out.println("NameG  " + goods.NameG);
                break;
            case 3://Нажали 3, вводим id юзера которому хотим заказ добавить,выводим что получилось
                System.out.println("Ord insert");
                System.out.println("Insert: id_User");
                int id_User = in.nextInt();
                Ord ord = new Ord(id_User);
                ord = (Ord)bl.Insert(ord);
                System.out.println("New Ord add,its parameters:");
                System.out.println("id ord  " + ord.id);
                System.out.println("id_User  " + ord.id_User);
                break;
            case 4://Нажали 4, вводим id заказа и id товара,выводим что получилось
                System.out.println("OrdS insert");
                System.out.println("Enter id_Ord");
                int id_Ord = in.nextInt();
                System.out.println("Enter id_Goods");
                int id_Goods = in.nextInt();
                OrdS ordS = new OrdS(id_Ord,id_Goods);
                ordS = (OrdS)bl.Insert(ordS);
                System.out.println("New OrdS add,its parameters:");
                System.out.println("id_Ord  " + ordS.id_Ord);
                System.out.println("id_Goods  " + ordS.id_Goods);
                break;
            default:
                break;
        }
        System.out.println("Go Next!");
    }
    public static void Update(){
        System.out.println("Press 1 to Update User");
        System.out.println("Press 2 to Update Goods");
        System.out.println("Press 3 to Update Ord");

        int index = in.nextInt();
        String str="";
        switch (index) {
            case 1:
                User user = new User();
                System.out.println("User id");
                str = new Scanner(System.in).nextLine();
                user = (User) bl.Select("User", "ID", str);
                System.out.println("id user  " + user.id);
                System.out.println("User Name  " + user.Name);
                if(user.id != 0) {
                    System.out.println("Update Name");
                    user.Name = in.next();
                    bl.Update(user);
                }
                break;
            case 2:
                Goods goods = new Goods();
                System.out.println("Goods id");
                str= new Scanner(System.in).nextLine();
                goods = (Goods)bl.Select("Goods", "ID", str);
                System.out.println( "id goods  " + goods.id );
                System.out.println( "NameG  " + goods.NameG );
                if(goods.id != 0) {
                    System.out.println("Update NameG");
                    goods.NameG = in.next();
                    bl.Update(goods);
                }
                break;
            case 3:
                List<Ord> ord = new ArrayList<Ord>();
                System.out.println("Ord id");
                str= new Scanner(System.in).nextLine();
                ord = (ArrayList<Ord>)bl.Select("Ord", "ID", str);
                for (Ord o:ord)
                {
                    System.out.println("id " + o.id);
                    System.out.println("id_User  " + o.id_User);
                }
                if(ord.get(0).id != 0) {
                    System.out.println("Update id_User");
                    ord.get(0).id_User = in.nextInt();
                    bl.Update(ord.get(0));
                }
                break;
            default:
                break;
        }
        System.out.println("Successful");
        System.out.println("Go Next!");
    }
    public static void Select(){
        System.out.println("Press 1 to Select User");
        System.out.println("Press 2 to Select Goods");
        System.out.println("Press 3 to Select Ord");
        System.out.println("Press 4 to Select OrdS");
        int index = in.nextInt();
        String str="";
        switch (index) {
            case 1://Нажали 1,хотим выбрать инфу из юзеров
                User user = new User();
                System.out.println("User");
                System.out.println("Press 1 to Select by User id");
                System.out.println("Press 2 to Select by  User Name");
                System.out.println("Press 3 to Select All info");
                index = in.nextInt();
                if(index>0 && index<4)
                {
                    switch (index) {
                        case 1://По id юзера выводим информацию о нём
                            System.out.println("User id");
                            str = new Scanner(System.in).nextLine();
                            user = (User) bl.Select("User", "ID", str);
                            System.out.println("id user  " + user.id);
                            System.out.println("User name  " + user.Name);
                            break;
                        case 2://Тоже самое по имени
                            System.out.println("User name");
                            str = new Scanner(System.in).nextLine();
                            user = (User) bl.Select("User", "NAME", str);
                            System.out.println("id user " + user.id);
                            System.out.println("User name " + user.Name);
                            break;
                        case 3://Выводим всё
                            List<User> userList = (List<User>) bl.Select("User", "ALL", str);
                            for (User u:userList) {
                                System.out.println("id user " + u.id);
                                System.out.println("User name " + u.Name);
                            }
                            return;
                        default:
                            break;
                    }
                    System.out.println("Press 1 to Select Ord of User");
                    System.out.println("Press 2 to Select Goods of User");
                    index = in.nextInt();
                    List<Ord> OrdL;
                    switch (index) {
                        case 1:
                            System.out.println( "Ord: ");
                            OrdL = (List<Ord>)bl.Select("Ord", "ID_USER", Integer.toString(user.id));
                            for (Ord o:OrdL) {
                                System.out.println("Ord id  " + o.id);
                                System.out.println("id user  " + o.id_User);
                            }
                            break;
                        case 2:
                            System.out.println( "Goods: ");
                            OrdL = (List<Ord>)bl.Select("Ord", "ID_USER", Integer.toString(user.id));
                            for (Ord o:OrdL)
                            {
                                List<OrdS> bookings = (ArrayList<OrdS>)bl.Select("OrdS", "ID_ORD", Integer.toString(o.id));
                                for (OrdS bk:bookings)
                                {
                                    Goods goods = (Goods)bl.Select("Goods", "ID", Integer.toString(bk.id_Goods));
                                    System.out.println( "NameG  " + goods.NameG );
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
                break;
            case 2://Хотим выбрать инфу из Товаров
                Goods goods = new Goods();
                System.out.println("Goods:");
                System.out.println("Press 1 to Select Goods by id");
                System.out.println("Press 2 to Select Goods by NameG");
                System.out.println("Press 3 to Select all Goods ");
                index = in.nextInt();
                switch (index) {
                    case 1:
                        System.out.println("Enter Goods id");
                        str= new Scanner(System.in).nextLine();
                        goods = (Goods)bl.Select("Goods", "ID", str);
                        System.out.println( "Goods id  " + goods.id );
                        System.out.println( "NameG  " + goods.NameG );
                        break;
                    case 2:
                        System.out.println("Enter NameG");
                        str= new Scanner(System.in).nextLine();
                        goods = (Goods)bl.Select("Goods", "NAMEG", str);
                        System.out.println( "Goods id " + goods.id );
                        System.out.println( "NameG  " + goods.NameG);
                        break;
                    case 3:
                        List<Goods> goodsList = (List<Goods>) bl.Select("Goods", "ALL", str);
                        for (Goods g:goodsList)
                        {
                            System.out.println("Goods id " + g.id);
                            System.out.println("NameG  " + g.NameG);
                        }
                    default:
                        break;
                }
                break;
            case 3://Хотим выбрать инфу из Заказа
                List<Ord> ordList = new ArrayList<Ord>();
                System.out.println("Ord");
                System.out.println("Press 1 to Select Ord by id ");
                System.out.println("Press 2 to Select Ord by id_User ");
                System.out.println("Press 3 to Select all Ord");

                index = in.nextInt();
                switch (index) {
                    case 1:
                        System.out.println("Enter Ord id");
                        str= new Scanner(System.in).nextLine();
                        ordList = (ArrayList<Ord>)bl.Select("Ord", "ID", str);
                        for (Ord o:ordList) {
                            System.out.println("Ord id  " + o.id);
                            System.out.println("id_User  " + o.id_User);
                        }
                        break;
                    case 2:
                        System.out.println("Enter Ord id_User:");
                        str= new Scanner(System.in).nextLine();
                        ordList = (List<Ord>)bl.Select("Ord", "ID_USER", str);
                        for (Ord o:ordList) {
                            System.out.println("Ord id  " + o.id);
                            System.out.println("id_User  " + o.id_User);
                        }
                        break;
                    case 3:
                        List<Ord> ordList1 = (List<Ord>) bl.Select("Ord", "ALL", str);
                        for (Ord o:ordList1) {
                            System.out.println("Ord id  " + o.id);
                            System.out.println("id_User  " + o.id_User);
                        }
                        return;
                    default:
                        break;
                }
                break;
            case 4://Хотим инфу из Заказов
                List<OrdS> ordSList = new ArrayList<OrdS>();
                System.out.println("OrdS:");
                System.out.println("Press 1 to Select OrdS by id_Ord ");
                System.out.println("Press 1 to Select OrdS by id_Goods ");
                System.out.println("Press 1 to Select all OrdS  ");
                index = in.nextInt();
                switch (index) {
                    case 1:
                        System.out.println("Enter Ord id_Ord");
                        str= new Scanner(System.in).nextLine();
                        ordSList = (ArrayList<OrdS>)bl.Select("OrdS", "ID_ORD", str);
                        for (OrdS o:ordSList) {
                            System.out.println("id_Ord  " + o.id_Ord);
                            System.out.println("id_Goods " + o.id_Goods);
                        }
                        break;
                    case 2:
                        System.out.println("Enter Ord id_Goods");
                        str= new Scanner(System.in).nextLine();
                        ordSList = (List<OrdS>)bl.Select("OrdS", "ID_GOODS", str);
                        for (OrdS o:ordSList) {
                            System.out.println("id_Ord = " + o.id_Ord);
                            System.out.println("id_Goods = " + o.id_Goods);
                        }
                        break;
                    case 3:
                        List<OrdS> ordSList2 = (List<OrdS>) bl.Select("OrdS", "ALL", str);
                        for (OrdS o:ordSList2) {
                            System.out.println("id_Ord  " + o.id_Ord);
                            System.out.println("id_Goods  " + o.id_Goods);
                        }
                        return;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        System.out.println("Go Next!");
    }
    public static void Delete(){
        System.out.println("Press 1 to Delete User");
        System.out.println("Press 2 to Delete Goods");
        System.out.println("Press 3 to Delete Ord");

        int index = in.nextInt();
        int id;
        switch (index) {
            case 1://При удалении юзера так же удаляется вся информация из заказов(сначала из заказов,затем сам юзер,чтобы связь не потерялась)
                System.out.println("Delete User by id");
                id = in.nextInt();
                List<Ord> ordList = (List<Ord>)bl.Select("Ord", "ID_USER", Integer.toString(id));
                for (Ord o:ordList)
                {
                    List<OrdS> bookings = (ArrayList<OrdS>)bl.Select("OrdS", "ID_ORD", Integer.toString(o.id));
                    for (OrdS os:bookings)
                    {
                        bl.Delete("OrdS","ID_ORD", os.id_Ord);
                    }
                    bl.Delete("Ord","ID", o.id_User);
                }
                bl.Delete("User","ID", id);
                break;
            case 2://Если товара нет,то и в заказах его быть не может
                System.out.println("Delete Goods by id");
                id = in.nextInt();
                List<OrdS> ordSList = (ArrayList<OrdS>)bl.Select("OrdS", "ID_GOODS", Integer.toString(id));
                for (OrdS os:ordSList)
                {
                    bl.Delete("OrdS", "ID_GOODS", os.id_Goods);
                }
                bl.Delete("Goods","ID", id);
                break;
            case 3:
                System.out.println("Delete Ord by id");
                id = in.nextInt();
                List<OrdS> ordSList1 = (ArrayList<OrdS>)bl.Select("OrdS", "ID_ORD", Integer.toString(id));
                for (OrdS os:ordSList1)
                {
                    bl.Delete("OrdS","ID_ORD", os.id_Ord);
                }
                bl.Delete("Ord","ID", id);
                break;
            default:
                break;
        }
        System.out.println("Delete completed");
        System.out.println("Go Next!");
    }
}
