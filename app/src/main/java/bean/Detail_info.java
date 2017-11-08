package bean;

public class Detail_info {

	private String tag;
	private Navi_location navi_location;
	private String type;
	private String detail_url;
	private String price;
	private String overall_rating;
	private String groupon_num;
	private String comment_num;
	
	public Detail_info(){
		
	}

	public Detail_info(String tag, Navi_location navi_location, String type,
			String detail_url, String price, String overall_rating,
			String groupon_num, String comment_num) {
		
		this.tag = tag;
		this.navi_location = navi_location;
		this.type = type;
		this.detail_url = detail_url;
		this.price = price;
		this.overall_rating = overall_rating;
		this.groupon_num = groupon_num;
		this.comment_num = comment_num;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Navi_location getNavi_location() {
		return navi_location;
	}

	public void setNavi_location(Navi_location navi_location) {
		this.navi_location = navi_location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOverall_rating() {
		return overall_rating;
	}

	public void setOverall_rating(String overall_rating) {
		this.overall_rating = overall_rating;
	}

	public String getGroupon_num() {
		return groupon_num;
	}

	public void setGroupon_num(String groupon_num) {
		this.groupon_num = groupon_num;
	}

	public String getComment_num() {
		return comment_num;
	}

	public void setComment_num(String comment_num) {
		this.comment_num = comment_num;
	}

	@Override
	public String toString() {
		return  tag + "," + navi_location
				+ "," + type + ", " + detail_url + ", "
				+ price + ", " + overall_rating
				+ "," + groupon_num + ","
				+ comment_num;
	}

	
	
}
