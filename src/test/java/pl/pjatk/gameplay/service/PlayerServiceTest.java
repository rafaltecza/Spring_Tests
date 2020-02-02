package pl.pjatk.gameplay.service;

import org.assertj.core.api.Assertions;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.pjatk.gameplay.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SpringBootTest
class PlayerServiceTest {

    @Autowired
    private PlayerService playerService;
    private Player attacker;
    private Player defender;

    @BeforeEach
    public void initialization() {
        this.attacker = playerService.createNewPlayer(new Player("test1", 100, 10));
        this.defender = playerService.createNewPlayer(new Player("test2", 30, 15));
    }


    @Test
    void findAllTest() {
        List<Player> list = playerService.findAll();
        Assertions.assertThat(list).isNotEmpty();
    }

    @Test
    void findPlayerByIdTest() {
        Player player = playerService.findById(attacker.getId());
        Assertions.assertThat(player.getId()).isEqualTo(attacker.getId());
    }

    @Test
    void findWrongPlayerByIdTest() {
        Player player = playerService.findById(defender.getId());
        Assertions.assertThat(player.getId()).isNotEqualTo(attacker.getId());
    }


    @Test
    void attackTest() {
        playerService.attack(attacker.getId(), defender.getId());
        Assertions.assertThat(playerService.findById(defender.getId()).getHealth()).isEqualTo(20);
    }

    @Test
    void createPlayerTest() {
        Player player = new Player("test1", 100, 10);
        Player playerObject = playerService.createNewPlayer(player);

        Assertions.assertThat(playerObject).isEqualTo(player);
    }

    @Test
    void deletePlayerTest() {
        Player player = playerService.createNewPlayer(
            new Player("test1", 100, 10)
        );

        playerService.deletePlayer(player.getId());
        Assertions.assertThatExceptionOfType(RuntimeException.class).isThrownBy(
                () -> playerService.findById(player.getId())
        );
    }
}
