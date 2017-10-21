package cognitev.reactive.nabil.com.nearbyapp.data.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Response{

	@SerializedName("suggestedFilters")
	private SuggestedFilters suggestedFilters;

	@SerializedName("totalResults")
	private int totalResults;

	@SerializedName("headerFullLocation")
	private String headerFullLocation;

	@SerializedName("headerLocationGranularity")
	private String headerLocationGranularity;

	@SerializedName("groups")
	private List<GroupsItem> groups;

	@SerializedName("suggestedBounds")
	private SuggestedBounds suggestedBounds;

	@SerializedName("headerLocation")
	private String headerLocation;

	public void setSuggestedFilters(SuggestedFilters suggestedFilters){
		this.suggestedFilters = suggestedFilters;
	}

	public SuggestedFilters getSuggestedFilters(){
		return suggestedFilters;
	}

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	public void setHeaderFullLocation(String headerFullLocation){
		this.headerFullLocation = headerFullLocation;
	}

	public String getHeaderFullLocation(){
		return headerFullLocation;
	}

	public void setHeaderLocationGranularity(String headerLocationGranularity){
		this.headerLocationGranularity = headerLocationGranularity;
	}

	public String getHeaderLocationGranularity(){
		return headerLocationGranularity;
	}

	public void setGroups(List<GroupsItem> groups){
		this.groups = groups;
	}

	public List<GroupsItem> getGroups(){
		return groups;
	}

	public void setSuggestedBounds(SuggestedBounds suggestedBounds){
		this.suggestedBounds = suggestedBounds;
	}

	public SuggestedBounds getSuggestedBounds(){
		return suggestedBounds;
	}

	public void setHeaderLocation(String headerLocation){
		this.headerLocation = headerLocation;
	}

	public String getHeaderLocation(){
		return headerLocation;
	}
}