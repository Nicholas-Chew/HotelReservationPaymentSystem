package model;

import java.io.Serializable;

public class Guest implements Serializable {
	
	//Attribute
	private String name;
	private String address;
	private String gender;
	private String nationality;
	private String contact;
	//Identity
	private Guest_Identity id = new Guest_Identity();
	//Payment
	private Payment_Detail pay_Detail  = new Payment_Detail();
	
	//Constructor
	public Guest (String name, String address, String gender, String nationality, String contact, 
				Guest_Identity.identity_Type id_Type, String id_Num, Payment_Detail.Payment_Type pay_Detail,
					String card_Name, String card_No, String billAdd, String card_Expiry)
	{
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.nationality = nationality;
		this.contact = contact;
		this.id.setID_Type(id_Type);
		this.id.setID_Num(id_Num);
		this.pay_Detail.setPayType(pay_Detail);
		this.pay_Detail.setCardName(card_Name);
		this.pay_Detail.setCardNo(card_No);
		this.pay_Detail.setBillAddress(billAdd);
		this.pay_Detail.setCardExipry(card_Expiry);
		
	}
	
	public Guest()
	{
		
	}
	
	//Getters
	public String getName()
	{
		return this.name;
	}
	
	public String getAddress()
	{
		return this.address;
	}
	
	public String getGender()
	{
		return this.gender;
	}
	
	public String getNationality()
	{
		return this.nationality;
	}
	
	public String getContact()
	{
		return this.contact;
	}
	
	public Guest_Identity.identity_Type getIdentityType()
	{
		return this.id.getID_Type();
	}
	
	public String getIdentityNum()
	{
		return this.id.getID_Num();
	}
	
	public Payment_Detail.Payment_Type getPaymentType()
	{
		return this.pay_Detail.getPayType();
	}
	
	public String getCardName()
	{
		return this.pay_Detail.getCardName();
	}
	
	public String getCardNo()
	{
		return this.pay_Detail.getCardNo();
	}

	public String getBillAddress()
	{
		return this.pay_Detail.getBillAddress();
	}
	
	public String getCardExpiry()
	{
		return this.pay_Detail.getCardExpiry();
	}

	public Payment_Detail getPaymentDetail()
	{
		return this.pay_Detail;
	}

	//Setters
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public void setGender(String gender)
	{
		this.gender = gender;
	}
	
	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}
	
	public void setContact(String contact)
	{
		this.contact = contact;
	}
	
	public void setIdentityType(Guest_Identity.identity_Type  type)
	{
		this.id.setID_Type(type);
	}
	
	public void setIdentityNum(String Num)
	{
		this.id.setID_Num(Num);
	}
	
	public void setPaymentType(Payment_Detail.Payment_Type pay_Type)
	{
		this.pay_Detail.setPayType(pay_Type);
	}
	
	public void setCardName(String card_Name)
	{
		this.pay_Detail.setCardName(card_Name);
	}
	
	public void setCardNo(String card_No)
	{
		this.pay_Detail.setCardNo(card_No);
	}

	public void setBillAddress(String billAddress)
	{
		this.pay_Detail.setBillAddress(billAddress);
	}
	
	public void setCardExpiry(String card_Expiry)
	{
		this.pay_Detail.setCardExipry(card_Expiry);
	}
}
