package cognitev.reactive.nabil.com.nearbyapp.data.model;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("com.robohorse.robopojogenerator")
public class TipsItem{

	@SerializedName("todo")
	private Todo todo;

	@SerializedName("createdAt")
	private int createdAt;

	@SerializedName("canonicalUrl")
	private String canonicalUrl;

	@SerializedName("logView")
	private boolean logView;

	@SerializedName("agreeCount")
	private int agreeCount;

	@SerializedName("id")
	private String id;

	@SerializedName("text")
	private String text;

	@SerializedName("type")
	private String type;

	@SerializedName("disagreeCount")
	private int disagreeCount;

	@SerializedName("user")
	private User user;

	@SerializedName("likes")
	private Likes likes;

	public void setTodo(Todo todo){
		this.todo = todo;
	}

	public Todo getTodo(){
		return todo;
	}

	public void setCreatedAt(int createdAt){
		this.createdAt = createdAt;
	}

	public int getCreatedAt(){
		return createdAt;
	}

	public void setCanonicalUrl(String canonicalUrl){
		this.canonicalUrl = canonicalUrl;
	}

	public String getCanonicalUrl(){
		return canonicalUrl;
	}

	public void setLogView(boolean logView){
		this.logView = logView;
	}

	public boolean isLogView(){
		return logView;
	}

	public void setAgreeCount(int agreeCount){
		this.agreeCount = agreeCount;
	}

	public int getAgreeCount(){
		return agreeCount;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setText(String text){
		this.text = text;
	}

	public String getText(){
		return text;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setDisagreeCount(int disagreeCount){
		this.disagreeCount = disagreeCount;
	}

	public int getDisagreeCount(){
		return disagreeCount;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setLikes(Likes likes){
		this.likes = likes;
	}

	public Likes getLikes(){
		return likes;
	}
}