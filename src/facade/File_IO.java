package facade;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.*;

public class File_IO {
	
	public static void writeToXML(String file_Name,Object obj) throws Exception
	{
		try
		{
			//Make Folder Data if not exists
			new File(System.getProperty("user.dir") +"/Data").mkdirs();

			//Make File if not exists
			File tempFile = new File(System.getProperty("user.dir") +"/Data/"+file_Name+".xml");
			if(!tempFile.exists()) {
				tempFile.createNewFile();
			} 
			
			//XML Encoder
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
		                   			new FileOutputStream(System.getProperty("user.dir") +"/Data/"+file_Name+".xml")));
								
			encoder.writeObject(obj);
			encoder.close(); 
		}
		catch(Exception e)
		{
			throw new Exception(e+" File : "+file_Name);
		}
	}
	
	
	public static Object readFromXML(String file_Name) throws Exception
	{
		try
		{
			//XML Decoder
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
	                new FileInputStream(System.getProperty("user.dir") +"/Data/"+file_Name+".xml")));
	
	        Object obj = decoder.readObject();
	        decoder.close();
	        return obj;
		}
		catch (Exception e)
		{
			throw new Exception (e+" File : "+file_Name);
		}
	}

	public static void writeSerializable (String file_Name,Object obj) throws Exception
	{
		try
		{
			//Make Folder Data if not exists
			new File(System.getProperty("user.dir") +"/Data").mkdirs();

			//Make File if not exists
			File tempFile = new File(System.getProperty("user.dir") +"/Data/"+file_Name+".ser");
			if(!tempFile.exists()) {
				tempFile.createNewFile();
			}
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir") +"/Data/"+file_Name+".ser"));
				out.writeObject(obj);
				out.close();

		}
		catch (Exception e)
		{
			throw new Exception(e+" File : "+file_Name);
		}
	}

	public static Object readDeserializable(String file_Name) throws Exception
	{
		try
		{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") +"/Data/"+file_Name+".ser"));
			Object obj = in.readObject();
			in.close();
			return obj;
		}
		catch (Exception e)
		{
			throw  new Exception(e+" File : "+file_Name);
		}
	}
}
