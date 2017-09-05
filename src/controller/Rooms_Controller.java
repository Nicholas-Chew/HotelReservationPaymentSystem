package controller;

import java.util.ArrayList;
import java.util.Scanner;

import facade.Data_Function;
import facade.File_IO;
import model.Room;

public class Rooms_Controller {

	private final static String stored_File_Name = "rooms";
	private static ArrayList<Room> rooms;
	private static Scanner sc;
	
	//Constructor Load File if exists
	public Rooms_Controller()
	{
		 try
		 {
			 sc = new Scanner(System.in);
			 loadData();
		 }
		 finally
		 {
			 if(rooms == null)
				 rooms = new ArrayList<Room>();
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
            if(rooms == null)
                rooms = new ArrayList<>();
        }
    }

    //Printing All Room Static
    public static void printAllRoomStatus()
    {
        Integer single_count = 0;
        Integer v_single_count = 0;
        ArrayList<String> v_single = new ArrayList<>();
        ArrayList<String> o_single = new ArrayList<>();
        ArrayList<String> r_single = new ArrayList<>();
        ArrayList<String> u_single = new ArrayList<>();

        Integer double_count = 0;
        Integer v_double_count = 0;
        ArrayList<String> v_double = new ArrayList<>();
        ArrayList<String> o_double = new ArrayList<>();
        ArrayList<String> r_double = new ArrayList<>();
        ArrayList<String> u_double = new ArrayList<>();

        Integer deluxe_count = 0;
        Integer v_deluxe_count = 0;
        ArrayList<String> v_deluxe = new ArrayList<>();
        ArrayList<String> o_deluxe = new ArrayList<>();
        ArrayList<String> r_deluxe = new ArrayList<>();
        ArrayList<String> u_deluxe = new ArrayList<>();

        Integer vip_count = 0;
        Integer v_vip_count = 0;
        ArrayList<String> v_vip = new ArrayList<>();
        ArrayList<String> o_vip = new ArrayList<>();
        ArrayList<String> r_vip = new ArrayList<>();
        ArrayList<String> u_vip = new ArrayList<>();


        System.out.println("\n===============================================");
        System.out.println("                Room Statistic ");
        System.out.println("===============================================");


        //Calculate room count and room vacant
        for (Room r:rooms) {
            switch (r.getRoomType())
            {
                case Single :
                    single_count++;
                    switch (r.getRoomStatus())
                    {
                        case Vacant:
                            v_single_count++;
                            v_single.add(r.getRoomNo());
                            break;
                        case Occupied:
                            o_single.add(r.getRoomNo());
                            break;
                        case Reserved:
                            r_single.add(r.getRoomNo());
                            break;
                        case Under_Maintain:
                            u_single.add(r.getRoomNo());
                            break;
                    }
                    break;

                case Double :
                    double_count++;
                    switch (r.getRoomStatus())
                    {
                        case Vacant:
                            v_double_count++;
                            v_double.add(r.getRoomNo());
                            break;
                        case Occupied:
                            o_double.add(r.getRoomNo());
                            break;
                        case Reserved:
                            r_double.add(r.getRoomNo());
                            break;
                        case Under_Maintain:
                            u_double.add(r.getRoomNo());
                            break;
                    }
                    break;

                case Deluxe:
                    deluxe_count++;
                    switch (r.getRoomStatus())
                    {
                        case Vacant:
                            v_deluxe_count++;
                            v_deluxe.add(r.getRoomNo());
                            break;
                        case Occupied:
                            o_deluxe.add(r.getRoomNo());
                            break;
                        case Reserved:
                            r_deluxe.add(r.getRoomNo());
                            break;
                        case Under_Maintain:
                            u_deluxe.add(r.getRoomNo());
                            break;
                    }
                    break;

                case VIP_Suite:
                    vip_count++;
                    switch (r.getRoomStatus())
                    {
                        case Vacant:
                            v_vip_count++;
                            v_vip.add(r.getRoomNo());
                            break;
                        case Occupied:
                            o_vip.add(r.getRoomNo());
                            break;
                        case Reserved:
                            r_vip.add(r.getRoomNo());
                            break;
                        case Under_Maintain:
                            u_vip.add(r.getRoomNo());
                            break;
                    }
                    break;
            }
        }

        if(single_count != 0)
        {
            System.out.println("Single : " + v_single_count + " out of " + single_count);

            if(v_single.size() != 0) {
                System.out.print("Vacant :");
                Data_Function.printAllFromArray(v_single);
            }
            if(o_single.size() != 0) {
                System.out.print("\nOccupied :");
                Data_Function.printAllFromArray(o_single);
            }
            if(r_single.size() != 0) {
                System.out.print("\nReserved :");
                Data_Function.printAllFromArray(r_single);
            }
            if(u_single.size() != 0) {
                System.out.print("\nUnder Maintenance :");
                Data_Function.printAllFromArray(u_single);
            }
            System.out.println("\n");

        }

        if(double_count != 0)
        {
            System.out.println("Double : " + v_double_count + " out of " + double_count);

            if(v_double.size() != 0) {
                System.out.print("Vacant :");
                Data_Function.printAllFromArray(v_double);
            }
            if(o_double.size() != 0) {
                System.out.print("\nOccupied :");
                Data_Function.printAllFromArray(o_double);
            }
            if(r_double.size() != 0) {
                System.out.print("\nReserved :");
                Data_Function.printAllFromArray(r_double);
            }
            if(u_double.size() != 0) {
                System.out.print("\nUnder Maintenance :");
                Data_Function.printAllFromArray(u_double);
            }
            System.out.println("\n");
        }

        if(deluxe_count != 0)
        {
            System.out.println("Deluxe : " + v_deluxe_count + " out of " + deluxe_count);

            if(v_deluxe.size() != 0) {
                System.out.print("Vacant :");
                Data_Function.printAllFromArray(v_deluxe);
            }
            if(o_deluxe.size() != 0) {
                System.out.print("\nOccupied :");
                Data_Function.printAllFromArray(o_deluxe);
            }
            if(r_deluxe.size() != 0) {
                System.out.print("\nReserved :");
                Data_Function.printAllFromArray(r_deluxe);
            }
            if(u_deluxe.size() != 0) {
                System.out.print("\nUnder Maintenance :");
                Data_Function.printAllFromArray(u_deluxe);
            }

            System.out.println("\n");
        }

        if(vip_count != 0)
        {
            System.out.println("VIP Suite : " + v_vip_count + " out of " + vip_count);

            if(v_vip.size() != 0) {
                System.out.print("Vacant :");
                Data_Function.printAllFromArray(v_vip);
            }
            if(o_vip.size() != 0) {
                System.out.print("\nOccupied :");
                Data_Function.printAllFromArray(o_vip);
            }
            if(r_vip.size() != 0) {
                System.out.print("\nReserved :");
                Data_Function.printAllFromArray(r_vip);
            }
            if(u_vip.size() != 0) {
                System.out.print("\nUnder Maintenance :");
                Data_Function.printAllFromArray(u_vip);
            }
            System.out.println("\n");
        }

    }

