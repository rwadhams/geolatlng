package com.wadhams.geolatlng.util

import com.wadhams.geolatlng.geo.GeoPosition

class GeoLatLng {
	static double distance(GeoPosition gp1, GeoPosition gp2) {
		double lat1Radians = Math.toRadians(gp1.getLatDecimalDegrees().doubleValue())
		double lng1Radians = Math.toRadians(gp1.getLngDecimalDegrees().doubleValue())
		
		double lat2Radians = Math.toRadians(gp2.getLatDecimalDegrees().doubleValue())
		double lng2Radians = Math.toRadians(gp2.getLngDecimalDegrees().doubleValue())

		// calculate great-circle distance
		double distance = Math.acos(Math.sin(lat1Radians) * Math.sin(lat2Radians) + Math.cos(lat1Radians) * Math.cos(lat2Radians) * Math.cos(lng1Radians - lng2Radians))
		
		// distance in human-readable format:
		// earth's radius in km = ~6371
		return 6371 * distance
	}
}
