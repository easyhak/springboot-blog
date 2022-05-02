package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//ORM -> JAVA Object -> 다른 언어로 mapping 해준다
@Entity // User 클래스가 Mysql에 태이블이 생성된다.
// @DynamicInsert // insert시 null인 필드를 제거해준다
public class User {
	
	@Id //PrimaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY)// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스, auto-increment
	
	@Column(nullable = false, length = 100, unique = true)
	private String username; 
	
	@Column(nullable = false, length = 100) // 비밀번호를 암호화하기 때문 길이 충분하게
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("user")
	//DB는 RoleType이라는게 없다.
	@Enumerated(EnumType.STRING)
	private  RoleType role; // Enum을 쓰는게 좋음 //ADMIN ,USER
	
	private String oauth; //kakao, google
	
	@CreationTimestamp // 현재 시간이 자동 입력
	private Timestamp createDate;
	
}
