package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired // 의존성 주입 DI
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제 실패했습니다. 해당 아이디는 데이터베이스에 없습니다.";
		}
		
		return id+ "번id가 삭제되었습니다" ;
	}
	
	@Transactional // 함수 종료시에 자동 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser ) {
		//@RequestBody json data를 요청 => JavaObjectMessageConverter의 Jackson library가 변경해준다
		System.out.println("id: "+id);
		System.out.println("password: "+requestUser.getPassword());
		System.out.println("email: "+requestUser.getEmail());
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패했습니다");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail()); // 변경
		
		// userRepository.save(user); 
		// save함수는 id를 전달하지 않으면 insert를 해주고
		// save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고 
		// sava함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다.
		
		// Transactional 더티 체킹
		return user;
	}
	// http://localhost:8000/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한페이지당 2건의 데이터를 리턴받음
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
//		int page = 0, size = 2;
//		PageRequest pageRequest = PageRequest.of(page,size);
//		Page<User>pagingUsers = userRepository.findAll(pageRequest);
		Page<User> pagingUsers =  userRepository.findAll(pageable);
		List<User> users = pagingUsers.getContent();
		return users;
	}
	
	// {id} 주소로 파라미터를 전달받을 수 있음
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다.id: "+ id);
			}
		}); // 반환 타입 Optional
		
		// user 객체 자바 object
		return user;
	}
	
	// http://localhost:8000/blog/dummy/join (요청)
	// http의 바디에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public User join(User user) { 
		//RequestParam 생략가능
		// key = value
		System.out.println("username: "+user.getUsername());
		System.out.println("password: "+user.getPassword());
		System.out.println("email: "+user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return user;
		
	}
}
