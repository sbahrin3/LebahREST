package lebah.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lebah.api.JSONArrayBase;


/**
 * 
 * @author shamsulbahrin
 * @since 12 Mar 2022
 */
public class Test2 extends JSONArrayBase {

	@Override
	public void doAction(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, JSONException {
		

			List<JSONObject> messages = new ArrayList<>();
			for ( int i=0; i < jsonArrayIn.length(); i++ ) {
				JSONObject o = jsonArrayIn.getJSONObject(i);
				String message = o.getString("Message");
				
				JSONObject obj = new JSONObject();
				obj.put("No", i);
				obj.put("Message", message);
				messages.add(obj);
			}		
			
			JSONArray array = new JSONArray();
			array.put(messages);
			
			out.print(array);

	}

}
