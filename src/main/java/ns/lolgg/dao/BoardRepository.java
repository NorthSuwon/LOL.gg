package ns.lolgg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ns.lolgg.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{

}
