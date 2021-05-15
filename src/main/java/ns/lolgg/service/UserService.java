package ns.lolgg.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ns.lolgg.dao.MatchRepository;
import ns.lolgg.dao.UserRepository;
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
		
		user.refresh();
		List<String> matchIds = LolUtil.getSummonerMatchList(user.getPuuid());
		int len = matchIds.size();
		for (int i=0; i<len; i++) {
			String matchId = matchIds.get(i);
			if (matchRepo.findById(matchId).isEmpty()) {
				JSONObject obj = LolUtil.getMatchDetail(matchId);
				
			}
		}
	}
	
	@Override
	public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		return UserDetail.of(userRepo.findByUserId(username).orElseThrow(()->
			new UsernameNotFoundException(username)));
	}
}
