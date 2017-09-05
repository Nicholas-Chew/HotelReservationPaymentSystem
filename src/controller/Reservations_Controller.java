package controller;

import facade.Data_Function;
import facade.File_IO;
import model.Reservation;
import model.Room;
import org.omg.CORBA.INTERNAL;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by chewzhijie on 25/3/16.
 */

public class Reservations_Controller {
    private final static String stored_File_Name = "reservation";
    private static ArrayList<Reservation> reservations= new ArrayList<>();
    private static Scanner sc;

    public static void tryLoad()
    {
        try
        {
            sc = new Scanner(System.in);
            loadData();
        }
        finally
        {
            if(reservations == null)
                reservations = new ArrayList<Reservation>();
        }
    }

    //Create New Reservation
    public static void addNewReservation()
    {
        String Guest_ID = null;
        String Room_No = null;
        String date_Check_In;
        String date_Check_Out = null;
        Integer num_Of_Adult;
        Integer num_Of_Children;
        Reservation.reservation_Status status;
        String[] CmpStrings;
        boolean cmpFlag = false;

        String datePatternRegex = "[0-3][0-9]/[0-1][0-9]/[1-2][0-9][0-9][0-9]";

        //Reservation(String Guest_ID, String Room_No, String date_Check_In, String date_Check_Out,
        //              Integer num_Of_Adult, Integer num_Of_Children, reservation_Status status)
        System.out.println("=================================================");
        System.out.println("Creating New Reservation. Please Fill In The Form");
        System.out.println("=================================================");

        ////////////
        //Guest ID//
        ////////////


        while (!cmpFlag) {
            System.out.print("Guest's Passport/Driving Licence Identity Number: ");
            Guest_ID = sc.nextLine();
            cmpFlag = Guests_Controller.SearchGuestByID(Guest_ID) != -1;

            if (!cmpFlag) {
                System.out.println("No Guest Found!");
                System.out.println("Want to add guest? ('Y'or'N')");
                Guest_ID = sc.nextLine();
                if(Guest_ID.toUpperCase().equals("Y"))
                    Guests_Controller.addNewGuest();
            }
        }


        ////////////
        //Room Num//
        ////////////
        cmpFlag = false;
        while (!cmpFlag) {
            cmpFlag = false;
            System.out.print("Room Number: ");

            Room_No = sc.nextLine();
            cmpFlag = Rooms_Controller.findRoom(Room_No) != -1;


        }


        /////////////////
        //Date Check In//
        /////////////////
        do {
            cmpFlag = false;
            System.out.print("Check In Date (DD/MM/YYYY): ");
            date_Check_In = sc.nextLine();
            if (!Data_Function.regularExpressionMatch(datePatternRegex,date_Check_In))
            {
                System.out.println(date_Check_In+"  does not match with DD/MM/YYYY!");
                cmpFlag = true;
            }
        }
        while(cmpFlag);


        //////////////////
        //Date Check Out//
        //////////////////
        do {
            cmpFlag = false;
            System.out.print("Check Out Date (DD/MM/YYYY): ");
            date_Check_Out = sc.nextLine();
            if (!Data_Function.regularExpressionMatch(datePatternRegex,date_Check_Out))
            {
                System.out.println(date_Check_Out+"  does not match with DD/MM/YYYY!");
                cmpFlag = true;
            }
        }
        while(cmpFlag);

        ////////////////////
        //Number Of Adults//
        ////////////////////
        System.out.print("Number Of Adults: ");
        num_Of_Adult = Integer.parseInt(sc.nextLine());


        //////////////////////
        //Number Of Children//
        //////////////////////
        System.out.print("Number Of Children: ");
        num_Of_Children = Integer.parseInt(sc.nextLine());


        ///////////
        //Status//
        //////////
        //If room is Occupied, Reserved, Under_Maintenance Status will be in wait list else confirm
        if(Rooms_Controller.getStatus(Room_No) == Room.room_Status.Vacant)
            status = Reservation.reservation_Status.Confirm;
        else
            status = Reservation.reservation_Status.In_Waitlist;

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();

        reservations.add(new Reservation(Guest_ID, Room_No, date_Check_In, date_Check_Out,df.format(dateobj), num_Of_Adult, num_Of_Children, status));
        printReservationAcknowledgement(Guest_ID,Room_No,date_Check_In,date_Check_Out,df.format(dateobj),num_Of_Adult,num_Of_Children, status);
    }


