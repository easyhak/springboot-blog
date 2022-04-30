package com.cos.blog.model;

import lombok.Data;

@Data
public class KakaoProfile {

	private Long id;
	private String connectedAt;
	private Properties properties;
	private KakaoAccount kakaoAccount;
	@Data
	public class Properties {
		private String nickname;
	}
	@Data
	public class KakaoAccount {
		private Boolean profileNicknameNeedsAgreement;
		private Profile profile;
		private Boolean hasEmail;
		public Boolean emailNeedsAgreement;
		public Boolean isEmailValid;
		public Boolean isEmailVerified;
		public String email;
		@Data
		public class Profile {
			public String nickname;
		}
	}
}
