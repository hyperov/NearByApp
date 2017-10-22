package cognitev.reactive.nabil.com.nearbyapp.data.model.location;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class Hours{

	@SerializedName("isOpen")
	private boolean isOpen;

	@SerializedName("richStatus")
	private RichStatus richStatus;

	@SerializedName("isLocalHoliday")
	private boolean isLocalHoliday;

	@SerializedName("status")
	private String status;

	public void setIsOpen(boolean isOpen){
		this.isOpen = isOpen;
	}

	public boolean isIsOpen(){
		return isOpen;
	}

	public void setRichStatus(RichStatus richStatus){
		this.richStatus = richStatus;
	}

	public RichStatus getRichStatus(){
		return richStatus;
	}

	public void setIsLocalHoliday(boolean isLocalHoliday){
		this.isLocalHoliday = isLocalHoliday;
	}

	public boolean isIsLocalHoliday(){
		return isLocalHoliday;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}