    //Updating Room Status
    public static void updateRoomSatus()
    {
        String[] CmpStrings;
        boolean cmpFlag;
        String Status_Str;
        Room.room_Status Status;

        Integer index = findRoom();
        System.out.print("Current Room Status: ");

        CmpStrings = new String[]{"1","2","3","4"};
        do
        {
            cmpFlag = false;
            System.out.print("\nStatus Type \n1 Vacant\n2 Occupied\n3 Reserved\n4 Under Maintenance\nEnter Your Choice:");
            Status_Str =  sc.nextLine();


            if(!Data_Function.stringContainsItems(Status_Str,CmpStrings))
            {
                System.out.println(Status_Str + " not in the list!");
                cmpFlag = true;
            }
        }while(cmpFlag);
        Status = Room.room_Status.values()[Integer.parseUnsignedInt(Status_Str)-1];

        rooms.get(index).setRoomStatus(Status);
    }

    public static void updateRoomSatus(String roomNum, Room.room_Status status)
    {
        rooms.get(findRoom(roomNum)).setRoomStatus(status);
    }

    //Adding New Room Detail
    public static void addRoomDetail()
    {
        System.out.println("===============================================");
        System.out.println("      Adding Room Detail for Room ");
        System.out.println("===============================================");

        Integer index = findRoom();
        String rm_Detail_Name;

        System.out.println("");
        System.out.println("Adding Room Detail for Room "+rooms.get(index).getRoomNo());
        System.out.println("");
        System.out.print("Detail Name: ");
        rm_Detail_Name = sc.nextLine();


        rooms.get(index).add_Room_Detail(rm_Detail_Name);
    }

