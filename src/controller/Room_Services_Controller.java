package controller;

import facade.Data_Function;
import facade.File_IO;
import model.Room;
import model.Room_Service;
import model.Room_Service_Menu;
import model.order_Menu;
import org.omg.CORBA.INTERNAL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by chewzhijie on 25/3/16.
 */
public class Room_Services_Controller {
    private final static String stored_File_Name = "room_Services";

    private static ArrayList<Room_Service> room_services= new ArrayList<>();

    private static Scanner sc;


    public Room_Services_Controller()
    {
        try
        {
            sc = new Scanner(System.in);
            loadData();
        }
        finally
        {
            if(room_services == null)
                room_services = new ArrayList<Room_Service>();


        }
    }

    public static void tryLoad()
    {
        try
        {
            sc = new Scanner(System.in);
            loadData();
        }
        finally
        {
            if(room_services == null)
                room_services = new ArrayList<Room_Service>();
        }
    }

    //Adding New Room Service
    public static void addRoomService()
    {
        String date;
        String time;
        String room_Num = "";
        String remarks;
        Integer order_ID;
        Integer order_Quantity;
        ArrayList<order_Menu> order = new  ArrayList<>();
        boolean Okflag = false, done = false, cmpFlag;
        String datePatternRegex = "[0-3][0-9]/[0-1][0-9]/[1-2][0-9][0-9][0-9]";
        String timePatternRegex = "^^[^\\s]{4}$";

        //Room_Service( String date, String time, String room_Num, String remarks, ArrayList<order_Menu> order )
        System.out.println("=====================================");
        System.out.println("        Adding Room Service");
        System.out.println("=====================================");

        cmpFlag = false;
        while (!cmpFlag) {
            cmpFlag = false;
            System.out.print("Room Service Room Number: ");
            room_Num = sc.nextLine();

            cmpFlag = Rooms_Controller.findRoom(room_Num) != -1;
        }

        if(Rooms_Controller.getStatus(room_Num) != Room.room_Status.Occupied)
        {
            System.out.print("Room "+ room_Num +" is not checked-in.\n");
            return;
        }

        do {
            cmpFlag = false;
            System.out.print("Room Service Date (DD/MM/YYYY): ");
            date = sc.nextLine();

            if (!Data_Function.regularExpressionMatch(datePatternRegex,date))
            {
                System.out.println(date+"  does not match with DD/MM/YYYY!");
                cmpFlag = true;
            }
        }
        while(cmpFlag);


        do {
            cmpFlag = false;
            System.out.print("Room Service Time (hhmm : 24 hour time format): ");
            time = sc.nextLine();

            if (!Data_Function.regularExpressionMatch(timePatternRegex,time))
            {
                System.out.println(date+"  does not match with hhmm");
                cmpFlag = true;
            }
        }
        while(cmpFlag);



        Room_Services_Menu_Controller.listAllMenu();


        while (!done) {
            Okflag = false;
            while (!Okflag) {
                try {
                    System.out.print("Menu ID (-1 To Stop): ");
                    order_ID = Integer.parseInt(sc.nextLine());
                    if (order_ID == -1) {
                        done = true;
                        break;
                    }
                    if(order_ID > Room_Services_Menu_Controller.getMenuSize()) {
                        System.out.print("No Menu Item Found! ");
                        break;
                    }

                    System.out.print("Quantity : ");
                    order_Quantity = Integer.parseInt(sc.nextLine());

                    order.add(new order_Menu(order_ID,order_Quantity));

                    Okflag = true;
                } catch (Exception e) {
                    System.out.println("Invalid  Input!");
                    Okflag = false;
                }
            }
        }

        System.out.print("Remarks: ");
        remarks = sc.nextLine();

        room_services.add(new Room_Service(date,time,room_Num,remarks,order));
        System.out.println("Room Service Added!");
    }

    //Deleting New Room Service
    public static void deleteRoomService()
    {
        Integer index = 0;
        String choice;
        Boolean OkFlag = false;


        System.out.println("===============================");
        System.out.println("    Deleting Room Service");
        System.out.println("===============================");
        listRoomService();

        //MENU DESCRIPTION
        while (!OkFlag) {
            try {
                System.out.println("Deleting which ID from Room Service? ");
                index = Integer.parseInt(sc.nextLine());
                OkFlag = true;
            }
            catch (Exception e)
            {
                OkFlag = false;
            }
        }

        System.out.println("Delete Conformation ('Y'for Yes, other input for No): ");
        choice = sc.nextLine();

        if(choice.equals("Y"))
        {
            try {
                room_services.remove(index - 1);
                System.out.println("Sucessfully Deleted ID:" + index);
            }catch (ArrayIndexOutOfBoundsException e){System.out.println("Room Service With ID "+index+" Not Found!");}
        }
        else
        {
            System.out.println("Abort Delete");
        }
    }

