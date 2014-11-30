package me.mcdeadwoody.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created by Tim on 30-11-2014.
 */


public class Main extends JavaPlugin implements Listener
{


    Logger logger = Bukkit.getLogger();
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        for(int i=0;i<5;i++) {
            logger.info("Test plugin <Mcdeadwoody> starting..");
        }
    }
    @Override
    public void onDisable() {
        logger.info("Test plugin <Mcdeadwoody> shutting down..");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        return true;
    }

    // Minigame ideëen:

    // double jump
    public void doubleJump(PlayerVelocityEvent event) {
        Player p = event.getPlayer();
        int doubleJumpCount = 0;



        if(p.isFlying()) {
            if(p.isSneaking()) {
                if (doubleJumpCount == 0) {

                    //TODO setvelocity for the next jump, reset upon touching the ground, possible XP bar state indication.

                    doubleJumpCount = 1;
                }else
                    if(doubleJumpCount == 1){
                    return;
                }


            }
        }
    }



}
