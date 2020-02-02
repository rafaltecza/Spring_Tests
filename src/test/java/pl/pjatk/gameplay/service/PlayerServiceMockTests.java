package pl.pjatk.gameplay.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceMockTests {

    @Mock
    PlayerRepository playerRepository;

    @InjectMocks
    PlayerService playerService;

    @Test
    void playersEmptyTest() {
        Mockito.when(playerRepository.findAll()).thenReturn(new ArrayList<>());
        Assertions.assertThat(playerService.findAll()).isEmpty();
    }

    @Test
    void findByIdTest() {
        Player player = new Player("testowanie", 100, 10);
        player.setId(1l);
        Mockito.when(playerRepository.findById(1l)).thenReturn(Optional.of(player));
        Assertions.assertThat(playerService.findById(1L)).isEqualTo(player);
    }

    @Test
    void findByIdWrongTest() {
        Mockito.doThrow(new RuntimeException()).when(playerRepository).findById(1l);
        Assertions.assertThatExceptionOfType(RuntimeException.class).isThrownBy(
                () -> playerService.findById(1l)
        );
    }

    @Test
    void createPlayerTest() {
        Player player = new Player("testowanie", 100, 10);
        Mockito.when(playerRepository.save(player)).thenReturn(player);
        Assertions.assertThat(playerService.createNewPlayer(player)).isEqualTo(player);
    }
}
