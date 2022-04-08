package lebah.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import lebah.api.JSONBase;

public class Test extends JSONBase {

	@Override
	public void doAction(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, JSONException {

		String message = "No Message";
		try {
			message = jsonIn.getString("Message");
		} catch (JSONException e ) {
			
		}
		
		jsonOut.put("message", message);
		jsonOut.put("test", "ok");
		
		out.print(jsonOut);
	}

}
