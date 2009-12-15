package voizletest;

import com.google.wave.api.ProfileServlet;

@SuppressWarnings("serial")
public class Profile extends ProfileServlet {

	@Override
	public String getRobotName() {
		return "VoizleStat";
	}

	@Override
	public String getRobotAvatarUrl() {
		return "http://voizlestat.appspot.com/images/expandv.jpg";
	}

	@Override
	public String getRobotProfilePageUrl() {
		return "http://www.voizle.com/";
	}

}
