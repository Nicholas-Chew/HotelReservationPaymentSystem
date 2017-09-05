package model;

import java.util.ArrayList;

public class Room {
	
	public enum room_Type{
		Single, Double, Deluxe, VIP_Suite
	}

	public enum room_Status{
		Vacant, Occupied, Reserved, Under_Maintain
	}
	
	//Attribute
	private String room_No;
	private room_Type rm_Type;
	private room_Status rm_Stat;
	private ArrayList<String> room_Detail =  new ArrayList<String>();
	
	//Constructor
	public Room(String room_No, room_Type rm_Type, room_Status rm_Stat)
	{
		this.room_No = room_No;
		this.rm_Type = rm_Type;
		this.rm_Stat = rm_Stat;
	}

    public Room(String room_No, room_Type rm_Type, room_Status rm_Stat, ArrayList<String> room_Detail)
    {
        this.room_No = room_No;
        this.rm_Type = rm_Type;
        this.rm_Stat = rm_Stat;
        this.room_Detail = room_Detail;
    }
	
	public Room()
	{
	}
	

	public void add_Room_Detail(String detail)
	{
		room_Detail.add(detail);
	}

	public void delete_Room_Detail(int index)
	{
		try
		{
			room_Detail.remove(index-1);
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	
	//Getter
	public String getRoomNo()
	{
		return this.room_No;
	}
	
	public room_Type getRoomType()
	{
		return this.rm_Type;
	}

	public room_Status getRoomStatus()
	{
		return this.rm_Stat;
	}

	public ArrayList<String> getRoomDetail()
	{
		return room_Detail;
	}
	
	//Setter

    public void setRoomNo(String Room_No)
    {
        this.room_No = Room_No;
    }
	
	public void setRoomStatus(room_Status rm_Stat)
	{
		this.rm_Stat = rm_Stat;
	}
	
	public void setRoomType(room_Type rm_Type)
	{
		this.rm_Type = rm_Type;
	}

	public void setRoomDetail(ArrayList<String> room_Detail)
	{
		this.room_Detail = room_Detail;
	}

	
}
