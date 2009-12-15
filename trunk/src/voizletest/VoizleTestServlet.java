package voizletest;

import java.util.ArrayList;
import java.util.Hashtable;

import com.google.wave.api.AbstractRobotServlet;
import com.google.wave.api.Blip;
import com.google.wave.api.Element;
import com.google.wave.api.Event;
import com.google.wave.api.EventType;
import com.google.wave.api.Gadget;
import com.google.wave.api.Range;
import com.google.wave.api.RobotMessageBundle;
import com.google.wave.api.TextView;
import com.google.wave.api.Wavelet;

@SuppressWarnings("serial")
public class VoizleTestServlet extends AbstractRobotServlet {

	@Override
	public void processEvents(RobotMessageBundle bundle) {

		Wavelet wavelet = bundle.getWavelet();
		if (bundle.wasSelfAdded()) {
			Blip blip = wavelet.appendBlip();
			TextView textView = blip.getDocument();
			textView
					.appendMarkup("Thank you for adding VoizleStat. \n\r"
							+ "Expand Voizle short link and get statistics information of webpages.\n\r"
							+ "More Info: http://www.voizle.com/");
		}

		for (Event e : bundle.getEvents()) {
			if (e.getType() == EventType.BLIP_SUBMITTED) {
				addTask(e);
			}
		}
	}

	public void addTask(Event e) {
		SearchWord searchWord = new SearchWord();
		String startPattern = "http://u.voizle.com/";
		TextView cont = e.getBlip().getDocument();
		String blipText = cont.getText().replaceAll("\n\r", " ");
		searchWord.searchPattern(blipText, startPattern, " ");
		Hashtable<Integer, String> ReqTable = searchWord.getWords();
		ArrayList<Integer> list = searchWord.getIndex();

		for (Integer i : list) {
			String id = ReqTable.get(i);
			cont
					.delete(new Range(i, (startPattern.length() + i + id
							.length())));

			Element gad = new Gadget(
					"http://voizletest.appspot.com/gadgets.xml?id=" + id);
			cont.insertElement(i, gad);
		}
	}
}
