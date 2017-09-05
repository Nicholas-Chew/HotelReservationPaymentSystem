package controller;

import facade.File_IO;
import model.Room_Service_Menu;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by chewzhijie on 26/3/16.
 */
public class Room_Services_Menu_Controller {

    private final static String stored_File_Name = "room_Services_Menu";
    private static ArrayList<Room_Service_Menu> room_service_menus= new ArrayList<Room_Service_Menu>();

    private static Scanner sc;


    public  Room_Services_Menu_Controller()
    {
        try
        {
            sc = new Scanner(System.in);
            loadData();
        }
        finally
        {

            if(room_service_menus == null)
                room_service_menus = new ArrayList<Room_Service_Menu>();

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

            if(room_service_menus == null)
                room_service_menus = new ArrayList<Room_Service_Menu>();

        }
    }

    //Adding Menu Item
    public static void addMenuItem()
    {
        String name;
        Double price = 0.0;
        String decription;
        boolean Okflag = false;

        //Room_Service_Menu(String name, double price, String description)
        System.out.println("================");
        System.out.println("Adding Menu Item");
        System.out.println("================");

        //MENU NAME
        System.out.print("Menu Name: ");
        name = sc.nextLine();

        //MENU PRICE
        while (!Okflag) {
            try {
                System.out.print("Menu Price: ");
                price = Double.parseDouble(sc.nextLine());
                Okflag = true;
            }
            catch (Exception e)
            {
                System.out.println("Invalid Price Input!");
                Okflag = false;
            }
        }

        //MENU DESCRIPTION
        System.out.print("Menu Description: ");
        decription = sc.nextLine();


        room_service_menus.add(new Room_Service_Menu(name,price,decription));


    }

    //Updating Menu Item
    public static void updateMenuItem()
    {
        String name;
        Double price = 0.0;
        String decription;
        Integer index = 0;
        boolean Okflag = false;

        //Room_Service_Menu(String name, double price, String description)
        System.out.println("===========================");
        System.out.println("    Updating Menu Item");
        System.out.println("===========================");

        listAllMenu();

        while (!Okflag) {
            try {
                System.out.print("\nID you want to update: ");
                index = Integer.parseInt(sc.nextLine());

                if(index <= room_service_menus.size())
                    Okflag = true;
                else
                    System.out.println("No such ID "+ index);
            }
            catch (Exception e)
            {
                System.out.println("Invalid Input!");
                Okflag = false;
            }
        }

        System.out.println("=======================================");
        System.out.println("    Updating Menu ID "+index);
        System.out.println("    Enter '.' to retain the information");
        System.out.println("=======================================");

        index--;
        //MENU NAME
        System.out.print("Menu Name ("+ room_service_menus.get(index).getName()+ "): ");
        name = sc.nextLine();
        if(!name.equals("."))
            room_service_menus.get(index).setName(name);

        Okflag = false;
        //MENU PRICE
        while (!Okflag) {
            try {
                System.out.print("Menu Price ("+ room_service_menus.get(index).getPrice()+ ") -1 to retain value: ");
                price = Double.parseDouble(sc.nextLine());
                if(price != -1)
                    room_service_menus.get(index).setPrice(price);
                Okflag = true;
            }
            catch (Exception e)
            {
                System.out.println("Invalid Price Input!");
                Okflag = false;
            }
        }

        //MENU DESCRIPTION
        System.out.print("Menu Description ("+room_service_menus.get(index).getDescription()+ "): ");
        decription = sc.nextLine();
        if(!decription.equals("."))
            room_service_menus.get(index).setDescription(decription);

        System.out.println("\nDone Updating Menu Item!\n");

    }

    //Removing Menu Item
    public static void deleteMenuItem()
    {
        Integer index = 0;
        String choice;
        Boolean OkFlag = false;


        System.out.println("===========================");
        System.out.println("    Deleting Menu Item");
        System.out.println("===========================");
        listAllMenu();

        //MENU DESCRIPTION
        while (!OkFlag) {
            try {
                System.out.println("Deleting which ID's Menu? ");
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
            System.out.println("Sucessfully Deleted " + room_service_menus.get(index-1).getName());
            room_service_menus.remove(index-1);
        }
        else
        {
            System.out.println("Abort Delete");
        }
    }

    public static void listAllMenu()
    {

        System.out.println("==========");
        System.out.println("Menu Items ");
        System.out.println("==========");
        System.out.println("ID      Name                      Price          Description");

        for(Integer i = 0; i <room_service_menus.size(); i++)
        {
            System.out.format("%-7s %-25s $%-13.2f %-30s\n", i+1, room_service_menus.get(i).getName() , room_service_menus.get(i).getPrice(), room_service_menus.get(i).getDescription());
        }
    }

    public static double getPriceFromID(Integer ID)
    {
        return room_service_menus.get(ID-1).getPrice();
    }

    public static String getMenuNameFromID(Integer ID)
    {
        return room_service_menus.get(ID-1).getName();
    }


    public static Integer getMenuSize()
    {
        return room_service_menus.size();
    }

    //Room Services Menu File Saver
    public static void saveData()
    {
        try
        {
            File_IO.writeToXML(stored_File_Name,room_service_menus);

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
            room_service_menus = (ArrayList<Room_Service_Menu>) File_IO.readFromXML(stored_File_Name);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
