package bean;

public class WC {

	private String name;
	private Location location;
	private String address;
	private String street_id;
	private String telephone;
	private String detail;
	private String uid;
	private Detail_info detail_info;
	
	
	public WC(){
		
	}


	public WC(String name, Location location, String address, String street_id,
			String telephone, String detail, String uid, Detail_info detail_info) {
		
		this.name = name;
		this.location = location;
		this.address = address;
		this.street_id = street_id;
		this.telephone = telephone;
		this.detail = detail;
		this.uid = uid;
		this.detail_info = detail_info;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getStreet_id() {
		return street_id;
	}


	public void setStreet_id(String street_id) {
		this.street_id = street_id;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;
	}


	public Detail_info getDetail_info() {
		return detail_info;
	}


	public void setDetail_info(Detail_info detail_info) {
		this.detail_info = detail_info;
	}


	@Override
	public String toString() {
		return  name + "," + location + ", "
				+ address + ", " + street_id + ","
				+ telephone + ", " + detail + ", " + uid
				+ "," + detail_info;
	}

	
}
