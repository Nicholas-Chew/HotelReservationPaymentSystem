package controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import facade.Data_Function;
import model.*;
import facade.File_IO;
import facade.Data_Function;

public class Guests_Controller {

	private final static String stored_File_Name = "guests";
	private static ArrayList<Guest> guests;
	private static ArrayList<Integer> temp_guests_id;
	private static Scanner  sc;

	//Constructor Load File if exists
	public Guests_Controller()
	{
		 try
		 {
			 sc = new Scanner(System.in);
			 loadData();
		 }
		 finally
		 {
			 if(guests == null)
				 guests= new ArrayList<Guest>();
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
			if(guests == null)
				guests= new ArrayList<Guest>();
		}
	}

	//Check In
	public static void guestCheckIn()
	{
		boolean cmpFlag = false;
		String Guest_ID = null;
		Integer reservation_Index;

		System.out.println("\n=========================================");
		System.out.println("			Checking In Guest");
		System.out.println("=========================================");

		while (!cmpFlag) {
			System.out.print("Guest Passport/Driving Licence Identity Number: ");
			Guest_ID = sc.nextLine();
			cmpFlag = SearchGuestByID(Guest_ID) != -1;

			if (!cmpFlag) {
				System.out.println("No Guest Found!");
				System.out.println("Want to add guest? ('Y'or'N')");
				Guest_ID = sc.nextLine();
				if(Guest_ID.toUpperCase().equals("Y"))
					addNewGuest();
			}
		}


		reservation_Index = Reservations_Controller.searchReservationByGuestID(Guest_ID);
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dateobj = new Date();

		if(reservation_Index != -1)
		{
			if(Reservations_Controller.getDate_In(reservation_Index).equals(df.format(dateobj))) {
				//Update Reservation Status
				Reservations_Controller.updateReservationStatus(reservation_Index, Reservation.reservation_Status.Check_In);

				//Update Room Status
				Rooms_Controller.updateRoomSatus(Reservations_Controller.getRoomNumFromIndex(reservation_Index), Room.room_Status.Occupied);

				System.out.println("=========================================");
				System.out.println("		Guest check-in complete!");
				System.out.println("=========================================");
			}else
			{
				System.out.println("Sorry, Reservation check in date is " + Reservations_Controller.getDate_In(reservation_Index));
			}
		}
		else
		{

			System.out.println("No Reservation Found!");
			Reservations_Controller.addWalkInReservation(df.format(dateobj) ,Guest_ID);
		}

	}

	public static void guestCheckOut()
	{

		Integer reservationIndex;
		String Room_Num = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dateobj = new Date();
		Integer paymentIndex = 0;

		System.out.println("\n=========================================");
		System.out.println("		   Checking Out Guest");
		System.out.println("=========================================");


		System.out.print("Room Number: ");
		Room_Num = sc.nextLine();
		reservationIndex = Reservations_Controller.searchReservationByRoomNDate(Room_Num,df.format(dateobj));


		if(reservationIndex != -1)
		{

			paymentIndex = Payment_Controller.addNewPayment(Room_Num,
													Rooms_Controller.getRoomType(Room_Num),
													Reservations_Controller.getLengthOfStay(reservationIndex),
													Reservations_Controller.getDate_In(reservationIndex),
													Reservations_Controller.getDate_Out(reservationIndex),
													guests.get(findGuestUsingID(Reservations_Controller.getGuestID(reservationIndex))).getPaymentDetail());

			Payment_Controller.pay(paymentIndex);
			Payment_Controller.generate_invoice(paymentIndex);

			Reservations_Controller.updateReservationStatus(reservationIndex,Reservation.reservation_Status.Check_Out);
			Rooms_Controller.updateRoomSatus(Room_Num, Room.room_Status.Vacant);
		}
		else
		{
			System.out.println("Room Number is not time for book out!");
		}

	}
	
