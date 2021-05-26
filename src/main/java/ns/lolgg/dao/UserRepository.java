package ns.lolgg.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ns.lolgg.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	// 아이디로 user 찾기
	Optional<User> findByUserId(String userId);
	
	// 롤아이디로 user 찾기
	@Query(value = "select * from lol_user where upper(lol_user.user_lol_id)=upper(:userLolId)", nativeQuery = true)
	Optional<User> findByUserLolId(String userLolId);
	
	Optional<User> findByPuuid(String puuid);
}
