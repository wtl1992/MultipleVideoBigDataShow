package biz.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SysContext {
	private static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	
	public static String PROJECT_ROOT;// 项目的根目录路径

	public static String ACCESS_TOKEN;//全局access_token

	/**
	 * 微信公众号的appId和AppSecret
	 */
	public static String AppID = "wx3670540bf9bd6c3b";
	public static String AppSecret = "91607abedaecda24e943f1383500a37a";

	static {
		String nodepath = SysContext.class.getClassLoader().getResource("/").getPath();  
		// 项目的根目录路径  
		if ("Windows".equals(System.getProperty("os.name").split(" ")[0])) {
			PROJECT_ROOT = nodepath.substring(1, nodepath.length() - 16);
		}
		else {
			/**
			 * 解决linux根/无法显示，
			 * 例如：/mine，而程序获得的是mine,没有/
			 */
			PROJECT_ROOT = nodepath.substring(0, nodepath.length() - 16);
		}
	}
	
	
	public static HttpServletRequest getRequest() {
		return requestLocal.get();
	}

	public static void setRequest(HttpServletRequest request) {
		requestLocal.set(request);
	}

	public static HttpServletResponse getResponse() {
		return responseLocal.get();
	}

	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}

	public static HttpSession getSession() {
		return (HttpSession) (getRequest()).getSession();
	}
}
