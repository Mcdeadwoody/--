package me.mcdeadwoody.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.logging.Logger;

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

    // double jump
    Object jumpState = null;
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if(e.getAction() == Action.PHYSICAL) {
            if(e.getMaterial() == Material.AIR) {
                if(jumpState == null) {
                    e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getBlockX(), 0.25, e.getPlayer().getVelocity().getBlockZ()));
                    for(int i=0; i<5; i++) {
                        e.getPlayer().sendMessage("vis");
                    }
                    jumpState = 1;
                }else {
                    e.setCancelled(true);
                }

            }
        }
    }




}