    //Remove Room Detail
    public static void removeRoomDetail()
    {
        String detailName;
        System.out.println("===============================================");
        System.out.println("      Removing Room Detail for Room ");
        System.out.println("===============================================");

        Integer rm_Index = findRoom();

        if(rm_Index != -1) {
            printRoomDetail(rm_Index);
            System.out.print("Detail Name: ");
            detailName = sc.nextLine();

            Integer detail_Index = findDetail(rm_Index, detailName);

            if(detail_Index != -1) {
                rooms.get(rm_Index).delete_Room_Detail(detail_Index+1);
                System.out.println("Successfully remove " + detailName + " from " + rooms.get(rm_Index).getRoomNo());
            }
            else {
                System.out.println("No Such Detail!");
            }
        }
        else
        {
            System.out.println("Room Not Found!");
        }
    }

    //Edit Room Detail
    public static void editRoomDetail()
    {
        String detailName;
        String editedDetailName;
        System.out.println("\n===============================================");
        System.out.println("         Edit Room Detail for Room ");
        System.out.println("===============================================");

        Integer rm_Index = findRoom();

        if(rm_Index != -1) {
            printRoomDetail(rm_Index);
            System.out.print("Detail Name: ");
            detailName = sc.nextLine();

            Integer detail_Index = findDetail(rm_Index, detailName);

            if(detail_Index != -1) {
                System.out.print("New Detail Name: ");
                editedDetailName = sc.nextLine();
                rooms.get(rm_Index).getRoomDetail().set(detail_Index,editedDetailName);
                System.out.println("Successfully edit " + detailName +" to " + editedDetailName+" from " + rooms.get(rm_Index).getRoomNo());
            }
            else {
                System.out.println("No Such Detail!");
            }
        }
        else
        {
            System.out.println("Room Not Found!");
        }
    }

    //Print Room Detail
    public static void printRoomDetail()
    {
        System.out.println("\n===============================================");
        System.out.println("         Print Room Detail for Room ");
        System.out.println("===============================================");

        Integer rm_Index = findRoom();

        if(rm_Index != -1) {
            printRoomDetail(rm_Index);

        }
        else
        {
            System.out.println("Room Not Found!");
        }
    }

    public static Room.room_Status getStatus(String RoomNum)
    {
        Integer index = findRoom(RoomNum);

        return rooms.get(index).getRoomStatus();
    }

    public static Room.room_Type getRoomType(String RoomNum)
    {
        Integer index = findRoom(RoomNum);

        return rooms.get(index).getRoomType();
    }

    public static Integer findRoom(String RoomNum)
    {
        Integer i;

        //For each Room in Rooms
        for(i = 0; i < rooms.size(); i++)
        {

            if(rooms.get(i).getRoomNo().toLowerCase().equals(RoomNum.toLowerCase()))
            {
                return i;
            }
        }

        System.out.println("No Room "+RoomNum+" FOUND!");
        return -1;

    }

