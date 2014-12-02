package me.mcdeadwoody.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
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
        if(label.equalsIgnoreCase("test")) {
            Player p = (Player) sender;
            p.sendMessage("test");
        }
        return true;
    }

    // double jump
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
        if(e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            e.getPlayer().setVelocity(new Vector(e.getPlayer().getVelocity().getBlockX(), 1, e.getPlayer().getVelocity().getBlockZ()));
            e.setCancelled(true);
        }
    }


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
            if (e.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR && e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                e.getPlayer().setAllowFlight(true);
            }
    }





}
