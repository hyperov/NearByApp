package cognitev.reactive.nabil.com.nearbyapp.data.model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Photos{

	@SerializedName("count")
	private int count;

	@SerializedName("groups")
	private List<Object> groups;

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setGroups(List<Object> groups){
		this.groups = groups;
	}

	public List<Object> getGroups(){
		return groups;
	}
}