    //Create New Reservation
    public static void addWalkInReservation(String CheckInDate, String Guest_ID)
    {
        String Room_No = null;
        String date_Check_In;
        String date_Check_Out = null;
        Integer num_Of_Adult;
        Integer num_Of_Children;
        Reservation.reservation_Status status;
        String[] CmpStrings;
        boolean cmpFlag = false;

        String datePatternRegex = "[0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9]";

        //Reservation(String Guest_ID, String Room_No, String date_Check_In, String date_Check_Out,
        //              Integer num_Of_Adult, Integer num_Of_Children, reservation_Status status)
        System.out.println("================================================");
        System.out.println("       Walk-In. Please Fill In The Form");
        System.out.println("================================================");

        ////////////
        //Guest ID//
        ////////////


        while (!cmpFlag) {
            //System.out.print("Guest Identity Number: ");
           // Guest_ID = sc.nextLine();
            cmpFlag = Guests_Controller.SearchGuestByID(Guest_ID) != -1;

            if (!cmpFlag) {
                System.out.println("No Guest Found!");
                System.out.println("Want to add guest? ('Y'or'N')");
                Guest_ID = sc.nextLine();
                if(Guest_ID.toUpperCase().equals("Y"))
                    Guests_Controller.addNewGuest();
            }
        }


        ////////////
        //Room Num//
        ////////////
        cmpFlag = false;
        while (!cmpFlag) {
            cmpFlag = false;
            System.out.print("Room Number: ");

            Room_No = sc.nextLine();
            cmpFlag = Rooms_Controller.findRoom(Room_No) != -1;


        }


        /////////////////
        //Date Check In//
        /////////////////
        date_Check_In = CheckInDate;

        /////////////////
        //Date Check Out//
        /////////////////
        do {
            cmpFlag = false;
            System.out.print("Check Out Date (DD/MM/YYYY): ");
            date_Check_Out = sc.nextLine();
            if (!Data_Function.regularExpressionMatch(datePatternRegex,date_Check_Out))
            {
                System.out.println(date_Check_Out+"  does not match with DD/MM/YYYY!");
                cmpFlag = true;
            }
        }
        while(cmpFlag);


        ////////////////////
        //Number Of Adults//
        ////////////////////
        System.out.print("Number Of Adults: ");
        num_Of_Adult = Integer.parseInt(sc.nextLine());


        //////////////////////
        //Number Of Children//
        //////////////////////
        System.out.print("Number Of Children: ");
        num_Of_Children = Integer.parseInt(sc.nextLine());


        ///////////
        //Status//
        //////////
        //If room is Occupied, Reserved, Under_Maintanance Status will be in wait list else confirm

        status = Reservation.reservation_Status.Check_In;



        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();


        reservations.add(new Reservation(Guest_ID, Room_No, date_Check_In, date_Check_Out,df.format(dateobj), num_Of_Adult, num_Of_Children, status));
        Rooms_Controller.updateRoomSatus(Room_No,Room.room_Status.Occupied);
    }

