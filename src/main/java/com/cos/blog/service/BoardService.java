package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;
import com.sun.nio.sctp.IllegalReceiveException;

@Service // 스프링이 component scan을 통해서 bean에 등록해준다.
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;

	@Transactional
	public void 글쓰기(Board board, User user) {

		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);

	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalReceiveException("글 상세보기 실패: 아이디를 찾을 수 없습니다.");
		});

	}

	@Transactional
	public void 글삭제하기(int id, PrincipalDetail principal) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			System.out.println("글 찾기 실패 : 해당 글이 존재하지 않습니다.");
			return new IllegalArgumentException("글 찾기 실패 : 해당 글이 존재하지 않습니다.");
		});

		if (board.getUser().getId() != principal.getUser().getId()) {
			System.out.println("글 삭제 실패 : 해당 글을 삭제할 권한이 없습니다.");
			throw new IllegalStateException("글 삭제 실패 : 해당 글을 삭제할 권한이 없습니다.");
		}
		boardRepository.delete(board);
	}

	@Transactional
	public void 글수정하기(int id, Board requestBoard, PrincipalDetail principal) {
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			System.out.println("글 찾기 실패 : 해당 글이 존재하지 않습니다.");
			return new IllegalArgumentException("글 찾기 실패 : 해당 글이 존재하지 않습니다.");
		}); // 영속화

		if (board.getUser().getId() != principal.getUser().getId()) {
			System.out.println("글 삭제 실패 : 해당 글을 수정할 권한이 없습니다.");
			throw new IllegalStateException("글 삭제 실패 : 해당 글을 수정할 권한이 없습니다.");
		} else {
			board.setTitle(requestBoard.getTitle());
			board.setContent(requestBoard.getContent());
		}

		// 해당 함수 종료시 service가 종료 될 때 transaction이 종료 이 때 dirty checking이 일어나면서 자동
		// update/flush 된다.

	}
	
	
}
