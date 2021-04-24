package ns.lolgg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ns.lolgg.dao.UserRepository;
import ns.lolgg.domain.User;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

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
		userRepo.save(user);
	}
}
