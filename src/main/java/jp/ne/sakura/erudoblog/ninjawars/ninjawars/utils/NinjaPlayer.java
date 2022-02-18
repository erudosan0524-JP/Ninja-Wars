package jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils;

import lombok.Data;
import org.bukkit.entity.Player;

import java.util.UUID;

@Data
public class NinjaPlayer {

    private static final int defaultHP = 100;

    private Player player; //プレイヤー
    private boolean isJumping; //壁ジャンプ
    private boolean isClimbing; //壁よじ登り
    private int hp = 0;
    private PlayerStatus status;


    public NinjaPlayer(Player player, int hp, PlayerStatus status) {
        this.player = player;
        this.hp = hp;
        this.status = status;
        this.isJumping = false;
        this.isClimbing = false;
    }

    public NinjaPlayer(Player player, PlayerStatus status) {
        this(player,defaultHP, status);
    }

    public NinjaPlayer(Player player) {
        this(player,defaultHP,PlayerStatus.NONE);
    }

    //HPを1減らす関数
    public void decHP() {
        this.hp -= 1;
    }


}
