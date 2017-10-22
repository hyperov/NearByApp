package cognitev.reactive.nabil.com.nearbyapp.data.model;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

import cognitev.reactive.nabil.com.nearbyapp.data.model.location.Response;

@Generated("com.robohorse.robopojogenerator")
public class ApiResponseLocation extends ApiResponseParent{



	@SerializedName("response")
	private Response response;


	public void setResponse(Response response){
		this.response = response;
	}

	public Response getResponse(){
		return response;
	}
}