package model;

import java.io.Serializable;

public class Guest_Identity implements Serializable {

	public enum identity_Type{
		Driving_License, Passport
	}
	
	//Identity
	private identity_Type id_Type;
	private String id_Num;
	
	//Constructor
	public Guest_Identity(identity_Type id_Type, String id_Num)
	{
		this.id_Type = id_Type;
		this.id_Num = id_Num;
	}
	
	public Guest_Identity()
	{
		
	}
	
	//Getter
	public identity_Type getID_Type()
	{
		return this.id_Type;
	}
	
	public String getID_Num()
	{
		return this.id_Num;
	}
	
	//Setter
	public void setID_Type(identity_Type id_Type)
	{
		this.id_Type = id_Type;
	}
	
	public void setID_Num(String id_Num)
	{
		this.id_Num = id_Num;
	}
	
	
}
