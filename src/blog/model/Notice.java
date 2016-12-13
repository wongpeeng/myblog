package blog.model;
public class Notice {
	private	int		id;
	private int		level;
	private	String	title;
	private	String	author;
	private String	content;
	private	String	strDate;
	private String	endDate;
	
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	
	public void setLevel(int level){
		this.level=level;
	}
	public int getLevel(){
		return level;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
	}
	
	public void setAuthor(String author){
		this.author=author;
	}
	public String getAuthor(){
		return author;
	}
	
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	
	public void setStrDate(String strDate){
		this.strDate=strDate;
	}
	public String getStrDate(){
		return strDate;
	}
	
	public void setEndDate(String endDate){
		this.endDate=endDate;
	}
	public String getEndDate(){
		return endDate;
	}
}
