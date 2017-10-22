package cognitev.reactive.nabil.com.nearbyapp.data.model.photo;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ResponsePhoto{

	@SerializedName("photos")
	private Photos photos;

	public void setPhotos(Photos photos){
		this.photos = photos;
	}

	public Photos getPhotos(){
		return photos;
	}

	@Override
 	public String toString(){
		return 
			"Response{" + 
			"photos = '" + photos + '\'' + 
			"}";
		}
}