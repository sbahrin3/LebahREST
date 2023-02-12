package lebah.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RestTemplate extends HttpServlet {
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException    {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
		PrintWriter out = res.getWriter();
        String pathInfo = req.getPathInfo();
        pathInfo = pathInfo != null ? pathInfo.substring(1) : ""; //get rid of the first '/'
		if ( !"".equals(pathInfo) ) {
			String module = pathInfo;
			module = module.replace("/", ".");
			String cname = module.substring(module.lastIndexOf(".") + 1);
			cname = cname.substring(0,1).toUpperCase() + cname.substring(1);
			module = module.substring(0, module.lastIndexOf(".")) + "." + cname;
			try {
				Object object = Class.forName(module).newInstance();	
				if ( object instanceof RestServlet ) {
					((RestServlet) object).doService(req, res);
				}
			} catch ( ClassNotFoundException cnfex ) {
				cnfex.printStackTrace();
				out.print("Module Not Found Error: " + module);
			} catch ( InstantiationException iex ) {
				iex.printStackTrace();
				out.print("Module Instantiation Error: " + module);
			} catch ( IllegalAccessException illex ) {
				illex.printStackTrace();
				out.print("Illegal Access Error: " + module);
			} catch ( Exception ex ) {
				ex.printStackTrace();
				out.print("Module Error: " + module);
			}	
		}

	}
	
}
