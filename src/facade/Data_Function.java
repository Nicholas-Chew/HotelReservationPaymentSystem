package facade;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chewzhijie on 25/3/16.
 */
public class Data_Function {

    public static boolean stringContainsItems(String inputString, String[] items)
    {
        for(int i =0; i < items.length; i++)
        {
            if(inputString.equals(items[i]))
            {
                return true;
            }
        }
        return false;
    }

    public static void printAllFromArray(ArrayList<String> strings)
    {
        int arrayList = strings.size() - 1;
        int i = 0;

        //ForEach String in strings
        for (String s: strings) {
            System.out.print(" "+s);
            if(i++<arrayList)
                System.out.print(",");

        }
    }

    public static boolean regularExpressionMatch(String regex, String matchStr)
    {
        Pattern Pattern = java.util.regex.Pattern.compile(regex);

        Matcher m = Pattern.matcher(matchStr);
        if (m.find( )) {
           return true;
        } else {
            return false;
        }
    }

    public static int daysDifference(String start_Date, String stop_Date, String Expression )
    {

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat(Expression);

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(start_Date);
            d2 = format.parse(stop_Date);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffDays = diff / (24 * 60 * 60 * 1000);

            return (int) diffDays;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int hoursDifference(String start_Date, String stop_Date, String Expression )
    {

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat(Expression);

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(start_Date);
            d2 = format.parse(stop_Date);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffDays = diff / (60 * 60 * 1000);

            return (int) diffDays;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static int minDifference(String start_Date, String stop_Date, String Expression )
    {

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat(Expression);

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(start_Date);
            d2 = format.parse(stop_Date);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffDays = diff / (60 * 1000);

            return (int) diffDays;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public static boolean betweenDate(String cmpDate,String start_Date, String stop_Date, String Expression )
    {

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat(Expression);

        Date d1 = null;
        Date d2 = null;
        Date d3 = null;

        try {
            d1 = format.parse(start_Date);
            d2 = format.parse(stop_Date);
            d3 = format.parse(cmpDate);

            return d3.after(d1) && d3.before(d2);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static int weekendsInDate(String start_Date, String stop_Date, String Expression )
    {

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat(Expression);
        // 25/04/2016 . "dd MM yyyy hh:mm"
        int i = 0;

        Date d1 = null;
        Date d2 = null;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();



        try {
            d1 = format.parse(start_Date);
            d2 = format.parse(stop_Date);
            c1.setTime(d1);
            c2.setTime(d2);
            c2.add(Calendar.DAY_OF_YEAR, 1);

           do {
               int day = c1.get(Calendar.DAY_OF_WEEK);
                if( day == Calendar.SATURDAY || day == Calendar.SUNDAY)
                {
                    i++;
                }
               c1.add(Calendar.DAY_OF_YEAR, 1);
           }while (!c1.equals(c2));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return i;
    }
}