    //Update Reservation
    public static void updateReservation()
    {
        Integer reservation_Index;
        String Guest_ID = null;
        String Room_No = null;
        String date_Check_In;
        String date_Check_Out = null;
        Integer num_Of_Adult;
        Integer num_Of_Children;
        Reservation.reservation_Status status;
        String[] CmpStrings;
        boolean cmpFlag = false;

        String datePatternRegex = "[0-3][0-9]/[0-1][0-9]/[1-2][0-9][0-9][0-9]";

        //Reservation(String Guest_ID, String Room_No, String date_Check_In, String date_Check_Out,
        //              Integer num_Of_Adult, Integer num_Of_Children, reservation_Status status)
        System.out.println("\n==================================");
        System.out.println("        Editing Reservation");
        System.out.println("==================================");

        ////////////
        //Guest ID//
        ////////////

        System.out.print("Guest's Passport/Driving Licence Identity Number: ");
        Guest_ID = sc.nextLine();




        reservation_Index = Reservations_Controller.searchReservationByGuestID(Guest_ID);

        if(reservation_Index == -1)
        {
            System.out.println("No reservation found!");
            return;
        }


        ////////////
        //Room Num//
        ////////////
        cmpFlag = false;
        while (!cmpFlag) {
            cmpFlag = false;
            System.out.print("Room Number ("+reservations.get(reservation_Index).getRoomNo()+"): ");

            Room_No = sc.nextLine();
            cmpFlag = Rooms_Controller.findRoom(Room_No) != -1;
        }

            reservations.get(reservation_Index).setRoomNo(Room_No);

        /////////////////
        //Date Check In//
        /////////////////
        do {
            cmpFlag = false;
            System.out.print("Check In Date (DD/MM/YYYY) ("+reservations.get(reservation_Index).getDateCheckIn()+"): ");
            date_Check_In = sc.nextLine();
            if (!Data_Function.regularExpressionMatch(datePatternRegex,date_Check_In))
            {
                System.out.println(date_Check_In+"  does not match with DD/MM/YYYY!");
                cmpFlag = true;
            }
        }
        while(cmpFlag);

        reservations.get(reservation_Index).setDateCheckIn(date_Check_In);

        //////////////////
        //Date Check Out//
        //////////////////
        do {
            cmpFlag = false;
            System.out.print("Check Out Date (DD/MM/YYYY)("+reservations.get(reservation_Index).getDateCheckOut()+"): ");
            date_Check_Out = sc.nextLine();
            if (!Data_Function.regularExpressionMatch(datePatternRegex,date_Check_Out))
            {
                System.out.println(date_Check_Out+"  does not match with DD/MM/YYYY!");
                cmpFlag = true;
            }
        }
        while(cmpFlag);

        reservations.get(reservation_Index).setDateCheckOut(date_Check_Out);

        ////////////////////
        //Number Of Adults//
        ////////////////////
        System.out.print("Number Of Adults("+reservations.get(reservation_Index).getNumOfAdult()+"): ");
        num_Of_Adult = Integer.parseInt(sc.nextLine());


        reservations.get(reservation_Index).setNumOfAdult(num_Of_Adult);


        //////////////////////
        //Number Of Children//
        //////////////////////
        System.out.print("Number Of Children ("+reservations.get(reservation_Index).getNumOfChildren()+"): ");
        num_Of_Children = Integer.parseInt(sc.nextLine());

        reservations.get(reservation_Index).setNumOfChildren(num_Of_Children);

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();

        reservations.get(reservation_Index).setReservationDate(df.format(dateobj));


        printReservationAcknowledgement(Guest_ID,Room_No,date_Check_In,date_Check_Out,reservations.get(reservation_Index).getReservationDate(),num_Of_Adult,num_Of_Children,reservations.get(reservation_Index).getStatus());

    }

