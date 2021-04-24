package ns.lolgg.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ns.lolgg.domain.User;

public class UserDTO {

	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class UserLogin {
		private String id;
		private String password;
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class UserRegi {
		private String id;
		private String password;
		private String email;
		private String lolid;
		
		public User toEntity() {
			return User.builder()
					.userId(this.id)
					.userPassword(this.password)
					.userEmail(this.email)
					.userLolId(this.lolid).build();
		}
	}
}
