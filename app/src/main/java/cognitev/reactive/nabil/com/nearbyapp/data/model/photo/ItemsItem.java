package cognitev.reactive.nabil.com.nearbyapp.data.model.photo;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class ItemsItem{

	@SerializedName("createdAt")
	private int createdAt;

	@SerializedName("visibility")
	private String visibility;

	@SerializedName("prefix")
	private String prefix;

	@SerializedName("width")
	private int width;

	@SerializedName("id")
	private String id;

	@SerializedName("source")
	private Source source;

	@SerializedName("suffix")
	private String suffix;

	@SerializedName("user")
	private User user;

	@SerializedName("height")
	private int height;

	public void setCreatedAt(int createdAt){
		this.createdAt = createdAt;
	}

	public int getCreatedAt(){
		return createdAt;
	}

	public void setVisibility(String visibility){
		this.visibility = visibility;
	}

	public String getVisibility(){
		return visibility;
	}

	public void setPrefix(String prefix){
		this.prefix = prefix;
	}

	public String getPrefix(){
		return prefix;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setSource(Source source){
		this.source = source;
	}

	public Source getSource(){
		return source;
	}

	public void setSuffix(String suffix){
		this.suffix = suffix;
	}

	public String getSuffix(){
		return suffix;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"ItemsItem{" + 
			"createdAt = '" + createdAt + '\'' + 
			",visibility = '" + visibility + '\'' + 
			",prefix = '" + prefix + '\'' + 
			",width = '" + width + '\'' + 
			",id = '" + id + '\'' + 
			",source = '" + source + '\'' + 
			",suffix = '" + suffix + '\'' + 
			",user = '" + user + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}
}