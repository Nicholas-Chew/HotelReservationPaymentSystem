package app;

import controller.*;

import java.util.Scanner;

/**
 * Created by chewzhijie on 27/3/16.
 */
public class HRPS {

    private static Scanner  sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        ClassA a = new ClassC();
        ClassG g = (ClassG) a;
        g.print("1");
        /*
        //Load All Data From XML and Ser Files. Create ArrayList when No Data To Load
        loadAllData();
        Integer choice = 0;

        System.out.println("===============================================");
        System.out.println("     Hotel Reservations and Payment System  ");
        System.out.println("                 Hotel Airdnd  ");
        System.out.println("===============================================");

        while (choice != 8)
        {
            System.out.print("\n1.Guest Function " +
                             "\n2.Reservation Function  " +
                             "\n3.Room Function" +
                             "\n4.Room Services Function" +
                             "\n5.Room Service Menu Item Function" +
                             "\n6.Current Room Status" +
                             "\n7.Room Occupancy Report"+
                             "\n8.Exit" +
                             "\nChoice:");

            try {

                choice = Integer.parseInt(sc.nextLine());
                switch (choice)
                {
                    case 1 : guestFunction();
                            break;
                    case 2 : reservationFunction();
                            break;
                    case 3 : roomFunction();
                            break;
                    case 4 : roomServiceFunction();
                            break;
                    case 5 : roomServiceMenuFunction();
                            break;
                    case 6 : Rooms_Controller.printAllRoomStatus();
                            break;
                    case 7 : Reservations_Controller.roomOccupancyReport();

                    default:
                        break;
                }

                updateWorker();

                //Save everything to ~/Data/*
                saveAllData();
            } catch (Exception e) {System.out.println(e.getMessage());}
        }
*/

    }

    private static void guestFunction()throws Exception
    {
        Integer choice;

        System.out.println("\n===============================================");
        System.out.println("                Guest Function");
        System.out.println("===============================================");


        System.out.print("1.Guest Check-In " +
                "\n2.Guest Check-Out " +
                "\n3.Add Guest  " +
                "\n4.Update Guest  " +
                "\n5.Search Guest " +
                "\nOther Numerical Number to Exit" +
                "\nChoice:");

        choice = Integer.parseInt(sc.nextLine());

        try {
            switch (choice)
            {
                case 1: Guests_Controller.guestCheckIn();
                    break;

                case 2: Guests_Controller.guestCheckOut();
                    break;

                case 3: Guests_Controller.addNewGuest();
                    break;

                case 4: Guests_Controller.updateGuest();
                    break;

                case 5: Guests_Controller.searchGuest();
                    break;
                default:
                    break;
            }
            } catch (Exception e) {throw new Exception(e);}//System.out.println(e.getMessage());}//System.out.println(e.getMessage());}


    }

    private static void reservationFunction()throws Exception
    {
        Integer choice;

        System.out.println("\n===============================================");
        System.out.println("            Reservation Function");
        System.out.println("===============================================");


        System.out.print("1.Add Reservation " +
                "\n2.Update Reservation  " +
                "\n3.Search Reservation " +
                "\n4.Print All Reservation " +
                "\nOther Numerical Number to Exit" +
                "\nChoice:");

        try {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice)
            {
                case 1: Reservations_Controller.addNewReservation();
                    break;

                case 2: Reservations_Controller.updateReservation();
                    break;

                case 3: Reservations_Controller.searchReservationByGuestName();
                    break;

                case 4: Reservations_Controller.printAllReservations();
                    break;

                default:
                    break;
            }
        } catch (Exception e) {throw new Exception(e);}//System.out.println(e.getMessage());}//System.out.println(e.getMessage());}


    }

    private static void roomFunction() throws Exception
    {
        Integer choice;

        System.out.println("\n===============================================");
        System.out.println("                Room Function");
        System.out.println("===============================================");


        System.out.print("1.Add Room Detail " +
                "\n2.Update Room Detail  " +
                "\n3.Remove Room Detail " +
                "\n4.Print Room Detail " +
                "\n5.Update Room Status " +
                "\nOther Numerical Number to Exit" +
                "\nChoice:");

        try {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice)
            {
                case 1: Rooms_Controller.addRoomDetail();
                    break;

                case 2: Rooms_Controller.editRoomDetail();
                    break;

                case 3: Rooms_Controller.removeRoomDetail();
                    break;

                case 4: Rooms_Controller.printRoomDetail();
                    break;

                case 5: Rooms_Controller.updateRoomSatus();
                    break;

                default:
                    break;
            }
        } catch (Exception e) { throw new Exception(e);}//System.out.println(e.getMessage());}//System.out.println(e.getMessage());}
    }

    private static void roomServiceFunction() throws Exception
    {
        Integer choice;

        System.out.println("\n===============================================");
        System.out.println("            Room Service Function");
        System.out.println("===============================================");


        System.out.print("1.Add Room Service " +
                "\n2.Update Room Service  " +
                "\n3.Remove Room Service " +
                "\n4.Current Room Service Status" +
                "\nOther Numerical Number to Exit" +
                "\nChoice:");

        try {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice)
            {
                case 1: Room_Services_Controller.addRoomService();
                    break;

                case 2: Room_Services_Controller.updateRoomService();
                    break;

                case 3: Room_Services_Controller.deleteRoomService();
                    break;

                case 4: Room_Services_Controller.printRoomServiceStatus();
                    break;

                default:
                    break;
            }
        } catch (Exception e) {throw new Exception(e);}//System.out.println(e.getMessage());}


    }

    private static void roomServiceMenuFunction() throws Exception
    {
        Integer choice;

        System.out.println("\n===============================================");
        System.out.println("          Room Service Menu Function");
        System.out.println("===============================================");


        System.out.print("1.Add Room Service Menu" +
                "\n2.Update Room Service Menu" +
                "\n3.Remove Room Service Menu" +
                "\nOther Numerical Number to Exit" +
                "\nChoice:");

        try {
            choice = Integer.parseInt(sc.nextLine());
            switch (choice)
            {
                case 1: Room_Services_Menu_Controller.addMenuItem();
                    break;

                case 2: Room_Services_Menu_Controller.updateMenuItem();
                    break;

                case 3: Room_Services_Menu_Controller.deleteMenuItem();
                    break;

                default:
                    break;
            }
        } catch (Exception e) {throw new Exception(e);}//System.out.println(e.getMessage());}//System.out.println(e.getMessage());}
    }

    private static void loadAllData()
    {
        Guests_Controller.tryLoad();
        Reservations_Controller.tryLoad();
        Room_Services_Controller.tryLoad();
        Room_Services_Menu_Controller.tryLoad();
        Rooms_Controller.tryLoad();
        Payment_Controller.tryLoad();
    }

    private static void saveAllData()
    {
        Guests_Controller.saveData();
        Reservations_Controller.saveData();
        Room_Services_Controller.saveData();
        Room_Services_Menu_Controller.saveData();
        Rooms_Controller.saveData();
        Payment_Controller.saveData();
    }

    private static void updateWorker()
    {
        Reservations_Controller.updateAllStatusWorker();
        Room_Services_Controller.updateAllStatusWorker();
    }
}
