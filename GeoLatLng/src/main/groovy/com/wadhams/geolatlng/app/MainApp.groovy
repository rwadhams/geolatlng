package com.wadhams.geolatlng.app

import com.wadhams.geolatlng.geo.GeoPosition
import com.wadhams.geolatlng.geo.Proximity
import com.wadhams.geolatlng.type.Direction

class MainApp {
	static main(args) {
		println 'MainApp started...'
		println ''

		MainApp app = new MainApp()
		app.execute()
		
		println 'MainApp ended.'
	}
	
	def execute() {
		println 'execute...'
		println ''
		
		GeoPosition wandinNorth = new GeoPosition(37, 47, 7, Direction.South, 145, 25, 52, Direction.East)
		println "Wandin North....: ${wandinNorth.getLatDecimalDegrees()}, ${wandinNorth.getLngDecimalDegrees()}"
		println ''
		
		GeoPosition melbourne = new GeoPosition(-37.81394, 144.96342)
		println "Melbourne.......: ${melbourne.getLatDecimalDegrees()}, ${melbourne.getLngDecimalDegrees()}"
		println ''
		
		//double distanceKm = GeoLatLng.distance(wandinNorth, melbourne)
		println "Distance(km) from Wandin North to Melbourne: ${wandinNorth.distance(melbourne)}"
		println ''
		
		Proximity p1 = new Proximity(wandinNorth, 10d)
		//println p1
		println "Is Melbourne within 10km of Wandin North? ${p1.nearBy(melbourne)}"
		println ''
		
		Proximity p2 = new Proximity(wandinNorth, 50d)
		//println p2
		println "Is Melbourne within 50km of Wandin North? ${p2.nearBy(melbourne)}"
		println ''
		
	}
}
