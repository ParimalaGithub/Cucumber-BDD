package stepDefinitions;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void preconditions() throws Exception
	{
		//we need to create place id only when place id is null
		StepDefinition SD = new StepDefinition();
		if(StepDefinition.place_id==null)
		{
		SD.add_place_PayLoad_with("PAri", "EN","1340,ashton park lane");
		SD.user_calls_with_http_request("AddPlaceAPI","Post");
		SD.verify_place_Id_created_maps_to_using_AddPlaceAPI("PAri", "getPlaceAPI");
		}
	}
}
