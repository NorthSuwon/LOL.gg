package ns.lolgg.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BOARD_NUM")
	private Long boardNum;
	
	@Column(name = "POSITION", length = 127)
	private String position;
	
	@Column(name = "CONTENT", length = 255)
	private String content;
	
	@Column(name = "CREATE_DATE")
	private LocalDate createDate;
	
	@ManyToOne
	@JoinColumn(name = "USER_NUM")
	private User user;
}