    private static Integer findRoom()
    {
        System.out.print("Enter Room Number: ");
        String RoomNum = sc.nextLine();
        Integer i;

        //For each Room in Rooms
        for(i = 0; i < rooms.size(); i++)
        {

            if(rooms.get(i).getRoomNo().toLowerCase().equals(RoomNum.toLowerCase()))
            {
                return i;
            }
        }

        System.out.println("No Room "+RoomNum+" FOUND!");
        return -1;

    }

    private static Integer findDetail(Integer room_Index,String detailName)
    {
        Integer i;

        //For each room detail in room Details
        for(i = 0; i < rooms.get(room_Index).getRoomDetail().size(); i++)
        {

            if(rooms.get(room_Index).getRoomDetail().get(i).toLowerCase().equals(detailName.toLowerCase()))
            {
                return i;
            }
        }

        System.out.println("No Detail "+detailName+" FOUND!");
        return -1;
    }

    private static void printRoomDetail(Integer index)
    {

        System.out.println("\n===============================================");
        System.out.println("Room Detail for Room "+rooms.get(index).getRoomNo()+" , "+rooms.get(index).getRoomType() + " , "+rooms.get(index).getRoomStatus());
        System.out.println("===============================================");
        System.out.println("Detail Name  ");
        System.out.println("-----------  ");

        for (Integer i = 0; i<rooms.get(index).getRoomDetail().size();i++) {

            System.out.format("%-12s\n",rooms.get(index).getRoomDetail().get(i));

        }
    }


    public static void addNewRoom()
    {
        String Room_No;
        String Rm_Type_Str;
        Room.room_Type Rm_Type;
        String Rm_Stat_Str;
        Room.room_Status Rm_Stat;
        String rm_Detail_name;
        String rm_Detail_status;
        ArrayList<String> room_Detail = new ArrayList<>();
        String[] CmpStrings;
        boolean cmpFlag;

        //Room(String room_No, room_Type rm_Type, room_Status rm_Stat)

        System.out.println("===============================================");
        System.out.println("Creating New Room. \nPlease Fill Up The Form :");
        System.out.println("===============================================");

        ///////////
        //Room_No//
        ///////////
        System.out.print("Room Number: ");
        Room_No = sc.nextLine();

        /////////////////
        //Identity Type//
        /////////////////
        CmpStrings = new String[]{"1","2","3","4"};
        do
        {
            cmpFlag = false;
            System.out.print("\nRoom Type \n1 Single\n2 Double\n3 Deluxe\n4 VIP_Suite\nEnter Your Choice:");
            Rm_Type_Str =  sc.nextLine();


            if(!Data_Function.stringContainsItems(Rm_Type_Str,CmpStrings))
            {
                System.out.println(Rm_Type_Str + " not in the list!");
                cmpFlag = true;
            }
        }while(cmpFlag);
        Rm_Type = Room.room_Type.values()[Integer.parseUnsignedInt(Rm_Type_Str)-1];

        //Data Dumping for Java garbrage collection to destroy
        CmpStrings = null;

        System.out.println("\nRoom Detail:");
        do
        {
            System.out.print("Room Detail Name (Enter 1 to stop adding) : ");
            rm_Detail_name = sc.nextLine();

            if(!rm_Detail_name.equals("1"))
                room_Detail.add(rm_Detail_name);
        }while (!rm_Detail_name.equals("1"));

        System.out.println();
        rooms.add(new Room(Room_No, Rm_Type, Room.room_Status.Vacant, room_Detail));
    }

    public static void deleteRoom()
    {
        Integer index = findRoom();
        try
        {
            rooms.remove(index);
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.print("No Room Found!");
        }
    }


	public static void saveData()
	{
		try
		{
			File_IO.writeToXML(stored_File_Name,rooms);

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
			rooms = (ArrayList<Room>) File_IO.readFromXML(stored_File_Name);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
}
