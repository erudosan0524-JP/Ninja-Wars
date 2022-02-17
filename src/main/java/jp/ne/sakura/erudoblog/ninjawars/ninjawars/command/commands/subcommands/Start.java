package jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.commands.subcommands;

import jp.ne.sakura.erudoblog.ninjawars.ninjawars.NinjaWars;
import jp.ne.sakura.erudoblog.ninjawars.ninjawars.command.commands.SubCommand;
import org.bukkit.entity.Player;

public class Start extends SubCommand {

    public Start(NinjaWars plugin) {
        super(plugin);
    }

    @Override
    public void onCommand(Player player, String[] args) {
        if(args.length <= 2) {
            //args[0] => カウントダウン時間
            //args[1] => ゲーム時間
            int countdownTime = 0;
            int gameTime = 0;

            try {
                countdownTime = Integer.parseInt(args[0]);
                gameTime = Integer.parseInt(args[1]);
            }catch(NumberFormatException e) {
                player.sendMessage("引数には整数値を入力してください");
            }catch(ArrayIndexOutOfBoundsException e) {}

            getPlugin().gameStart(countdownTime,gameTime);
        }

    }

    @Override
    public String name() {
        return "start";
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
