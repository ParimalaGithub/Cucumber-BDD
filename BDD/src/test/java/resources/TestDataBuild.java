package resources;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import pojoClass.GMGetPlace;
import pojoClass.Location;

public class TestDataBuild {
	
	public  GMGetPlace AddPlacePayLoad(String name,String language,String address) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		GMGetPlace P = new GMGetPlace();
		Location L = new pojoClass.Location();
		L.setLat(-38.383494);
		L.setLng(33.427362);
		P.setLocation(L);
		P.setAccuracy(50);P.setName(name);P.setPhone_number("8748648769");P.setAddress(address);
		List<String>myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		P.setTypes(myList);
		P.setWebsite("http://google.com");
		P.setLanguage(language);
		return P;
	}
	public String deletePlacePayLoad(String place_id)
	{

		return "{\r\n   \"place_id\": \""+place_id+"\"\r\n}\r\n";
	}
	
}