	//Adding
	public static void addNewGuest(){
		String Name;
		String Address;
		String Gender;
		String Nationality;
		String Contact;
		String Id_Type_Str;
		Guest_Identity.identity_Type Id_Type;
		String Id_Num;
		String Pay_Type_Str;
		Payment_Detail.Payment_Type Pay_Type;
		String Card_Name = "";
		String Card_Num = "";
		String Card_BillAdd = "";
		String Card_Expiry = "";
		String[] CmpStrings;
		boolean cmpFlag;

		String datePatternRegex = "[0-9][0-9][/][0-9][0-9]";
		String creditCardREgex = "[0-9]{16}";

		
		//		guests.add(new Guest(Name, Address, Gender, Nationality, Contact, Id_Type, Id_Num,
		//					Pay_Type, Card_Name, Card_Num, Card_Expiry));
		System.out.println("\n=================================================");
		System.out.println("			Adding New Guest. \n" +
						   "			Please Fill Up The Form :");
		System.out.println("=================================================");

		////////
		//Name//
		////////
		System.out.print("Name: ");
		Name = sc.nextLine();

		///////////
		//Address//
		///////////
		System.out.print("Address: ");
		Address = sc.nextLine();

		///////////
		//Gender//
		//////////
        CmpStrings = new String[]{"M","F"};
		do
		{
			cmpFlag = false;
			System.out.print("Gender ('M' or 'F'): ");
			Gender = sc.nextLine();
			
			if(!Data_Function.stringContainsItems(Gender,CmpStrings))
            {
				System.out.println(Gender + " not in the list!\n");
                cmpFlag = true;
			}
		}while(cmpFlag);


		///////////////
		//Nationality//
		///////////////
		System.out.print("Nationality: ");
		Nationality = sc.nextLine();

		////////////
		//Contact//
		///////////
		System.out.print("Contact: ");
		Contact = sc.nextLine();

		/////////////////
		//Identity Type//
		/////////////////
		CmpStrings = new String[]{"1","2"};
		do
		{
			cmpFlag = false;
			System.out.print("\nIdentity Type \n1 Driving License\n2 Passport\nEnter Your Choice:");
			Id_Type_Str =  sc.nextLine();

			if(!Data_Function.stringContainsItems(Id_Type_Str,CmpStrings))
			{
				System.out.println(Id_Type_Str + " not in the list!");
				cmpFlag = true;
			}
		}while(cmpFlag);

		Id_Type = Guest_Identity.identity_Type.values()[Integer.parseUnsignedInt(Id_Type_Str)-1];


		////////////////
		//Payment Type//
		//Identity Number//
		///////////////////
		System.out.print(Id_Type.name().replace('_', ' ')+ " Number: ");
		///////////////////
		Id_Num = sc.nextLine();


		////////////////
		////////////////
		CmpStrings = new String[]{"1","2","3"};
		do
		{
			cmpFlag = false;
			System.out.print("\nPayment type:\n1 Credit Card\n2 Debit Card\n3 Cash\nEnter Your Choice:");
			Pay_Type_Str =  sc.nextLine();
			
			if(!Data_Function.stringContainsItems(Pay_Type_Str,CmpStrings))
			{
				System.out.println(Pay_Type_Str + " not in the list!");
				cmpFlag = true;
			}
		}while(cmpFlag);
		Pay_Type = Payment_Detail.Payment_Type.values()[Integer.parseUnsignedInt(Pay_Type_Str)-1];

		
		//Get Card identity if its not cash payment Type
		if(Pay_Type != Payment_Detail.Payment_Type.Cash)
		{
			//////////////
			//Card Name//
			/////////////
			System.out.print("Name on Card: ");
			Card_Name = sc.nextLine();

			//////////////
			//Card Name//
			/////////////
			do {
				cmpFlag = false;
				System.out.print("Card Number: ");
				Card_Num = sc.nextLine();

				if (!Data_Function.regularExpressionMatch(creditCardREgex,Card_Num))
				{
					System.out.println(Card_Num+" is not a valid card number!");
					cmpFlag = true;
				}
			}
			while(cmpFlag);


			///////////////
			//Card Expiry//
			///////////////

			do {
				cmpFlag = false;
				System.out.print("Card Expiry (MM/YYYY): ");
				Card_Expiry = sc.nextLine();
				if (!Data_Function.regularExpressionMatch(datePatternRegex,Card_Expiry))
				{
					System.out.println(Card_Expiry+"  does not match with MM/YY!");
					cmpFlag = true;
				}
			}
			while(cmpFlag);

			////////////////////////
			//Card Billing Address//
			////////////////////////
			System.out.print("Card Billing Address: ");
			Card_BillAdd = sc.nextLine();

		}
		
		
		//Adding a new guest into the Guests class
		guests.add(new Guest(Name, Address, Gender, Nationality, Contact, Id_Type, Id_Num,
								Pay_Type, Card_Name, Card_Num,Card_BillAdd, Card_Expiry));
		
		System.out.println("\nSuccessfully Added Guest "+Name+"!\n");
		

	}

	//Updating
	public static void updateGuest()
	{
		Integer index;

		System.out.println("\n=================================================");
		System.out.println("			Updating Guest Information");
		System.out.println("==================================================");
		System.out.print("Please Enter the Guest Name you want to update: ");
		String name = sc.nextLine();
		index = findGuest(name);
		editGuest(index);
	}

