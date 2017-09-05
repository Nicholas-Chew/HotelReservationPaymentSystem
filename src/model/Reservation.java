package model;


import java.io.Serializable;

public class Reservation implements Serializable{

	public enum reservation_Status{
		Confirm, In_Waitlist,Check_In, Expired ,Check_Out
	}
	
	//Attribute
	private String guest_ID;
	private String room_No;
	private String payment_Card_No;
	private String date_Check_In;
	private String date_Check_Out;
	private String reservation_Date;
	private Integer num_Of_Adult;
	private Integer num_Of_Children;
	private reservation_Status status;
	

	
	//Constructor
	public Reservation(String Guest_ID, String Room_No, String date_Check_In, String date_Check_Out,String reservation_Date,
					   Integer num_Of_Adult, Integer num_Of_Children, reservation_Status status)
	{
		this.guest_ID = Guest_ID;
		this.room_No = Room_No;
		this.date_Check_In = date_Check_In;
		this.date_Check_Out = date_Check_Out;
		this.reservation_Date = reservation_Date;
		this.num_Of_Adult = num_Of_Adult;
		this.num_Of_Children = num_Of_Children;
		this.status = status;
	}
	
	public Reservation()
	{
		
	}
	
	//Getter
	public String getGuestID()
	{
		return this.guest_ID;
	}

	public String getRoomNo()
	{
		return this.room_No;
	}

	public String getPaymentCardNo()
	{
		return this.payment_Card_No;
	}

	public String getDateCheckIn()
	{
		return this.date_Check_In;
	}

	public String getDateCheckOut()
	{
		return this.date_Check_Out;
	}

	public String getReservationDate()
	{
		return this.reservation_Date;
	}

	public Integer getNumOfAdult()
	{
		return this.num_Of_Adult;
	}

	public Integer getNumOfChildren()
	{
		return this.num_Of_Children;
	}

	public reservation_Status getStatus()
	{
		return this.status;
	}

	//Setter
	public void getGuestID(String guest_Id)
	{
		this.guest_ID = guest_Id;
	}

	public void setRoomNo(String room_No)
	{
		this.room_No = room_No;
	}

	public void setPaymentCardNo(String payment_Card_No)
	{
		this.payment_Card_No = payment_Card_No;
	}

	public void setDateCheckIn(String date_Check_In)
	{
		this.date_Check_In = date_Check_In;
	}

	public void setDateCheckOut(String date_Check_Out)
	{
		this.date_Check_Out = date_Check_Out;
	}

	public void setReservationDate(String reservation_Date)
	{
		this.reservation_Date = reservation_Date;
	}

	public void setNumOfAdult(Integer num_Of_Adult)
	{
		this.num_Of_Adult = num_Of_Adult;
	}

	public void setNumOfChildren(Integer num_Of_Children)
	{
		this.num_Of_Children = num_Of_Children;
	}

	public void setStatus(reservation_Status status)
	{
		this.status = status;
	}
}
