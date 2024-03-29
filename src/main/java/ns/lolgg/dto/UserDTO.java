package ns.lolgg.dto;

import java.io.IOException;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ns.lolgg.domain.User;
import ns.lolgg.util.LolUtil;

public class UserDTO {

	@NoArgsConstructor
	@AllArgsConstructor
	@Data
	@ToString
	@Builder
	public static class UserRegi {
		private String id;
		private String password;
		private String lolid;

		public User toEntity(JSONObject obj) throws ParseException, IOException {
			return User.builder().userId(this.id)
					.userPassword(this.password)
					.puuid((String) obj.get("puuid"))
					.profileIconId((Long) obj.get("profileIconId"))
					.userLolId(this.lolid)
					.level((Long) obj.get("summonerLevel"))
					.encLolId((String) obj.get("id"))
					.build();
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
