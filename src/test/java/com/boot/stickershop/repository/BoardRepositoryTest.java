package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Board;
import com.boot.stickershop.domain.BoardCategory;
import com.boot.stickershop.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    BoardRepository boardRepository; // test할 대상을 선언

    @Autowired
    BoardCategoryRepository boardCategoryRepository;
    @Autowired
    UserRepository userRepository;

    BoardCategory boardCategory;
    User user;

    @Before
    public void initTest() throws Exception{
        boardCategory = boardCategoryRepository.getOne(1L);
        user = userRepository.getOne(1L);

        // 3건의 Board 엔티티를 저장한다.
        addBoard("hello spring boot content", "hello spring");
        addBoard("hello java content", "hello java");
        addBoard("hello jpa content", "hello jpa");
    }

    private void addBoard(String content, String title){
        Board board = new Board();
        board.setBoardCategory(boardCategory);
        board.setContent(content);
        board.setEdittime(LocalDateTime.now());
        board.setHit(0);
        board.setTitle(title);
        board.setUser(user);

        boardRepository.save(board);
    }

    // 만약 @Test 라고 붙은 메소드가 3개라면 @Before가 붙은 메소드는 몇번 실행될까요?
    // @Before -> @1Test -> @After, @Before -> @1Test .....

    @Test
    public void testAddBoard() throws Exception{
        Board board = new Board();
        board.setBoardCategory(boardCategory);
        board.setContent("hello spring boot");
        board.setEdittime(LocalDateTime.now());
        board.setHit(0);
        board.setTitle("hello");
        board.setUser(user);

        Board saveBoard = boardRepository.save(board);
        System.out.println(saveBoard.getId()); // Assert를 이용한 검증문이 들어가야한다. 눈으로 확인을 위해..
    }

    @Test
    public void testGetBoardId1() throws Exception{
        Board board = boardRepository.getOne(1L);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    @Test
    public void testGetBoardId2() throws Exception{
        Board board = boardRepository.findBoardById(1L);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    // 제목에 llo가 포함된 목록을 구하는 메소드를 boardRepository에 추가하시오.
    @Test
    public void testFindBoardByTitleContaining() throws Exception{
        List<Board> list = boardRepository.findBoardByTitleContaining("llo");
        for(Board b : list){
            System.out.println(b.getTitle());
        }
    }

    // @Query 를 이용해서 id 가 일치하는 Board를 반환하는 메소드를 BoardRepository에 추가하시오.

    @Test
    public void testGetBoardId3() throws Exception{
        Board board = boardRepository.getBoard(1L);
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }
}
