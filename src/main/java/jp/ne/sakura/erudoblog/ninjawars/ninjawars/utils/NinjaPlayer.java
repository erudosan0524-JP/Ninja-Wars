package jp.ne.sakura.erudoblog.ninjawars.ninjawars.utils;

import lombok.Data;

import java.util.UUID;

@Data
public class NinjaPlayer {

    private String name; //プレイヤー名
    private UUID uuid; //UUID
    private PlayerStatus status; //プレイヤーの状態

    //プレイヤーのステータス
    public enum PlayerStatus {
        INGAME, //ゲームに参加している
        SPECTATOR, //観戦中
        NONE //何もしていない
    }
}
