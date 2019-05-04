package com.capgemini.gamecapmates;

import com.capgemini.gamecapmates.Exceptions.NoSuchUserException;
import com.capgemini.gamecapmates.domain.Game;
import com.capgemini.gamecapmates.domain.GameBoard;
import com.capgemini.gamecapmates.mapper.UserMapper;
import com.capgemini.gamecapmates.repository.GameBoardRepository;
import com.capgemini.gamecapmates.repository.GameRepository;
import com.capgemini.gamecapmates.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = GameCapMatesBoardApplication.class)
public class GameCapMatesBoardApplicationTests {

	@Autowired
	GameRepository gameRepository;
	@Autowired
	UserRepository userRepository;

	@Test
	public void checkingCalculateAge() {
		UserMapper userMapper = new UserMapper();

		int age = userMapper.calculateAge(LocalDate.of(1995, 7, 10));

		Assert.assertEquals(23, age);

	}

	@Test
	public void GameBoardrepotest() {
		try {
			GameBoardRepository gameBoardRepository = new GameBoardRepository(gameRepository,userRepository);
			List<Game> userId=gameBoardRepository.findById(1L);
			System.out.println(userId);
		} catch (NoSuchUserException e) {
			e.printStackTrace();
		}
	}


}
