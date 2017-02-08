package blog.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class GuestFilter
 */
@WebFilter("/GuestFilter")
public class GuestFilter implements Filter {

    /**
     * Default constructor. 
     */
    public GuestFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here

		// pass the request along the filter chain
		boolean login=true;
		try {
			System.out.println("start filter guest!");
			HttpServletRequest req=(HttpServletRequest) request;
			HttpServletResponse res=(HttpServletResponse) response;
			HttpSession session=req.getSession(false);
			login=req.getServletPath().equals("/index.jsp")||req.getServletPath().equals("/login.do");
		} catch (Exception e) {
			System.out.println("fail to filter login!");
		}
		if(session==null && !login)((HttpServletResponse)response).sendRedirect("/myblog/index.jsp");
		else chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
