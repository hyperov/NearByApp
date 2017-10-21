package cognitev.reactive.nabil.com.nearbyapp.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Stats{

	@SerializedName("checkinsCount")
	private int checkinsCount;

	@SerializedName("usersCount")
	private int usersCount;

	@SerializedName("tipCount")
	private int tipCount;

	public void setCheckinsCount(int checkinsCount){
		this.checkinsCount = checkinsCount;
	}

	public int getCheckinsCount(){
		return checkinsCount;
	}

	public void setUsersCount(int usersCount){
		this.usersCount = usersCount;
	}

	public int getUsersCount(){
		return usersCount;
	}

	public void setTipCount(int tipCount){
		this.tipCount = tipCount;
	}

	public int getTipCount(){
		return tipCount;
	}
}