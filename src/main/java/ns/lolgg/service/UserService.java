package ns.lolgg.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ns.lolgg.dao.MatchRepository;
import ns.lolgg.dao.MatchUserRepository;
import ns.lolgg.dao.UserRepository;
import ns.lolgg.domain.Match;
import ns.lolgg.domain.MatchUser;
import ns.lolgg.domain.User;
import ns.lolgg.dto.UserDTO.UserDetail;
import ns.lolgg.dto.UserDTO.UserRegi;
import ns.lolgg.util.LolUtil;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private MatchRepository matchRepo;
	@Autowired
	private MatchUserRepository matchUserRepo;
	@Autowired
	private LolUtil lolUtil;
	
	
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// 유저 찾기
	public User findUserById(String userId) {
		return userRepo.findByUserId(userId).orElse(null);
	}

	// 회원가입
	public void registerUser(UserRegi userRegi) throws ParseException, IOException {
		User user = userRepo.findByUserLolId(userRegi.getLolid()).orElse(null);
		if (user == null) {
			user = userRegi.toEntity();
		}
		user.setUserId(userRegi.getId());
		user.setUserPassword(passwordEncoder.encode(userRegi.getPassword()));
		userRepo.save(user);
	}
	
	public Optional<User> findUserByLolId(String userLolId){
		return userRepo.findByUserLolId(userLolId);
	}
	
	public void refreshUser(User user) throws ParseException, IOException {
		
		user.refresh(lolUtil.getSummonersByPuuid(user.getPuuid()));
		List<String> matchIds = lolUtil.getSummonerMatchList(user.getPuuid());
		int len = matchIds.size();
		for (int i=0; i<len; i++) {
			String matchId = matchIds.get(i);
			if (matchRepo.findById(matchId).isEmpty()) {
				JSONObject obj = lolUtil.getMatchDetail(matchId);
				refreshMatch(obj, matchId);
			}
		}
	}
	
	private void refreshMatch(JSONObject obj, String matchId) throws ParseException, IOException {
		
		JSONObject info = (JSONObject) obj.get("info");
		Match match = Match.builder().matchId(matchId)
			.gameCreation((Long) info.get("gameCreation"))
			.gameDuration((Long) info.get("gameDuration"))
			.build();
		
		
		JSONArray participantsInfo = (JSONArray) info.get("participants");
		String[] kill = {"NONE", "doubleKills", "tripleKills", "quadraKills", "pentaKills"};
		
		List<User> users = new ArrayList<>();
		
		int len = participantsInfo.size();
		for (int idx=0; idx<len; idx++) {
			
			JSONObject detail = (JSONObject) participantsInfo.get(idx);
			Long killIdx = (Long) detail.get("largestMultiKill");
			
			System.out.println((String) detail.get("summonerName"));
			User user = userRepo.findByUserLolId((String) detail.get("summonerName")).orElse(null);
			if (user==null) {
				try{
					user = searchPuuid((String) detail.get("summonerId"));
					users.add(user);
					System.out.println(user.getUserLolId());
				} catch (Exception e){
					continue;
				}
			}
			
//			MatchUser matchUser = MatchUser.builder()
//				.championName((String) detail.get("championName"))
//				.level((Long) detail.get("champLevel"))
//				.cs((Long) detail.get("totalMinionsKilled"))
//				.kills((Long) detail.get("kills"))
//				.deaths((Long) detail.get("deaths"))
//				.assists((Long) detail.get("assists"))
//				.win((boolean) detail.get("win"))
//				.firstBloodKill((boolean) detail.get("firstBloodKill"))
//				.maxKill(kill[killIdx.intValue()])
//				.lane((String) detail.get("lane"))
//				.primaryStyle((Long) detail.get("primaryStyle"))
//				.subStyle((Long) detail.get("subStyle"))
//				.summoner1Casts((Long) detail.get("summoner1Casts"))
//				.summoner1Id((Long) detail.get("summoner1Id"))
//				.summoner2Casts((Long) detail.get("summoner2Casts"))
//				.summoner2Id((Long) detail.get("summoner2Id"))
//				.item0((Long) detail.get("item0"))
//				.item1((Long) detail.get("item1"))
//				.item2((Long) detail.get("item2"))
//				.item3((Long) detail.get("item3"))
//				.item4((Long) detail.get("item4"))
//				.item5((Long) detail.get("item5"))
//				.item6((Long) detail.get("item6"))
//				.teamId((Long) detail.get("teamId"))
//				.match(match)
//				.user(user)
//				.build();

		}
		matchRepo.save(match);
		userRepo.saveAll(users);
		//matchUserRepo.save(null)
	}
	
	public User searchUser(String lolid) throws ParseException, IOException {
		JSONObject obj;
		try {
			obj = lolUtil.getSummoners(lolid);
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return userRepo.save(
				User.builder()
					.userId("!!!!!")
					.userPassword("!!!!!")
					.puuid((String) obj.get("puuid"))
					.profileIconId((Long) obj.get("profileIconId"))
					.userLolId((String) obj.get("name"))
					.level((Long) obj.get("summonerLevel"))
					.encLolId((String) obj.get("id"))
					.build()
				);
	}
	
	public User searchPuuid(String puuid) throws ParseException, IOException {
		JSONObject obj = lolUtil.getSummonersByPuuid(puuid);
		return User.builder()
					.userId("!!!!!")
					.userPassword("!!!!!")
					.puuid((String) obj.get("puuid"))
					.profileIconId((Long) obj.get("profileIconId"))
					.userLolId((String) obj.get("name"))
					.level((Long) obj.get("summonerLevel"))
					.encLolId((String) obj.get("id"))
					.build();
	}
	
	
	@Override
	public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		return UserDetail.of(userRepo.findByUserId(username).orElseThrow(()->
			new UsernameNotFoundException(username)));
	}
}
