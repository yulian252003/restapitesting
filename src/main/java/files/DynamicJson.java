package files;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class DynamicJson {
@Test(dataProvider="BooksData")
public void addBook(String isbn, String aisle)
{
	RestAssured.baseURI="http://216.10.245.166/";
	String response=given().log().all().header("Content-Type","application/json").
	body(Payload.Addbook(isbn,aisle)).
	when()
	.post("/Library/Addbook.php")
	.then().assertThat().statusCode(200)
	.extract().response().asString();
	JsonPath js=reUsableMethods.rawToJson(response);
	String id=js.get("ID");
	System.out.println(id);
}

//delte

@DataProvider(name="BooksData")

//array=collection of elements
//multidimentional array= collection of arrays.
public Object[][] getData()
{
return new Object[][]
  {
	{"ojo","1111"},
	{"nariz","2222"},
	{"boca","3333"}
  };
}
}
