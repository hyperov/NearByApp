package cognitev.reactive.nabil.com.nearbyapp.data.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ApiResponse{

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("response")
	private Response response;

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}
}