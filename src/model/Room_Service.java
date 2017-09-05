package model;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.Serializable;
import java.util.ArrayList;

public class Room_Service implements Serializable{

	public enum room_Service_Status
	{
		confirmed, preparing, delivered
	}
	
	//Attribute
	private String date;
	private String time;
	private String room_Num;
	private String remarks;
	private room_Service_Status status;
	private ArrayList<order_Menu> order= new ArrayList<>();
	
	//Constructor
	public Room_Service( String date, String time, String room_Num, String remarks,ArrayList<order_Menu> order)
	{
		this.date = date;
		this.time = time;
		this.room_Num = room_Num;
		this.remarks = remarks;
		this.status = room_Service_Status.confirmed;
		this.order = order;
	}
	
	public Room_Service()
	{
		
	}


	//Getter
	public String getDate()
	{
		return this.date;
	}

	public String getTime()
	{
		return this.time;
	}

	public String getRoomNum()
	{
		return this.room_Num;
	}

	public String getRemarks()
	{
		return this.remarks;
	}

	public room_Service_Status getStatus()
	{
		return this.status;
	}

	public ArrayList<order_Menu> getOrder()
	{
		return this.order;
	}


	//Setter
	public void setDate(String date)
	{
		this.date = date;
	}

	public void setTime(String time)
	{
		this.time = time;
	}

	public void setRoomNum(String Room_Num)
	{
		this.room_Num = Room_Num;
	}

	public void setRemarks(String remarks)
	{
		this.remarks = remarks;
	}

	public void setStatus(room_Service_Status status)
	{
		this.status = status;
	}

	public void setOrder(ArrayList<order_Menu> order)
	{
		this.order = order;
	}
}


