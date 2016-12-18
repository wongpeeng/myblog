package blog.util;

public class CharacterReplace {
	public static String titleReplace(String title){
		String t=title;
		t=t.replace("&", "&amp;");
		t=t.replace("<","&lt;");
		t=t.replace(">","&gt;");
		t=t.replace("\r\n", "");//remoce \r\n
		t=t.replace("\n", "");//remoce \r\n
		t=t.replace(" ", "&nbsp;");//replace space
		t=t.replace("\"","&quot;");//replace "
		t=t.replace("\'", "&apos;");//replace '
		return t;
	}
	public static String contentReplace(String content){
		String c=content;
		c=c.replace("&", "&amp;");
		c=c.replace("<","&lt;");
		c=c.replace(">","&gt;");
		c=c.replace("\r\n", "<br>");//\r\n
		c=c.replace("\n", "<br>");//\n
		c=c.replace(" ", "&nbsp;");//replace space
		c=c.replace("\"","&quot;");//replace "
		c=c.replace("\'", "&apos;");//replace '
		return c;
	}
}
