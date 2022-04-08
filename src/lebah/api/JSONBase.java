/**
 * 
 */
package lebah.api;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author shamsulbahrin
 * @since 12 Mar 2022
 */
public abstract class JSONBase extends Base {
	
	protected JSONObject jsonIn;

	static JSONObject getJSONInput(HttpServletRequest req) throws IOException, JSONException {
		StringBuilder sb = new StringBuilder();
        BufferedReader br = req.getReader();
        String str = null;
        while ((str = br.readLine()) != null) sb.append(str);
        if ( sb.toString().equals("")) sb.append("{}");
        return new JSONObject(sb.toString());
	}

	@Override
	public void doService(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		init(req, res);
		try {
			jsonIn = getJSONInput(req);
			doAction(req, res);
		} catch (JSONException e) {
			e.printStackTrace(out);
		}
	}

}
