package com.wadhams.geolatlng.geo

import java.math.MathContext
import java.math.RoundingMode

import com.wadhams.geolatlng.type.Direction

import groovy.transform.ToString

@ToString(includeNames=true)
class GeoPosition {
	int scale = 8
	BigDecimal minutesPerDegree = new BigDecimal(60)
	BigDecimal secondsPerDegree = new BigDecimal(3600)
	
	BigDecimal latDecimalDegrees
	double latRadians
	
	BigDecimal lngDecimalDegrees
	double lngRadians
	
	public GeoPosition(BigDecimal latDecimalDegrees, BigDecimal lngDecimalDegrees) {
		this.latDecimalDegrees = latDecimalDegrees
		this.lngDecimalDegrees = lngDecimalDegrees
		latRadians = Math.toRadians(this.latDecimalDegrees.doubleValue())
		lngRadians = Math.toRadians(this.lngDecimalDegrees.doubleValue())
	}
	
	public GeoPosition(int latDegrees, int latMinutes, int latSeconds, Direction latDirection, int lngDegrees, int lngMinutes, int lngSeconds, Direction lngDirection) {
		this.latDecimalDegrees = calculateLatDecimalDegrees(latDegrees, latMinutes, latSeconds, latDirection)
		this.lngDecimalDegrees = calculateLngDecimalDegrees(lngDegrees, lngMinutes, lngSeconds, lngDirection)
		latRadians = Math.toRadians(this.latDecimalDegrees.doubleValue())
		lngRadians = Math.toRadians(this.lngDecimalDegrees.doubleValue())
	}
	
	double distance(GeoPosition gp) {
		// calculate great-circle distance
		double distance = Math.acos(Math.sin(this.latRadians) * Math.sin(gp.latRadians) + Math.cos(this.latRadians) * Math.cos(gp.latRadians) * Math.cos(this.lngRadians - gp.lngRadians))
		
		// distance in human-readable format:
		// earth's radius in km = ~6371
		return 6371 * distance
	}
	
	BigDecimal calculateLatDecimalDegrees(int latDegrees, int latMinutes, int latSeconds, Direction latDirection) {
//		println "scale...........: $scale"
//		println "minutesPerDegree: $minutesPerDegree"
//		println "secondsPerDegree: $secondsPerDegree"
		
		BigDecimal d = new BigDecimal(latDegrees)
//		println "01: $d"
		
		BigDecimal m = new BigDecimal(latMinutes)
//		println "03: $m"
		m = m.divide(minutesPerDegree, MathContext.DECIMAL64)
//		println "04: $m"
		
		BigDecimal s = new BigDecimal(latSeconds)
//		println "05: $s"
		s = s.divide(secondsPerDegree, MathContext.DECIMAL64)
//		println "06: $s"
		
		BigDecimal r = d.add(m).add(s).setScale(scale, RoundingMode.UP)
		if (latDirection == Direction.South) {
			r = r.negate()
		}
//		println "09: $r"
		return r
	}
	
	BigDecimal calculateLngDecimalDegrees(int lngDegrees,	int lngMinutes, int lngSeconds, Direction lngDirection) {
		BigDecimal d = new BigDecimal(lngDegrees)
//		println "11: $d"
		
		BigDecimal m = new BigDecimal(lngMinutes)
//		println "13: $m"
		m = m.divide(minutesPerDegree, MathContext.DECIMAL64)
//		println "14: $m"
		
		BigDecimal s = new BigDecimal(lngSeconds)
//		println "15: $s"
		s = s.divide(secondsPerDegree, MathContext.DECIMAL64)
//		println "16: $s"
		
		BigDecimal r = d.add(m).add(s).setScale(scale, RoundingMode.HALF_UP)
		if (lngDirection == Direction.West) {
			r = r.negate()
		}
//		println "19: $r"
		return r
	}
}
