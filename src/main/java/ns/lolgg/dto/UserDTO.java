package ns.lolgg.dto;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class UserAuthority implements GrantedAuthority {
		private String authority;
	}
	
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Builder
	public static class UserDetail implements UserDetails{
		
		private String UserName;
		private String UserKey;
		@JsonIgnore
		private Set<UserAuthority> authorities; 
		private boolean enabled;
		
		@Override
		public String getPassword() {
			return UserKey;
		}
		@Override
		public String getUsername() {
			return UserName;
		}
		@Override
		public boolean isAccountNonExpired() {
			return enabled;
		}
		@Override
		public boolean isAccountNonLocked() {
			return enabled;
		}
		@Override
		public boolean isCredentialsNonExpired() {
			return enabled;
		}
		
		public static UserDetail of(User User) {
			return UserDetail.builder().UserName(User.getUserId()).UserKey(User.getUserPassword())
					.authorities(Set.of(new UserAuthority("ROLE_USER"))).enabled(true).build();
		}
	}
}
