package ns.lolgg.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

@Component
public class LolUtil {

	private static HttpClient client = HttpClientBuilder.create().build();
	private static ResponseHandler<String> handler = new BasicResponseHandler();

	// @Value("${lol.api.key}")
	private static String key = "RGAPI-431177fe-1750-45c7-b032-06f8d06b3e13";

	public static String getKey() {
		return key;
	}
	
	private static String common(HttpGet getRequest) throws IOException {

		HttpResponse response = client.execute(getRequest);
		if (response.getStatusLine().getStatusCode() == 200) {
			return handler.handleResponse(response);
		}
		return null;
	}
	
	public static List<String> stringToList(String s){
		
		String[] sss = s.substring(1, s.length() - 1).split(",");
		List<String> answer = new ArrayList<>();
		int len = sss.length;
		for (int i = 0; i < len; i++) {
			answer.add(sss[i].substring(1, sss[i].length() - 1));
		}
		return answer;
	}

	/**
	 * LOL 닉네임을 통해 Summoner Id의 Stat을 반환한다.
	 * @return Summoner Stat Json 객체
	 * @param LOL 닉네임
	 */
	public static JSONObject getSummoners(String userName) throws ParseException, IOException {

		return (JSONObject) new JSONParser().parse(common(
				new HttpGet("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + userName + "?api_key=" + key)));
	}
	
	/**
	 * Summoner의 puuid를 통해 최근 20경기 Match List를 반환한다.
	 * @return 20개의 Match Id List 
	 * @param Summoner의 puuid
	 */
	public static List<String> getSummonerMatchList(String puuid) throws IOException {

		String s = common(new HttpGet(
				"https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?api_key=" + key));
		return stringToList(s);
	}

	/**
	 * Match Id를 통해 해당 Match의 정보를 가져온다.
	 * @return Match detatil Json Object
	 * @param Match Id
	 */
	public static JSONObject getMatchDetail(String matchId) throws ParseException, IOException {
		
		return (JSONObject) new JSONParser().parse(common(
				new HttpGet("https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + key)));
	}
}
