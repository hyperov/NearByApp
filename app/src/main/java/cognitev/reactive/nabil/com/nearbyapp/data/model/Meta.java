package cognitev.reactive.nabil.com.nearbyapp.data.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Meta{

	@SerializedName("code")
	private int code;

	@SerializedName("requestId")
	private String requestId;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setRequestId(String requestId){
		this.requestId = requestId;
	}

	public String getRequestId(){
		return requestId;
	}
}