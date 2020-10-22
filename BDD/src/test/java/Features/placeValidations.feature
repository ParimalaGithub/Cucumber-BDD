Feature: Validating Place API's

@AddPlace
Scenario Outline: Verify if place is being successfully added using AddPlaceAPI
	Given Add place PayLoad with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "Post" http request
	Then the API call is success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using "getPlaceAPI"

	# When user calls "getPlaceAPI" with "get" http request
	
	#When user calls "deletePlaceAPI" with "Delete" http request
	
	Examples:
	|name  | language |address|
	|Pari  | English  | 1350, Ashton Park, CA91320|
	#|Vibu  | French   | 1340, Ashton Park, CA91320|
	#|Yazhin| Spanish  | 1360, Ashton Park, CA91320|
@DeletePlace	
Scenario: Verify if Delete place functionality is working
Given DeletePlace Payload
When user calls "deletePlaceAPI" with "Post" http request
Then the API call is success with status code 200
And "status" in response body is "OK"