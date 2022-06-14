package proj;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[]args)
	{
		JsonPath js=new JsonPath(Payload.CoursePrice());
		//number of course returned by API
		int count=js.getInt("courses.size()");
		System.out.println(count);
		// print purchase amount
        int purchaseamount=js.getInt("dashboard.purchoseAmount");
        System.out.println(purchaseamount);
        
        //print title of the first course
        String firscourse=js.getString("courses.title[0]");
        System.out.println(firscourse);
        
        // print all courses titles and their respective prices
        
        for(int i=0; i<count; i++)
        {
        	String coursetitles=js.get("courses.title["+i+"]");
        	System.out.println(coursetitles);
        	int prices=js.get("courses.price["+i+"]");
        	System.out.println(prices);
        }
        int copies=js.getInt("courses.copies[2]");
        System.out.println(copies);
	}
	
}
