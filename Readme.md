# Shops Near You API
Gives you the facility to add a shop with address. Stores the longitude and latitude of your shop using Google [Geocoding API](https://developers.google.com/maps/documentation/geocoding/intro). You can request for a shop mentioning your current latitude and longitude. The API would return you the shop nearest to you.

To add a resource
```
Resource			: /shop
Example				:http://localhost:8080/shop/
Description			: Adds a shop. The API would find out the latitude and longitude of the 
					  shop address using Google Maps API and store it with the address.
Method				: POST
Request Content-type: application/json
Example Request	1	:	{
                          "shopName": "test",
                          "shopNumber": "1",
                          "postCode": "411 009"                          
                        }
Success Response	: 200 OK

Example Request	2	:	{
                          "shopName": "test",
                          "shopNumber": "1",
                          "postCode": "411 010"                          
                        }
Success Response	: 200 OK

==============================================================================
To get a resource

Resource				: /shop/latitude/longitude
Example					: http://localhost:8080/shop/41.295842/-73.67855399
Description				: Gives you the nearest shop from your latitude and longitude.
Method					: GET
Response Content-type	: application/json;charset=UTF-8
Example Response		: 200 OK	
							{
						        "shopName": "test",
						        "shopLatitude": 41.2958422,
						        "shopLongitude": -73.67855399999999,
						        "shopNumber": "1",
						        "postCode": "411 010"
						    },
====================================================================================
To get resources list
```
Resource				: /shop
Example 				: http://localhost:8080/shop
Description				: Gives you the list of available shops
Method					: GET
Response Content-type	: application/json;charset=UTF-8
Example Response		: 200 OK	
							[
							
    {
        "shopName": "test",
        "shopLatitude": 41.2958422,
        "shopLongitude": -73.67855399999999,
        "shopNumber": "1",
        "postCode": "411 010"
    },
    {
        "shopName": "test1",
        "shopLatitude": 40.1397226,
        "shopLongitude": -111.4052058,
        "shopNumber": "1",
        "postCode": "411 009"
    }
]  

======================================================================================

## How to run
You need Java 8 for running this project.


**Maven :**
If using maven you can use below command to run it

From Command Promt go to the path Where Pom.xml is placed and use mvn clean install command to build the project.
In project there is java class FindShopNearByYou.java right click and select Run As--> Java application.


**As jar :**
You can directly run it as a jar using below command. Change the path to the jar accordingly

`java -jar  shops-near-you-0.0.1-SNAPSHOT.jar`


## If you are behind a proxy server

If you are running it behind a proxy server, set the proxy setting in `application.properties`. e.g.
```
config.proxy=true
config.proxyaddress=<your proxy host address>
config.proxyport=<your proxy port>
config.proxyuser=<user name>
config.proxypassword=<proxy password>
```
 ther wise no need of it In my case its not required.

## Google map's Geocoding API 
The Google Maps [Geocoding API](https://developers.google.com/maps/documentation/geocoding/start) is a service that provides you the latitude and longitude of an address which is called  geocoding. It also supports reverse geocoding i.e. gives you the address from the latitude and longitude provided. To use this Geocoding API you need to first [register](https://developers.google.com/maps/documentation/geocoding/get-api-key) your application with Google using your Google ID. After registration Google gives you an API key which you need to update in the `application.properties` file as
```
config.apikey=<your key goes here>
```

## The distance comparison logic
Haversine formula is used for it.