    //Update Room Service
    public static void updateRoomService()
    {
        Integer index = 0;
        String roomNum  = "";
        Integer choice = 0;
        Boolean OkFlag = false;

        String date;
        String time;
        String room_Num;
        String remarks;
        Integer order_ID;
        Integer order_Quantity;
        ArrayList<order_Menu> order;
        boolean Okflag = false, done = false, cmpFlag;
        String datePatternRegex = "[0-3][0-9]/[0-1][0-9]/[1-2][0-9][0-9][0-9]";
        String timePatternRegex = "[0-2][0-9][0-9][0-9]";

        System.out.println("===============================");
        System.out.println("    Updating Room Service");
        System.out.println("    Input '.' to retain data");
        System.out.println("===============================");


        //MENU DESCRIPTION
        while (!OkFlag) {
            System.out.print("Room Number: ");
            roomNum = sc.nextLine();
            index = findIndexByRmNum(roomNum);
            if(index != -1)
                OkFlag = true;
        }

        System.out.println("==========================================");
        System.out.println("    Updating Room Service for Room " +roomNum);
        System.out.println("==========================================");

        do {
            cmpFlag = false;
            System.out.print("Room Service Date (DD/MM/YYYY) ("+ room_services.get(index).getDate() +"): ");
            date = sc.nextLine();

            if(date.equals("."))
                break;

            if (!Data_Function.regularExpressionMatch(datePatternRegex,date))
            {
                System.out.println(date+"  does not match with DD/MM/YYYY!");
                cmpFlag = true;
            }
        }
        while(cmpFlag);
        if(!date.equals("."))
            room_services.get(index).setDate(date);


        do {
            cmpFlag = false;
            System.out.print("Room Service Time (hhmm) ("+ room_services.get(index).getTime() +"): ");
            time = sc.nextLine();

            if(time.equals("."))
                break;

            if (!Data_Function.regularExpressionMatch(timePatternRegex,time))
            {
                System.out.println(date+"  does not match with hhmm");
                cmpFlag = true;
            }
        }
        while(cmpFlag);
        if(!time.equals("."))
            room_services.get(index).setTime(time);

        Room_Services_Menu_Controller.listAllMenu();


        //Order Menu Manipulation
        System.out.println("\nListing Ordered Menu");
        listRoomServiceOrder(index);



        while (!done) {
            order = room_services.get(index).getOrder();
            System.out.println("\n1.Add More Menu\n2.Delete Menu By ID\nOthers numeric value to exit");
            try
            {
                choice = Integer.parseInt(sc.nextLine());
            }
            catch (Exception e)
            {
                System.out.println("Invalid  Input!");
                done = false;
            }

            if(choice == 1) {
                OkFlag = false;
                while (!OkFlag) {
                    try
                    {
                        Room_Services_Menu_Controller.listAllMenu();

                        System.out.print("Menu ID: ");
                        order_ID = Integer.parseInt(sc.nextLine());

                        if (order_ID > Room_Services_Menu_Controller.getMenuSize()) {
                            System.out.print("No Menu Item Found! ");
                            break;
                        }

                        System.out.print("Quantity : ");
                        order_Quantity = Integer.parseInt(sc.nextLine());

                        order.add(new order_Menu(order_ID, order_Quantity));

                        OkFlag = true;
                    } catch (Exception e) {
                        System.out.println("Invalid  Input!");
                        OkFlag = false;
                    }
                }
            }

            else if(choice == 2){
                OkFlag = false;
                while (!OkFlag) {
                    System.out.print("Ordered ID to remove: ");
                    try {
                        order_ID = Integer.parseInt(sc.nextLine());

                        order.remove(order_ID-1);
                        OkFlag = true;
                    }
                    catch (Exception e)
                    {
                        System.out.println("Invalid Input!");
                        OkFlag = false;
                    }
                }
            }
            else
                done = true;

        }


        System.out.print("Room Service Remarks ("+ room_services.get(index).getRemarks() +"): ");
        remarks = sc.nextLine();
        if(!remarks.equals("."))
            room_services.get(index).setRemarks(remarks);

        System.out.println("Room Service Added!");
    }

