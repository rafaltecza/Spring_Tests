package pl.pjatk.gameplay.service;

import org.springframework.stereotype.Service;
import pl.pjatk.gameplay.model.Player;
import pl.pjatk.gameplay.repository.PlayerRepository;

import java.util.List;

@Service
public class PlayerService {

    private final DamageService damageService;
    private final PlayerRepository playerRepository;

    public PlayerService(DamageService damageService, PlayerRepository playerRepository) {
        this.damageService = damageService;
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll(){
        return playerRepository.findAll();
    }

    public Player findById(Long id){
        return playerRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Player createNewPlayer(Player player){
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id){
        playerRepository.delete(findById(id));
    }

    public Player attack(Long attackerId, Long defenderId){
        Player attacker = findById(attackerId);
        Player defender = findById(defenderId);

        defender = damageService.attack(attacker, defender);

        playerRepository.save(defender);

        return defender;
    }

    public Player comboAttack(Long attackerId, Long defenderId) {
        Player attacker = findById(attackerId);
        Player defender = findById(defenderId);

        defender = damageService.comboAttack(attacker, defender);

        playerRepository.save(defender);

        return defender;
    }

}