    public static void roomOccupancyReport()
    {
        String dateToCheck;
        boolean cmpFlag;
        Integer s = 0, db = 0, du = 0, vip = 0;
        double so, dbo, duo, vipo;

        String datePatternRegex = "[0-3][0-9]/[0-1][0-9]/[1-2][0-9][0-9][0-9]";

        do {
            cmpFlag = false;
            System.out.print("Date to check (DD/MM/YYYY): ");
            dateToCheck = sc.nextLine();
            if (!Data_Function.regularExpressionMatch(datePatternRegex,dateToCheck))
            {
                System.out.println(dateToCheck+"  does not match with DD/MM/YYYY!");
                cmpFlag = true;
            }
        }
        while(cmpFlag);

        System.out.println("\n===========================================");
        System.out.println("        Room occupancy report for "+dateToCheck);
        System.out.println("===========================================");

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();
        String tdyDate = df.format(dateobj);

        for(int i = 0; i < reservations.size(); i++)
        {
            if(Data_Function.betweenDate(tdyDate,reservations.get(i).getDateCheckIn(),reservations.get(i).getDateCheckOut(),"dd/MM/yyyy"))
            {
                switch (Rooms_Controller.getRoomType(reservations.get(i).getRoomNo()))
                {
                    case Single: s++;
                        break;
                    case Double: db++;
                        break;
                    case Deluxe: du++;
                        break;
                    case VIP_Suite: vip++;
                        break;
                }
            }
        }

        so = s / 10.0 * 100.0;
        dbo = db / 16.0 * 100.0;
        duo = du / 20.0 * 100.0;
        vipo = vip / 2.0 * 100.0;


        System.out.format("Single occupancy rate : %.0f", so);
        System.out.print("%\n");
        System.out.format("Double occupancy rate : %.0f", dbo);
        System.out.print("%\n");
        System.out.format("Deluxe occupancy rate : %.0f", duo);
        System.out.print("%\n");
        System.out.format("VIP Suite occupancy rate : %.0f", vipo);
        System.out.print("%\n");
    }

    //Search Reservation by Guest Name
    public static void searchReservationByGuestName()
    {

        Integer reserve_index = -1;
        String GuestID;

        System.out.println("\n=======================================");
        System.out.println("Searching Reservation By Guest Identity");
        System.out.println("=======================================");
        System.out.print("Guest Passport/Driving Licence Number: ");
        GuestID = sc.nextLine();

        for (Integer i = 0 ; i <reservations.size(); i++)
        {
            if(reservations.get(i).getGuestID().equals(GuestID))
                reserve_index =  i;
        }


        if(reserve_index != -1)
            printReservation(reserve_index);
        else
            System.out.println("Reservation with "+GuestID+" Not Found!");


    }

    public static Integer searchReservationByGuestID(String GuestID)
    {
        Integer reserve_index = -1;
        for (Integer i = 0 ; i <reservations.size(); i++)
        {
            if(reservations.get(i).getGuestID().equals(GuestID))
                reserve_index =  i;
        }


        return reserve_index;


    }

    public static Integer searchReservationByRoomNDate(String Room ,String Date)
    {
        Integer reserve_index = -1;
        for (Integer i = 0 ; i <reservations.size(); i++)
        {
            if(reservations.get(i).getRoomNo().equals(Room)&&reservations.get(i).getDateCheckOut().equals(Date))
                reserve_index =  i;
        }


        return reserve_index;


    }

    public static void updateReservationStatus(Integer index,Reservation.reservation_Status status)
    {
        reservations.get(index).setStatus(status);
    }

    public static void printReservation(Integer index)
    {
        System.out.println("Guest Name: "+reservations.get(index).getGuestID());
        System.out.println("Room Number: "+reservations.get(index).getRoomNo());
        System.out.println("Date Check In : "+reservations.get(index).getDateCheckIn());
        System.out.println("Date Check Out : "+reservations.get(index).getDateCheckOut());
        System.out.println("Number Of Adult : "+reservations.get(index).getNumOfAdult());
        System.out.println("Number Of Children : "+reservations.get(index).getNumOfChildren());
        System.out.println("Status: "+reservations.get(index).getStatus());
    }

    public static void printAllReservations()
    {

        //Reservation(String Guest_ID, String Room_No, String date_Check_In, String date_Check_Out,
        //              Integer num_Of_Adult, Integer num_Of_Children, reservation_Status status)

        System.out.println("===================================");
        System.out.println("        All Reservations ");
        System.out.println("===================================");

        for (int i  = 0; i <reservations.size(); i++)
        {
            System.out.println("Index: "+ i);
            System.out.println("=========");
            printReservation(i);
            System.out.println();
        }
        System.out.println("\n");
    }

    public static Integer getLengthOfStay(Integer index)
    {
        return Data_Function.daysDifference(reservations.get(index).getDateCheckIn(),reservations.get(index).getDateCheckOut(),"dd/MM/yyyy");
    }

