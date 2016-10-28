package com.shops.geoCode;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.shops.model.Shop;

/**
 * Service to locate the latitude and longitude of a shop. Uses Google's
 * Geocoding service.
 * 
 * @author Jitendra
 *
 */

public class GeocodeService {

	/**
	 * logger
	 */
	private static final Logger LOG = Logger.getLogger(GeocodeService.class
			.getName());

	/**
	 * the context
	 */
	private GeoApiContext context;

	/**
	 * proxy settings
	 */
	// private boolean proxy;

	/**
	 * the api key
	 */
	private String apikey = "AIzaSyDdCtHufuX10ugun8wfs8kZUnmk6EoBPJA";

	/**
	 * initialize the Geo API Context with API key and request handler etc
	 */
	private void initializeGeoApiContext() {

		context = new GeoApiContext();

		context.setApiKey(apikey);
	}

	/**
	 * return the geocode of the shop
	 * 
	 * @param shop
	 * @return
	 */
	public LatLng getGeocode(Shop shop) {
		initializeGeoApiContext();
		GeocodingResult[] results = null;
		LatLng geocode = null;
		try {
			results = GeocodingApi.geocode(context, getFormattedAddress(shop))
					.await();
			geocode = results[0].geometry.location;
		} catch (Exception e) {
			e.printStackTrace();
			LOG.log(Level.SEVERE,
					"Exception in invoking Google geocoding API :",
					e.getCause());
		}
		return geocode;
	}

	/**
	 * comma separated string formated address
	 * 
	 * @param shop
	 * @return
	 */
	private String getFormattedAddress(Shop shop) {
		StringBuilder formattedAddress = new StringBuilder();
		if (Objects.nonNull(shop.getShopName())) {
			formattedAddress.append(shop.getShopName()).append(",");
		}
		if (Objects.nonNull(shop.getShopNumber())) {
			formattedAddress.append(shop.getShopNumber()).append(",");
		}

		if (Objects.nonNull(shop.getPostCode())) {
			formattedAddress.append(shop.getPostCode());
		}
		LOG.log(Level.INFO, "Evaluating geocode for the address :",
				formattedAddress.toString());
		return formattedAddress.toString();
	}

	public void setContext(GeoApiContext context) {
		this.context = context;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

}
