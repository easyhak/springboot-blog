package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

// 인증이 안된 사용자들이 출입할 수 있는 경로 /auth/** 허용
// 그냥 주소가 / 이면 index.jsp 허용
// static 이하에 있는  /js/**, /css/**, //image/**
@Controller
public class UserController {

	@Value("${cos.key}")
	private String cosKey;
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}

	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) { // 데이터를 return해주는 controller

		// post방식으로 key value 타입의 데이터를 요청
		// Retrofit2, OkHttp, RestTemplate

		RestTemplate rt = new RestTemplate();

		// Httpheader object 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		// httpbody object 생성
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "a9b6d7b0798909ecdd9d8164e48915ec");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);

		// httpheader와 httpbody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

		// http요청하기 = post방식으로 - 그리고 response변수의 응답 받음
		ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST,
				kakaoTokenRequest, String.class);

		// Gson, JsonSimple, Object Mapper
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("카카오 엑세스 토큰: " + oauthToken.getAccess_token());

		RestTemplate rt2 = new RestTemplate();

		// Httpheader object 생성
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer "+oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me", 
				HttpMethod.POST,
				kakaoProfileRequest2, 
				String.class
			);

		
		ObjectMapper objectMapper2 = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
		KakaoProfile kakaoProfile = null;
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// User object: username, password, email
		System.out.println("카카오 아이디: "+ kakaoProfile.getId());
		System.out.println("카카오 이메일: "+ kakaoProfile.getKakaoAccount().getEmail());
		
		System.out.println("블로그서버 username: "+ kakaoProfile.getKakaoAccount().getEmail()+"_"+ kakaoProfile.getId());
		System.out.println("블로그서버 email: " + kakaoProfile.getKakaoAccount().getEmail());
		// UUID -> 중복되지 않는 특정 값을 만들어내는 알고리즘
		System.out.println("블로그서버 패스워드: "+ cosKey);
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakaoAccount().getEmail()+"_"+ kakaoProfile.getId())
				.email(kakaoProfile.getKakaoAccount().getEmail())
				.password(cosKey)
				.oauth("kakao")
				.build();
		
		// 가입자인지 아닌지 체크 
		User originUser = userService.회원찾기(kakaoUser.getUsername());
		if (originUser.getUsername() == null) {
			System.out.println("기존 회원이 아닙니다.");
			userService.회원가입(kakaoUser);
		}
		
		// 로그인 처리
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	
		return "redirect:/";
	}
}
