/**
 * 
 */
package lebah.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import lebah.servlets.RestServlet;

/**
 * 
 * @author shamsulbahrin
 * @since 12 Mar 2022
 */
public abstract class Base implements RestServlet {
	
	protected PrintWriter out;
	protected String serverUrl;
	protected JSONObject jsonOut = new JSONObject();
	
	String getServerUrl(HttpServletRequest req) {
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
        String server = serverPort != 80 ? serverName + ":" + serverPort : serverName;
        String http = req.getRequestURL().toString().substring(0, req.getRequestURL().toString().indexOf("://") + 3);
        String serverUrl = http + server;		
		String uri = req.getRequestURI();
		String s1 = uri.substring(1);
		String appName = s1.substring(0, s1.indexOf("/"));
        String url = serverUrl + "/" + appName;
		return url;
	}
	
	public abstract void doAction(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, JSONException;

	void init(HttpServletRequest req, HttpServletResponse res)  throws IOException, ServletException {

		res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");

		serverUrl = getServerUrl(req);
		out = res.getWriter();
	}
	
	@Override
	public void doService(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		init(req, res);
		
		try {
			doAction(req, res);

		} catch (JSONException e) {
			e.printStackTrace(out);
		}

	}

}
