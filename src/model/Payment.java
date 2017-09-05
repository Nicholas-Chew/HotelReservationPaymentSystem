package model;

import java.io.Serializable;
import java.text.DecimalFormat;


public class Payment implements Serializable {
	public static final double tax = 0.07;
	public static final double service_charge = 0.1;
	public static final double discount = 20;

	//Attributes
	private String Room_Num;
	private Integer invoice_Num;
	private Room.room_Type room_Type;
	private String date_In;
	private String date_Out;
	private Integer num_Day_Stay;
	private double room_Service_Cost;
	private double total_Cost;
	private double total_Amount;
	private double balance;
	
	//Payment
	private Payment_Detail pay_Detail;

	
	//Attribute Function
	private static DecimalFormat df2 = new DecimalFormat(".##");
	
	//Constructor
	public Payment(String Room_Num,int invoice_Num, Room.room_Type room_Type, int num_Day_Stay, String date_In, String date_Out, double room_Service_Cost, double total_Cost, double total_Amount, double balance, Payment_Detail pay_Detail)
	{
		this.Room_Num = Room_Num;
		this.invoice_Num = invoice_Num;
		this.room_Type = room_Type;
		this.num_Day_Stay = num_Day_Stay;
		this.date_In = date_In;
		this.date_Out = date_Out;
		this.room_Service_Cost = room_Service_Cost;
		this.total_Cost = total_Cost;
		this.total_Amount = total_Amount;
		this.balance = balance;
		this.pay_Detail = pay_Detail;
	}
	
	public Payment()
	{
		
	}

	//Getter
	public String getRoom_Num()
	{
		return this.Room_Num;
	}

	public Integer getInvoice_Num()
	{
		return this.invoice_Num;
	}

	public Room.room_Type getRoom_Type()
	{
		return this.room_Type;
	}

	public Integer getNum_Day_Stay()
	{
		return this.num_Day_Stay;
	}

	public String getDate_In()
	{
		return this.date_In;
	}

	public String getDate_Out()
	{
		return this.date_Out;
	}

	public double getRoom_Service_Cost()
	{
		return this.room_Service_Cost;
	}

	public double getTotal_Cost()
	{
		return this.total_Cost;
	}

	public double getTotal_Amount()
	{
		return this.total_Amount;
	}

	public double getBalance()
	{
		return this.balance;
	}

	public Payment_Detail getPay_Detail()
	{
		return this.pay_Detail;
	}

	//Setter
	public void	setRoom_Num(String room_Num)
	{
		this.Room_Num = room_Num;
	}

	public void setInvoice_Num(Integer invoice_Num)
	{
		this.invoice_Num = invoice_Num;
	}

	public void setRoom_Type(Room.room_Type room_Type)
	{
		this.room_Type = room_Type;
	}

	public void setNum_Day_Stay(Integer num_Day_Stay)
	{
		this.num_Day_Stay = num_Day_Stay;
	}

	public void setDate_In(String date_In)
	{
		this.date_In = date_In;
	}

	public void setDate_Out(String date_Out)
	{
		this.date_Out = date_Out;
	}

	public void setRoom_Service_Cost(double room_Service_Cost)
	{
		this.room_Service_Cost = room_Service_Cost;
	}

	public void setTotal_Cost(double total_Cost)
	{
		this.total_Cost = total_Cost;
	}

	public void setTotal_Amount(double total_Amount)
	{
		this.total_Amount = total_Amount;
	}

	public void setBalance(double balance)
	{
		this.balance = balance;
	}

	public void setPay_Detail(Payment_Detail pay_Detail)
	{
		this.pay_Detail = pay_Detail;
	}

}
