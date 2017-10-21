package cognitev.reactive.nabil.com.nearbyapp.data.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class SuggestedFilters{

	@SerializedName("header")
	private String header;

	@SerializedName("filters")
	private List<FiltersItem> filters;

	public void setHeader(String header){
		this.header = header;
	}

	public String getHeader(){
		return header;
	}

	public void setFilters(List<FiltersItem> filters){
		this.filters = filters;
	}

	public List<FiltersItem> getFilters(){
		return filters;
	}
}