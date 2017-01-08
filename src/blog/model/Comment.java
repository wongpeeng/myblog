package blog.model;
public class Comment {
	private	int id;
	private int logId;
	private int cmtId;
	private String writer;
	private String pubDate;
	private String revDate;
	private String content;
	private int pros;
	private int cons;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public int getCmtId() {
		return cmtId;
	}
	public void setCmtId(int cmtId) {
		this.cmtId = cmtId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPubDate() {
		return pubDate;
	}
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getRevDate() {
		return revDate;
	}
	public void setRevDate(String revDate) {
		this.revDate = revDate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPros() {
		return pros;
	}
	public void setPros(int pros) {
		this.pros = pros;
	}
	public int getCons() {
		return cons;
	}
	public void setCons(int cons) {
		this.cons = cons;
	}
	
}
