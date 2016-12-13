package blog.vo;
public class User {
	private	int		id;
	private	String	name;
	private	String	grp;
	private String	pwd;
	private	String	regDate;
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	
	public void setName(String name){
		this.name=name;
	}
	public String getName(){
		return name;
	}
	
	public void setGrp(String grp){
		this.grp=grp;
	}
	public String getGrp(){
		return grp;
	}
	
	public void setPwd(String pwd){
		this.pwd=pwd;
	}
	public String getPwd(){
		return pwd;
	}
	
	public void setRegDate(String regDate){
		this.regDate=regDate;
	}
	public String getRegDate(){
		return regDate;
	}
}
