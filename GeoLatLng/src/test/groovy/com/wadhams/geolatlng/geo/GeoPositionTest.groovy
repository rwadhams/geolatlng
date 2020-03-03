package com.wadhams.geolatlng.geo

import com.wadhams.geolatlng.type.Direction
import spock.lang.Ignore
import spock.lang.Title

@Title("Unit tests for GeoPosition")
class GeoPositionTest extends spock.lang.Specification {
	
	@Ignore
	def "test constructor"() {
		given:
			GeoPosition gp = new GeoPosition(37, 47, 7, Direction.South, 145, 25, 52, Direction.East)
		
		expect:
			gp.latDegrees == 37
			gp.latMinutes == 47
			gp.latSeconds == 7
			gp.latDirection == Direction.South
			gp.lngDegrees == 145
			gp.lngMinutes == 25
			gp.lngSeconds == 52
			gp.lngDirection == Direction.East
	}
	
	def "test decimal degree methods"() {
		given:
			GeoPosition gp = new GeoPosition(37, 47, 7, Direction.South, 145, 25, 52, Direction.East)
		
		expect:
			gp.getLatDecimalDegrees() == new BigDecimal('-37.78527778')
			gp.getLngDecimalDegrees() == new BigDecimal('145.43111111')
	}

}
