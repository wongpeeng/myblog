package blog.vo;
public class User {
	private	int		id;
	private	String	name;
	private	String	grp;
	private String	pwd;
	private	String	createdate;
	
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
	
	public void setCreatedate(String createdate){
		this.createdate=createdate;
	}
	public String getCreatedate(){
		return createdate;
	}
}
