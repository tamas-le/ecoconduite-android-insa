$(document).ready(function () {

	var initialize_map = false;

	function generateRequest(address){
		return "http://maps.google.com/maps/api/geocode/json?address=" + address.replace(/ /g,"+") + "&sensor=false";
	}


	var request_moi = generateRequest("378 Chemin des espinaux à la bedosse, 30100, Alès"),
		request_pm = generateRequest("10 rue Pierre Loti, 30100, Alès"),
		cord_moi, cord_pm;

	

	var directionsDisplay;
	var directionsService = new google.maps.DirectionsService();
	var map;

	function initialize() {
	  directionsDisplay = new google.maps.DirectionsRenderer();
	  var ales = new google.maps.LatLng(44.1267887, 4.119556800000055),
		  mapOptions = {
		    zoom:9,
		    // waypoints: waypts,
		    // optimizeWaypoints: true,
		    center: ales
	  	  }
	  map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
	  directionsDisplay.setMap(map);
	}

	function calcRoute(start, end) {
	  var request = {
	    origin: new google.maps.LatLng(44.1267887, 4.1195568),
	    destination: new google.maps.LatLng(44.133818, 4.09509930000001),
	    	    waypoints: [{
		      location: new google.maps.LatLng(44.12195699999999, 4.091671499999999)
		    }],

	    travelMode: google.maps.TravelMode.DRIVING
	  };
	  directionsService.route(request, function(result, status) {
	    if (status == google.maps.DirectionsStatus.OK) {
	      directionsDisplay.setDirections(result);
	      console.log(result.routes[0].legs[0].duration.value);
	      console.log(result.routes[0].legs[1].duration.value);
	      //routes 0 car pas d'alternative possible
	      //legs 0 car pas de waypoint, s'il y avait 1 wyapoint => 2 legs
	    }
	  });
	}


	$.getJSON(request_moi, function(data) {
		if(data.results.length === 1){ //un seul résultat les amis
			cord_moi = new google.maps.LatLng(data.results[0].geometry.location.lat, data.results[0].geometry.location.lng);
			console.log(cord_moi);
			if(initialize){
				initialize();
				calcRoute(cord_moi, cord_pm);
			}else{
				initialize_map = true;
			}
		}else{
			console.log("Erreur adresse trop imprécise!!");
		}
	});

	$.getJSON(request_pm, function(data) {
		if(data.results.length === 1){ //un seul résultat les amis
			cord_pm = new google.maps.LatLng(data.results[0].geometry.location.lat, data.results[0].geometry.location.lng);
			console.log(cord_pm);
			if(initialize){
				initialize();
				calcRoute(cord_moi, cord_pm);
			}else{
				initialize_map = true;
			}
		}else{
			console.log("Erreur adresse trop imprécise!!");
		}
	});


//https://maps.googleapis.com/maps/api/directions/json?origin=Chicago,IL&destination=Los+Angeles,CA&waypoints=Joplin,MO|Oklahoma+City,OK&key=AIzaSyASDA0eZvH1781uZidsoWCbt_8s4aj1P88


//https://maps.googleapis.com/maps/api/directions/json?origin=44.1267887+4.1195568&destination=44.1338180+4.0950993&key=AIzaSyASDA0eZvH1781uZidsoWCbt_8s4aj1P88

});