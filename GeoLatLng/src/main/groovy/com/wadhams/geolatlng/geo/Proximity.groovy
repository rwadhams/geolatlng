package com.wadhams.geolatlng.geo

import groovy.transform.ToString

@ToString(includeNames=true)
class Proximity {
	double distance
	GeoPosition gp
	
	double maxLat
	double minLat
	
	double maxLng
	double minLng
	
	public Proximity(GeoPosition gp, double distance) {
		this.gp = gp
		this.distance = distance
		
		maxLat = this.gp.latDecimalDegrees + Math.toDegrees(distance / 6371d);
//		println "maxLat: $maxLat"
		minLat = this.gp.latDecimalDegrees - Math.toDegrees(distance / 6371d);
//		println "minLat: $minLat"
		
		maxLng = this.gp.lngDecimalDegrees + Math.toDegrees(distance / 6371d / Math.cos(this.gp.latRadians));
//		println "maxLng: $maxLng"
		minLng = this.gp.lngDecimalDegrees - Math.toDegrees(distance / 6371d / Math.cos(this.gp.latRadians));
//		println "minLng: $minLng"
//		println ''
	}

	boolean nearBy(GeoPosition gp) {
		return gp.latDecimalDegrees < maxLat && gp.latDecimalDegrees > minLat && gp.lngDecimalDegrees < maxLng && gp.lngDecimalDegrees > minLng
	}	
}
