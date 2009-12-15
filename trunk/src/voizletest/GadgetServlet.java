package voizletest;

import java.io.IOException;
import java.util.Hashtable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class GadgetServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/xml");

		String vid = req.getParameter("id");
		ReadXml rx = new ReadXml();
		Hashtable<String, String> result = rx.getResult(vid);
		resp
				.getWriter()
				.println(
						"<?xml version='1.0' encoding='UTF-8'?>"
								+ "<Module>"
								+ "<ModulePrefs"
								+ " title='VoizleTest'"
								+ " author='Love sharma'"
								+ " author_email='love.sharma.87@gmail.com'"
								+ " width='350'"
								+ " height='150'>"
								+ " <Require feature='wave'/>"
								+ " <Require feature='dynamic-height'/> "
								+ "<Require feature='com.google.gadgets.analytics' /> "
								+ " </ModulePrefs>"
								+ " <Content type='html' view='canvas'><![CDATA["
								+ "<style>"
								+ "div, table, td, a{"
								+ "font-size:10pt;"
								+ "}.header{"
								+ "background-color:#73C164;"
								+ "color:#FFFFFF;"
								+ "height:25px;"
								+ "font-size:12pt;"
								+ "font-weight:bolder;"
								+ "}"
								+ ".header a:visited, .header a{"
								+ "color:#ffffff;"
								+ "text-decoration:none;"
								+ "padding-left:10px;"
								+ "}"
								+ ".bor{"
								+ "height:150px;"
								+ "width:350px;"
								+ "}"
								+ "tfoot{"
								+ "background-color:#BAE2B2"
								+ "}"
								+ "tfoot td{"
								+ "		padding-left:10px;}"
								+ ".orig{"
								+ "		text-align:center;"
								+ "		padding:10px;}"
								+ "</style>"
								+ "<div class='bor'>"
								+ "<table border='1' frame='box' rules='all' bordercolor='#000099' width='100%' height='100%' align='center' style='margin-bottom:5px'>"
								+ "<thead>" + "<tr class='header'>"
								+ "<td colspan='2'>" + "<a href='"
								+ result.get("voizleurl")
								+ "' target='_blank'>"
								+ result.get("voizleurl")
								+ "</a>"
								+ "</td>"
								+ "</tr>"
								+ "</thead>"
								+ "<tbody>"
								+ "<tr>"
								+ "<td class='orig' colspan='2'>"
								+ "<a href='"
								+ result.get("originalurl")
								+ "' target='_blank'>"
								+ result.get("originalurl")
								+ "</a>"
								+ "</td>"
								+ "</tr>"
								+ "</tbody>"
								+ "<tfoot>"
								+ "<tr>"
								+ "<td width='50%'>Referrers: </td>"
								+ "<td><a target='_blank' href='http://www.voizle.com/preview.vz?id="
								+ result.get("urlid")
								+ "&referer=true'>"
								+ result.get("refs")
								+ "</a></td>"
								+ "</tr>"
								+ "<tr>"
								+ "<td>Hits: </td>"
								+ "<td><a target='_blank' href='http://www.voizle.com/preview.vz?id="
								+ result.get("urlid")
								+ "&stat=true'>"
								+ result.get("clicks")
								+ "</a></td>"
								+ "</tr>"
								+ "</tfoot>"
								+ "</table>"
								+ "</div><script type='text/javascript'> var ga = new _IG_GA('UA-9339177-1');"
								+ "ga.reportPageview('/ExpandVoizle');"
								+ "gadgets.window.adjustHeight();</script>"
								+ "]]></Content></Module>");
	}
}
