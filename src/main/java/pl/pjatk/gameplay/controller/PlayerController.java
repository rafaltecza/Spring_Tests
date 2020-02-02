package pl.pjatk.gameplay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll(){
        return ResponseEntity.ok(playerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findById(@PathVariable Long id){
        return ResponseEntity.ok(playerService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Player> createNewPlayer(@RequestBody Player player){
        return ResponseEntity.ok(playerService.createNewPlayer(player));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id){
        playerService.deletePlayer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/attacker/{attackerId}/defender/{defenderId}")
    public ResponseEntity<Player> attackPlayer(@PathVariable Long attackerId, @PathVariable Long defenderId){
        return ResponseEntity.ok(playerService.attack(attackerId, defenderId));
    }

    @GetMapping("combo/attacker/{attackerId}/defender/{defenderId}")
    public ResponseEntity<Player> comboAttackPlayer(@PathVariable Long attackerId, @PathVariable Long defenderId){
        return ResponseEntity.ok(playerService.comboAttack(attackerId, defenderId));
    }

}