    public static void updateAllStatusWorker()
    {

        String dateIn;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Date dateobj = new Date();

        for (int i = 0; i<reservations.size(); i++)
        {
            dateIn = reservations.get(i).getDateCheckIn();

            int hoursDiff = Data_Function.hoursDifference(dateIn+" 15:00",df.format(dateobj), "dd/MM/yyyy hh:mm");
            if (hoursDiff >=1)
            {
                if(reservations.get(i).getStatus() != Reservation.reservation_Status.Check_Out)
                    updateReservationStatus(i, Reservation.reservation_Status.Expired);
            }

            int dateDiff = Data_Function.daysDifference(dateIn,df1.format(dateobj), "dd/MM/yyyy");
            if(dateDiff == 0)
            {
                if(Rooms_Controller.getStatus(reservations.get(i).getRoomNo()) == Room.room_Status.Vacant)
                {
                    Rooms_Controller.updateRoomSatus(reservations.get(i).getRoomNo(), Room.room_Status.Reserved);
                }
            }
        }
    }

    public static String getRoomNumFromIndex(Integer index)
    {
        return reservations.get(index).getRoomNo();
    }

    public static String getDate_In(Integer index)
    {
        return reservations.get(index).getDateCheckIn();
    }

    public static String getDate_Out(Integer index)
    {
        return reservations.get(index).getDateCheckOut();
    }

    public static String getGuestID(Integer index)
    {
        return reservations.get(index).getGuestID();
    }

    private static void deleteReservation(Integer index)
    {
        try
        {
            printReservation(index);
            System.out.print("Deleting the record above ('Y' or 'N')? ");
            if(sc.nextLine().equals("Y"))
                reservations.remove(index);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Index not in range!");
        }
    }

    private static void printReservationAcknowledgement(String Guest_ID, String Room_No, String date_Check_In, String date_Check_Out,
                                                        String reservation_Date, Integer num_Of_Adult, Integer num_Of_Children, Reservation.reservation_Status status)
    {
        System.out.println("==========================================");
        System.out.println("         Acknowledgement Receipts");
        System.out.println("==========================================");
        System.out.println("Guest Identity Number: "+Guest_ID);
        System.out.println("Date of reservation: "+reservation_Date);
        System.out.println("Date check-in: "+date_Check_In);
        System.out.println("Date check-out: "+date_Check_Out);
        System.out.println("Number of adult: "+num_Of_Adult);
        System.out.println("Number of children: "+num_Of_Children);
        System.out.println("Room Number: "+Room_No);
        System.out.println("Reservation Status: "+ status);

        System.out.format ("Price:  %.2f\n",calculatePrice(date_Check_In,date_Check_Out,Room_No));
    }

    private static double calculatePrice(String date_Check_In,String date_Check_Out, String Room_No)
    {
        Integer num_Day_Stay = Data_Function.daysDifference(date_Check_In,date_Check_Out,"dd/MM/yyyy");
        Integer numWeekend = Data_Function.weekendsInDate(date_Check_In, date_Check_Out,"dd/MM/yyyy");

        double roomCost = 1.0;
        double droomCost = 1.0;
        Room.room_Type type = Rooms_Controller.getRoomType(Room_No);

        switch (type)
        {
            case Single: roomCost = 100.0;
                droomCost = 80;
                break;
            case Double: roomCost = 180.0;
                droomCost = 145.0;
                break;
            case Deluxe: roomCost = 280.0;
                droomCost = 220.0;
                break;
            case VIP_Suite: roomCost = 500.0;
                droomCost = 400.0;
        }


        return droomCost * (num_Day_Stay - numWeekend) + roomCost*numWeekend ;
    }

    public static void saveData()
    {
        try
        {
            File_IO.writeSerializable(stored_File_Name,reservations);

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public static void loadData()
    {
        try
        {
            reservations = (ArrayList<Reservation>) File_IO.readDeserializable(stored_File_Name);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
