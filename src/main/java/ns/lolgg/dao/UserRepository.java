package ns.lolgg.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ns.lolgg.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	// 아이디로 user 찾기
	Optional<User> findByUserId(String userId);
	
	// 롤아이디로 user 찾기
	Optional<User> findByUserLolId(String userLolId);
}
