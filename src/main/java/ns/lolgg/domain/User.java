package ns.lolgg.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

@Entity(name = "LOL_USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_NUM")
	private Long userNum;
	
	@Column(name = "USER_ID", length = 31)
	private String userId;
	
	@Column(name = "USER_PASSWORD", length = 31)
	private String userPassword;
	
	@Column(name = "USER_LOL_ID")
	private String userLolId;
	
	@Column(name = "USER_EMAIL", length = 127)
	private String userEmail;
	
	@OneToMany(mappedBy = "user", fetch=FetchType.LAZY)
	private List<Board> boards;
}
