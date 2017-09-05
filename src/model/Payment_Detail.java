package model;

import java.io.Serializable;

public class Payment_Detail implements Serializable{

	public enum Payment_Type{
		Credit_card, Debit_card, Cash;
	}

	//Payment
	private Payment_Type pay_Type;
	private String card_Name;
	private String card_No;
	private String bill_address;
	private String card_expiry_Date;
	
	//Constructor
	public Payment_Detail(Payment_Type pay_Type, String card_Name, String card_No, String bill_address, String card_expiry_Date)
	{
		this.pay_Type = pay_Type;
		this.card_Name = card_Name;
		this.card_No = card_No;
		this.bill_address = bill_address;
		this.card_expiry_Date = card_expiry_Date;
	}
	
	public Payment_Detail()
	{
		
	}
	
	//Getter
	public Payment_Type getPayType()
	{
		return this.pay_Type;
	}
	
	public String getCardName()
	{
		return this.card_Name;
	}
	
	public String getCardNo()
	{
		return this.card_No;
	}

	public String getBillAddress()
	{
		return this.bill_address;
	}
	
	public String getCardExpiry()
	{
		return this.card_expiry_Date;
	}
	
	
	//Setter
	public void setPayType(Payment_Type pay_Type)
	{
		this.pay_Type = pay_Type;
	}
	
	public void setCardName(String card_Name)
	{
		this.card_Name = card_Name;
	}
	
	public void setCardNo(String card_No)
	{
		this.card_No = card_No;
	}

	public void setBillAddress(String bill_address)
	{
		this.bill_address = bill_address;
	}
	
	public void setCardExipry(String card_expiry_Date)
	{
		this.card_expiry_Date = card_expiry_Date;
	}
	
}
