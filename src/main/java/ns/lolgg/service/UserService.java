package ns.lolgg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ns.lolgg.dao.UserRepository;
import ns.lolgg.domain.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	private PasswordEncoder encoder = new BCryptPasswordEncoder();

	// 유저 찾기
	public User findUserById(String userId) {
		
		return userRepo.findByUserId(userId).orElse(null);
	}

	// 비밀번호 확인
	public boolean checkUserPassword(User user, String password) {

		if (encoder.matches(password, user.getUserPassword())) {
			return true;
		}
		return false;
	}

	// 회원가입
	public void registerUser(User user) {
		
		user.setUserPassword(encoder.encode(user.getUserPassword()));
		userRepo.save(user);
	}
}
