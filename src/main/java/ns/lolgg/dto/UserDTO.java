package ns.lolgg.dto;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
	@ToString
	public static class UserRegi {
		private String id;
		private String password;
		private String email;
		private String lolid;

		public User toEntity() {
			return User.builder().userId(this.id).userPassword(this.password).userEmail(this.email)
					.userLolId(this.lolid).build();
		}
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@Builder
	public static class UserDetail {
		private String id;
		private Set<GrantedAuthority> role;
	}
}
