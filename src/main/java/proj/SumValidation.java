package proj;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation
{
@Test
public void sumOfCourses()
{
	JsonPath js=new JsonPath(Payload.CoursePrice());
	int count=js.getInt("courses.size()");
	int totalabsoluto=0;
	for(int i=0; i<count; i++)
	{
		int price=js.get("courses.price["+i+"]");
		int copies=js.get("courses.copies["+i+"]");
		int totalindividual=price*copies;
		totalabsoluto=totalabsoluto + totalindividual;
	}
	System.out.println(totalabsoluto);
	int purchaseamount=js.getInt("dashboard.purchoseAmount");
	Assert.assertEquals(totalabsoluto, purchaseamount);
}
}