    public static void updateAllStatusWorker()
    {
        String dateIn;
        Integer mimDiff;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hhmm");
        Date dateobj = new Date();

        for (int i = 0 ; i<room_services.size(); i++)
        {
            mimDiff = Data_Function.minDifference(df.format(dateobj), room_services.get(i).getDate()+" "+room_services.get(i).getTime(), "dd/MM/yyyy hhmm");

            if(mimDiff < 0)
                room_services.get(i).setStatus(Room_Service.room_Service_Status.delivered);
            else  if (mimDiff < 60)
                room_services.get(i).setStatus(Room_Service.room_Service_Status.preparing);
        }
    }

    public static void printRoomServiceStatus()
    {
        String roomNum = "";
        Integer index = -1;
        boolean OkFlag = false, done = false, cmpFlag;


        while (!OkFlag) {
            System.out.print("\nRoom Number: ");
            roomNum = sc.nextLine();
            index = findIndexByRmNum(roomNum);
            if(index != -1)
                OkFlag = true;
        }

        System.out.println("====================================");
        System.out.println("     Room Service for Room " +roomNum);
        System.out.println("=====================================");
        System.out.println("Status :"+room_services.get(index).getStatus());
    }

    public static double calculateServiceCost(Integer index)
    {
        double totalCost = 0.0;
        double orderCost = 0.0;
        for (order_Menu order: room_services.get(index).getOrder()) {
            orderCost = Room_Services_Menu_Controller.getPriceFromID(order.getMenu_ID());
            totalCost = totalCost + orderCost*order.getQuantity();
        }
        return totalCost;
    }

    private static void listRoomService()
    {
        System.out.println("===================================");
        System.out.println("Listing All Available Room Services");
        System.out.println("===================================");

        //Room_Service( String date, String time, String room_Num, String remarks, ArrayList<order_Menu> order )
        for(Integer i = 0 ; i < room_services.size(); i++)
        {
            System.out.println("ID: " + i+1);
            System.out.println("Date: "+ room_services.get(i).getDate());
            System.out.println("Time: "+ room_services.get(i).getTime());
            System.out.println("Room Number: "+ room_services.get(i).getRoomNum());
            System.out.println("Remarks: "+ room_services.get(i).getRemarks());
            System.out.println("Orders");

            System.out.println("ID      Item Name            Quantity");
            for (Integer j = 0; j < room_services.get(i).getOrder().size(); j++)
            {
                System.out.format("%-8s %-22s $%-10s\n", i, getItemNameFromID(i,j) , room_services.get(i).getOrder().get(j).getQuantity().toString());
            }

        }
    }

    public static void listRoomServiceOrder(Integer index)
    {
        ArrayList<order_Menu> order = room_services.get(index).getOrder();

        System.out.println("ID      Name                   Quantity");

        for(Integer i = 0; i <order.size(); i++)
        {
            System.out.format("%-7s %-25s %-13s\n",i,
                    Room_Services_Menu_Controller.getMenuNameFromID(order.get(i).getMenu_ID()),
                    order.get(i).getQuantity());
        }
    }

    private static String  getItemNameFromID(Integer i, Integer j)
    {
        return Room_Services_Menu_Controller.getMenuNameFromID(room_services.get(i).getOrder().get(j).getMenu_ID());
    }

    public static Integer findIndexByRmNum(String Room_Num)
    {
        for(Integer i = room_services.size() - 1; 0<=i; i--)
        {
            if(room_services.get(i).getRoomNum().equals(Room_Num))
            {
                return i;
            }
        }
        return -1;
    }

    //Room Services File Saver
    public static void saveData()
    {
        try
        {
            File_IO.writeSerializable(stored_File_Name,room_services);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    //Room Services File Loader
    public static void loadData()
    {
        try
        {
            room_services = (ArrayList<Room_Service>) File_IO.readDeserializable(stored_File_Name);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }


    //Print Room Service
    public static void printRoomService()
    {
        String roomNum = "";
        Integer index = -1;
        boolean OkFlag = false, done = false, cmpFlag;


        while (!OkFlag) {
            System.out.print("\nRoom Number: ");
            roomNum = sc.nextLine();
            index = findIndexByRmNum(roomNum);
            if(index != -1)
                OkFlag = true;
        }

        System.out.println("====================================");
        System.out.println("     Room Service for Room " +roomNum);
        System.out.println("=====================================");
        System.out.println("Date :"+room_services.get(index).getDate());
        System.out.println("Time :"+room_services.get(index).getTime());
        listRoomServiceOrder(index);
        System.out.println("Remark :"+room_services.get(index).getRemarks());
        System.out.println("Status :"+room_services.get(index).getStatus());
    }


}
