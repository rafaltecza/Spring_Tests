package pl.pjatk.gameplay.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.pjatk.gameplay.model.Player;

class DamageServiceTest {

    DamageService damageService = new DamageService();

    @Test
    void defenderTest() {
        //given
        Player attacker = new Player("test", 100, 10);
        Player defender = new Player("test2", 30, 15);

        //when
        damageService.attack(attacker, defender);

        //then
        Assertions.assertThat(defender.getHealth()).isEqualTo(20);
    }

    @Test
    void attackerTest() {
        //given
        Player attacker = new Player("test", 100, 10);
        Player defender = new Player("test2", 30, 15);

        //when
        damageService.attack(attacker, defender);

        //then
        Assertions.assertThat(attacker.getHealth()).isEqualTo(100);
    }

    @Test
    void defenderComboTest() {
        //given
        Player attacker = new Player("test", 100, 10);
        Player defender = new Player("test2", 100, 15);

        //when
        damageService.comboAttack(attacker, defender);

        //then
        Assertions.assertThat(defender.getHealth()).isEqualTo(80);
    }

    @Test
    void attackerComboTest() {
        //given
        Player attacker = new Player("test", 100, 10);
        Player defender = new Player("test2", 100, 15);

        //when
        damageService.comboAttack(attacker, defender);

        //then
        Assertions.assertThat(attacker.getHealth()).isEqualTo(100);
    }
}
