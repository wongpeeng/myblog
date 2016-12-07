package blog.vo;
public class Blog {
	private	int		id;
	private	String	title;
	private	String	author;
	private String	content;
	private	String	pubDate;
	private String	revDate;
	private	int		views;
	private int		cmtnum;
	
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
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
	
	public void setPubDate(String pubDate){
		this.pubDate=pubDate;
	}
	public String getPubDate(){
		return pubDate;
	}
	
	public void setRevDate(String revDate){
		this.revDate=revDate;
	}
	public String getRevDate(){
		return revDate;
	}

	public void setViews(int views){
		this.views=views;
	}
	public int getViews(){
		return views;
	}
	
	public void setCmtnum(int cmtnum){
		this.cmtnum=cmtnum;
	}
	public int getCmtnum(){
		return cmtnum;
	}
}
