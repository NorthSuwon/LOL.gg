package ns.lolgg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ns.lolgg.dao.UserRepository;
import ns.lolgg.domain.User;
import ns.lolgg.dto.UserDTO.UserDetail;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	// 유저 찾기
	public User findUserById(String userId) {
		return userRepo.findByUserId(userId).orElse(null);
	}

	// 비밀번호 확인
	public boolean checkUserPassword(User user, String password) {

		if (password.equals(user.getUserPassword())) {
			return true;
		}
		return false;
	}

	// 회원가입
	public void registerUser(User user) {
		user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		userRepo.save(user);
	}

	@Override
	public UserDetail loadUserByUsername(String username) throws UsernameNotFoundException {
		return UserDetail.of(userRepo.findByUserId(username).orElseThrow(()->
			new UsernameNotFoundException(username)));
	}
}
