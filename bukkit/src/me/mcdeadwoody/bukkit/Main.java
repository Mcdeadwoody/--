package me.mcdeadwoody.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Hashtable;
import java.util.logging.Logger;

public class Main extends JavaPlugin implements Listener {
    Logger logger = Bukkit.getLogger();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        for (int i = 0; i < 5; i++) {
            logger.info("Test plugin <Mcdeadwoody> starting..");
        }
    }

    @Override
    public void onDisable() {
        logger.info("Test plugin <Mcdeadwoody> shutting down..");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("test")) {
            Player p = (Player) sender;
            p.sendMessage("test");
        }
        return true;
    }

    // double jump
    @EventHandler
    public void onPlayerToggleFlight(PlayerToggleFlightEvent e) {
        if (e.getPlayer().getGameMode() != GameMode.CREATIVE) {
            e.getPlayer().setVelocity(e.getPlayer().getLocation().getDirection().multiply(1.5).setY(1));
            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLAZE_HIT, 1f, 1f);
            e.getPlayer().setFlying(false);
            e.getPlayer().setAllowFlight(false);
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        if (e.getPlayer().getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR && e.getPlayer().getGameMode() != GameMode.CREATIVE && !e.getPlayer().isFlying()) {
            e.getPlayer().setAllowFlight(true);
        }
    }
    // knockback logic
    Hashtable<Player, Double> Map = new Hashtable<Player,Double>();

    public void standardKnockback(){
        for(int i = 0; i<=Bukkit.getOnlinePlayers().length; i++) {
            Map.put(Bukkit.getOnlinePlayers()[i], 0.0);
        }
    }

    @EventHandler
    public void onDmg(EntityDamageByEntityEvent e) {
        Player p = (Player) e.getEntity();
        if((e.getEntity() != null) && (e.getDamager() instanceof Player) && (p.isOnline())) {
            double kbVal = Map.get(p);
            Map.put(p, kbVal + 0.1);
            p.setVelocity(p.getVelocity().add(p.getLocation().toVector().subtract(e.getDamager().getLocation().toVector()).normalize().multiply(Map.get(p))));
        }
        e.setCancelled(true);
    }
}
