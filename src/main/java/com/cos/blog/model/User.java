package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

// ORM -> JAVA Object -> 다른 언어로 mapping 해준다

@Entity // User 클래스가 Mysql에 태이블이 생성된다.
public class User {
	
	@Id //PrimaryKey
	@GeneratedValue(strategy = GenerationType.IDENTITY)// 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //시퀀스, auto-increment
	
	@Column(nullable = false, length = 30)
	private String username; 
	
	@Column(nullable = false, length = 100) // 비밀번호를 암호화하기 때문 길이 충분하게
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role; // Enum을 쓰는게 좋음
	
	@CreationTimestamp // 시간이 자동 입력
	private Timestamp createDate;
	
}