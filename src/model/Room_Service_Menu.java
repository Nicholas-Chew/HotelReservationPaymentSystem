package model;

public class Room_Service_Menu {

	//Attribute
	private String name;
	private Double price;
	private String description;
	
	//Constructor
	public Room_Service_Menu(String name, double price, String description)
	{
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	public Room_Service_Menu()
	{
	}
	
	//Getter
	public String getName()
	{
		return this.name;
	}
	
	public Double getPrice()
	{
		return this.price;
	}
	
	public String getDescription()
	{
		return this.description;
	}

	//Setter
	public void setName(String name)
	{
		this.name = name;
	}

	public void setPrice(Double price)
	{
		this.price = price;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}
	

}
