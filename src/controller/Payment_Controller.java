package controller;

import facade.Data_Function;
import facade.File_IO;
import model.Payment_Detail;
import model.Room;
import model.Payment;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

/**
 * Created by chewzhijie on 4/4/16.
 */
public class Payment_Controller {

    private final static String stored_File_Name = "payments";
    private static ArrayList<Payment> payments;
    private static Scanner sc;


    public static void tryLoad()
    {
        try
        {
            sc = new Scanner(System.in);
            loadData();
        }
        finally
        {
            if(payments == null)
                payments= new ArrayList<Payment>();
        }
    }

    public static Integer addNewPayment(String Room_Num, Room.room_Type room_Type, int num_Day_Stay , String date_In, String date_Out, Payment_Detail payment_detail)
    {
        double total_Cost, droomCost = 0;
        double roomCost = 0;
        double total_Amount;
        double room_Service_Cost = 0;

        Integer room_Service_Cost_Index;
        Integer numWeekend = Data_Function.weekendsInDate(date_In, date_Out,"dd/MM/yyyy");
        Integer invoice_Num = payments.size() +1 ;
        room_Service_Cost_Index = Room_Services_Controller.findIndexByRmNum(Room_Num);

        if(room_Service_Cost_Index != -1)
            room_Service_Cost = Room_Services_Controller.calculateServiceCost(room_Service_Cost_Index);


        switch (room_Type)
        {
            case Single: roomCost = 100.0;
                droomCost = 80;
                break;
            case Double: roomCost = 180.0;
                droomCost = 145.0;
                break;
            case Deluxe: roomCost = 280.0;
                droomCost = 220.0;
                break;
            case VIP_Suite: roomCost = 500.0;
                droomCost = 400.0;
        }

        total_Cost = droomCost * (num_Day_Stay - numWeekend) + roomCost*numWeekend +room_Service_Cost;
        total_Amount = total_Cost * (1 + Payment.tax) * (1 + Payment.service_charge);

        payments.add(new Payment(Room_Num,invoice_Num, room_Type, num_Day_Stay, date_In, date_Out, room_Service_Cost, total_Cost, total_Amount,total_Amount,payment_detail));

        return payments.size() - 1;
    }

    public static void pay(Integer index)
    {
        double change = 0;

        if(payments.get(index).getPay_Detail().getPayType() == Payment_Detail.Payment_Type.Cash) {
            System.out.println("Paying By Cash");
            System.out.println("Please input the Amount Given : $");
            Double paid_Amount = sc.nextDouble();

            if (paid_Amount >= payments.get(index).getTotal_Amount()) {
                change = paid_Amount - payments.get(index).getTotal_Amount();
                payments.get(index).setBalance(0);
                System.out.format("Please return guests change Amount $%.2f",change);
            } else {
                payments.get(index).setBalance(payments.get(index).getTotal_Amount() - paid_Amount);
                System.out.format("Payment not full. Balance of  $%.2f", payments.get(index).getBalance());
            }
        }
        else {
            System.out.println("Paying By "+payments.get(index).getPay_Detail().getPayType());
            System.out.println("Paying Card Name "+payments.get(index).getPay_Detail().getCardName());
            System.out.println("Paying Card Number "+payments.get(index).getPay_Detail().getCardNo());
            System.out.println("Paying Card Expiry "+payments.get(index).getPay_Detail().getCardExpiry());
            payments.get(index).setBalance(0);
        }
    }

    public static void generate_invoice(Integer index)
    {
        double roomCost = 0, droomCost = 0;
        int room_Service_Cost_Index;

        System.out.println("===================================================");
        System.out.println("                 Payment Receipt");
        System.out.println("===================================================");

        if(payments.get(index).getBalance() != 0)
        {
            //If Payment have not been made, ask to pay
            System.out.println("Please make your payment");
            String balance_Amount = String.format("%.2f", payments.get(index).getBalance());
            System.out.println("Total Balance Amount : $"+ balance_Amount);

        }
        else
        {
            //If Payment have been made, print full invoice
            System.out.println("Check-In-Date: "+ payments.get(index).getDate_In());
            System.out.println("Check-Out-Date: "+ payments.get(index).getDate_Out());
            System.out.println("Number of Stay: "+ payments.get(index).getNum_Day_Stay());
            System.out.println("Room No: "+ payments.get(index).getRoom_Num());
            System.out.println("Room Type: "+ payments.get(index).getRoom_Type());

            switch (payments.get(index).getRoom_Type())
            {
                case Single: roomCost = 100.0;
                            droomCost = 80;
                    break;
                case Double: roomCost = 180.0;
                            droomCost = 145.0;
                    break;
                case Deluxe: roomCost = 280.0;
                            droomCost = 220.0;
                    break;
                case VIP_Suite: roomCost = 500.0;
                                droomCost = 400.0;

                    break;
            }

            System.out.format("Room Cost Weekend(per day): %.2f\n", roomCost);
            System.out.format("Room Cost Weekday(per day): %.2f\n", droomCost);

            room_Service_Cost_Index = Room_Services_Controller.findIndexByRmNum(payments.get(index).getRoom_Num());

            if(room_Service_Cost_Index != -1)
            {
                System.out.println("Room Service Cost");
                System.out.println("=================");
               Room_Services_Controller.listRoomServiceOrder(room_Service_Cost_Index);
                System.out.format("Service Cost: %.2f\n", Room_Services_Controller.calculateServiceCost(room_Service_Cost_Index));
            }

            System.out.format("Total Cost : %.2f\n", payments.get(index).getTotal_Cost());
            System.out.format("Total Amount : %.2f\n", payments.get(index).getTotal_Amount());


        }

        System.out.println("===================================================");
    }

    public static void saveData()
    {
        try
        {
            File_IO.writeSerializable(stored_File_Name,payments);

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
            payments = (ArrayList<Payment>) File_IO.readDeserializable(stored_File_Name);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