	//Searching Guest
	public static void searchGuest()
	{
		Integer index;

		System.out.println("\n=================================================");
		System.out.println("			Searching Guest Information		");
		System.out.println("====================================================");
		System.out.print("Please Enter the Guest Name you want to search: ");
		String name = sc.nextLine();
		index = findGuest(name);
		printGuest(index);
	}


	public static Integer SearchGuestByID(String ID)
	{
		for (Integer i = 0; i<guests.size();i++) {
			if(guests.get(i).getIdentityNum().equals(ID))
				return i;
		}

		return -1;
	}

	public static Integer findGuest(String name)
	{

		Integer i = 0;
		Integer exactMatch = -1;
		temp_guests_id = new ArrayList<Integer>();
		
		//For each guest in Guests
		for(i = 0; i < guests.size(); i++)
		{
			
			if(guests.get(i).getName().toLowerCase().contains(name.toLowerCase()))
			{
				temp_guests_id.add(i);
				if(guests.get(i).getName().toLowerCase().equals(name.toLowerCase()))
					exactMatch = i;
			}
		}
		
		//If no entries found
		if(temp_guests_id.isEmpty())
		{
			System.out.println("No Entry Found!");
			return -1;
		}
		//If only one detail found
		else if(temp_guests_id.size() == 1)
		{	
			return temp_guests_id.get(0);
		}
		//If Found Exact Match
		else if(exactMatch != -1)
		{
			return exactMatch;
		}
		//If found multiple entries
		else if(temp_guests_id.size()>1)
		{
			System.out.println("Guests Found From Search: ");
			//For each Integer in temp_guests_id
			for(i = 0; i < temp_guests_id.size(); i++)
			{
				System.out.println(i+1+"	"+guests.get(temp_guests_id.get(i)).getName());
			}
			
			System.out.print("Please Enter The Name You Want To Serch: ");
			return findGuest(sc.nextLine());
		}
		
		return -1;
		
	}

	private static Integer findGuestUsingID(String ID)
	{
		for(int i = 0; i <guests.size(); i++)
		{
			if (guests.get(i).getIdentityNum().equals(ID))
				return i;
		}

		return  -1;
	}

	private static void printGuest(Integer index)
	{
		if(index != -1) {
			System.out.println("\n===============================================");
			System.out.println("		Guest "+guests.get(index).getName() + " Found");
			System.out.println("===============================================");
			System.out.println("Name: " + guests.get(index).getName());
			System.out.println("Address: " + guests.get(index).getAddress());
			System.out.println("Gender: " + guests.get(index).getGender());
			System.out.println("Nationality: " + guests.get(index).getNationality());
			System.out.println("Contact: " + guests.get(index).getContact());
			System.out.println("Identity Type: " + guests.get(index).getIdentityType());
			System.out.println("Identity Number: " + guests.get(index).getIdentityNum());
			System.out.println("Payment Type: " + guests.get(index).getPaymentType());

			if (guests.get(index).getPaymentType() != Payment_Detail.Payment_Type.Cash) {
				System.out.println("Card Name: " + guests.get(index).getCardName());
				System.out.println("Card Number: " + guests.get(index).getCardNo());
				System.out.println("Card Billing Address: " + guests.get(index).getBillAddress());
				System.out.println("Card Expiry Date: " + guests.get(index).getCardExpiry());
			}
			System.out.println("");
		}
	}
	
