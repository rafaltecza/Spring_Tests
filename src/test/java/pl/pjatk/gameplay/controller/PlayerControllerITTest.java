package pl.pjatk.gameplay.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.PlayerService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(MockitoExtension.class)
class PlayerControllerITTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private PlayerService playerService;

    @Autowired
    private MockMvc mockMvc;

    private static Player darkKnight = new Player(1L, "Dark Knight", 100, 20);


    @Test
    void checkStatus() throws Exception {
        List<Player> players = new ArrayList<>();
        players.add(darkKnight);

        when(playerService.findAll()).thenReturn(players);

        mockMvc.perform(get("/players"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(players))
                );
    }

    @Test
    void getPlayerById() throws Exception {
        final Long id = darkKnight.getId();

        when(playerService.findById(id)).thenReturn(darkKnight);

        mockMvc.perform(get("/players/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(darkKnight))
                );
    }

    @Test
    void createNewPlayer() throws Exception {
        when(playerService.createNewPlayer(any())).thenReturn(darkKnight);

        mockMvc.perform(post("/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"zxc\", \"health\": 100, \"attack\": 10}"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(darkKnight))
                );
    }

    @Test
    void deletePlayer() throws Exception {
        final Long id = darkKnight.getId();

        doNothing().when(playerService).deletePlayer(1L);
        mockMvc.perform(delete("/players/{id}", 1L))
                .andExpect(status().isOk());
    }

}