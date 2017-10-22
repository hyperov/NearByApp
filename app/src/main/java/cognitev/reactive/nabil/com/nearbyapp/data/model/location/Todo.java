package cognitev.reactive.nabil.com.nearbyapp.data.model.location;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Todo{

	@SerializedName("count")
	private int count;

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}
}