	private static void editGuest(Integer index)
	{	
		String Name;
		String Address;
		String Gender;
		String Nationality;
		String Contact;
		String Id_Type_Str;
		String Id_Num;
		String Pay_Type_Str;
		String Card_Name = "";
		String Card_Num = "";
		String Card_Expiry = "";
		String Card_BillAdd = "";
        String[] CmpStrings;
        boolean cmpFlag;
		

		if(index != -1) {

			//Guest n = new Guest(name, address, gender, nationality, contact, Credit_Card, id_Type, id_Num);
			System.out.println("\n======================================");
			System.out.println("Editing Guest " + guests.get(index).getName());
			System.out.println("Enter '.' to retain the infomation");
			System.out.println("=======================================");

			////////
			//Name//
			////////
			System.out.print("Name (" + guests.get(index).getName() + "):");
			Name = sc.nextLine();
			if (!Name.equals("."))
				guests.get(index).setName(Name);

			///////////
			//Address//
			///////////
			System.out.print("Address (" + guests.get(index).getAddress() + "):");
			Address = sc.nextLine();
			if (!Address.equals("."))
				guests.get(index).setAddress(Address);

			//////////
			//Gender//
			//////////
			CmpStrings = new String[]{"M", "F", "."};
			do {
				cmpFlag = false;
				System.out.print("Gender ('M' or 'F') (" + guests.get(index).getGender() + "):");
				Gender = sc.nextLine();

				if (!Data_Function.stringContainsItems(Gender, CmpStrings)) {
					System.out.println(Gender + " not in the list!\n");
					cmpFlag = true;
				}
			} while (cmpFlag);

			if (!Gender.equals("."))
				guests.get(index).setGender(Gender);

			//Data Dumping for Java garbrage collection to destroy
			CmpStrings = null;

			///////////////
			//Nationality//
			///////////////
			System.out.print("Nationality:(" + guests.get(index).getNationality() + "):");
			Nationality = sc.nextLine();
			if (!Nationality.equals("."))
				guests.get(index).setNationality(Nationality);

			///////////
			//Contact//
			///////////
			System.out.print("Contact:(" + guests.get(index).getContact() + "):");
			Contact = sc.nextLine();
			if (!Contact.equals("."))
				guests.get(index).setContact(Contact);

			/////////////////
			//Identity Type//
			/////////////////
			CmpStrings = new String[]{"1", "2", "."};
			do {
				cmpFlag = false;
				System.out.print("\nIdentity Type(" + guests.get(index).getIdentityType() + "): \n1 Driving License\n2 Passport\nEnter Your Choice:");
				Id_Type_Str = sc.nextLine();

				if (!Data_Function.stringContainsItems(Id_Type_Str, CmpStrings)) {
					System.out.println(Id_Type_Str + " not in the list!");
					cmpFlag = true;
				}
			} while (cmpFlag);

			if (!Id_Type_Str.equals("."))
				guests.get(index).setIdentityType(Guest_Identity.identity_Type.values()[Integer.parseUnsignedInt(Id_Type_Str) - 1]);

			//Data Dumping for Java garbrage collection to destroy
			CmpStrings = null;

			///////////////////
			//Identity Number//
			///////////////////
			System.out.print(guests.get(index).getIdentityType().name().replace('_', ' ') + " Number:(" + guests.get(index).getIdentityNum() + "): ");
			;
			Id_Num = sc.nextLine();
			if (!Id_Num.equals("."))
				guests.get(index).setIdentityNum(Id_Num);


			/////////////////
			//Payment Type//
			////////////////
			CmpStrings = new String[]{"1", "2", "3", "."};
			do {
				cmpFlag = false;
				System.out.print("\nPayment type(" + guests.get(index).getPaymentType() + "):\n1 Credit Card\n2 Debit Card\n3 Cash\nEnter Your Choice:");
				Pay_Type_Str = sc.nextLine();

				if (!Data_Function.stringContainsItems(Pay_Type_Str, CmpStrings)) {
					System.out.println(Pay_Type_Str + " not in the list!");
					cmpFlag = true;
				}
			} while (cmpFlag);

			if (!Pay_Type_Str.equals("."))
				guests.get(index).setPaymentType(Payment_Detail.Payment_Type.values()[Integer.parseUnsignedInt(Pay_Type_Str) - 1]);

			//Data Dumping for Java garbrage collection to destroy
			CmpStrings = null;


			//Get Card identity if its not cash payment Type
			if (guests.get(index).getPaymentType() != Payment_Detail.Payment_Type.Cash) {
				/////////////
				//Card Name//
				/////////////
				System.out.print("Card Name(" + guests.get(index).getCardName() + "):");
				Card_Name = sc.nextLine();
				if (!Card_Name.equals("."))
					guests.get(index).setCardName(Card_Name);

				//////////////
				//Card Name//
				/////////////
				System.out.print("Card Number(" + guests.get(index).getCardNo() + "):");
				Card_Num = sc.nextLine();
				if (!Card_Num.equals("."))
					guests.get(index).setCardNo(Card_Num);

				///////////////
				//Card Expiry//
				///////////////
				System.out.print("Card Expiry (MM/YYYY)(" + guests.get(index).getCardExpiry() + "):");
				Card_Expiry = sc.nextLine();
				if (!Card_Expiry.equals("."))
					guests.get(index).setCardExpiry(Card_Expiry);

				////////////////////////
				//Card Billing Address//
				////////////////////////
				System.out.print("Card Billing Address ("+guests.get(index).getBillAddress() +"):");
				Card_BillAdd = sc.nextLine();
				if (!Card_Expiry.equals("."))
					guests.get(index).setBillAddress(Card_BillAdd);
			}


			System.out.println("\nSucessfully Edited Guest " + guests.get(index).getName() + "!\n");
		}
	}
	
	public static void saveData()
	{
		try
		{
			File_IO.writeSerializable(stored_File_Name,guests);

		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private static void loadData()
	{
		try
		{
			guests = (ArrayList<Guest>) File_IO.readDeserializable(stored_File_Name);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
