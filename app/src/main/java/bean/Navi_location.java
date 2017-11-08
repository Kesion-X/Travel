package bean;

public class Navi_location {

	private Double lat;
	private Double  lng;
	
	public Navi_location(){
		
	}
	
	public Navi_location(Double lat, Double lng) {
		
		this.lat = lat;
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return lat + ", " + lng ;
	}	
	
	
}
