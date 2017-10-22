package cognitev.reactive.nabil.com.nearbyapp.data.model.location;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Location{

	@SerializedName("cc")
	private String cc;

	@SerializedName("country")
	private String country;

	@SerializedName("address")
	private String address;

	@SerializedName("labeledLatLngs")
	private List<LabeledLatLngsItem> labeledLatLngs;

	@SerializedName("lng")
	private double lng;

	@SerializedName("distance")
	private int distance;

	@SerializedName("formattedAddress")
	private List<String> formattedAddress;

	@SerializedName("city")
	private String city;

	@SerializedName("state")
	private String state;

	@SerializedName("lat")
	private double lat;

	public void setCc(String cc){
		this.cc = cc;
	}

	public String getCc(){
		return cc;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setLabeledLatLngs(List<LabeledLatLngsItem> labeledLatLngs){
		this.labeledLatLngs = labeledLatLngs;
	}

	public List<LabeledLatLngsItem> getLabeledLatLngs(){
		return labeledLatLngs;
	}

	public void setLng(double lng){
		this.lng = lng;
	}

	public double getLng(){
		return lng;
	}

	public void setDistance(int distance){
		this.distance = distance;
	}

	public int getDistance(){
		return distance;
	}

	public void setFormattedAddress(List<String> formattedAddress){
		this.formattedAddress = formattedAddress;
	}

	public List<String> getFormattedAddress(){
		return formattedAddress;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	public void setLat(double lat){
		this.lat = lat;
	}

	public double getLat(){
		return lat;
	}
}