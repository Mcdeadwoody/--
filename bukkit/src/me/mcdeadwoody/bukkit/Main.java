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
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        if (e.getPlayer().getLocation().subtract(0, 1, 0).getBlock().getType() != Material.AIR && e.getPlayer().getGameMode() != GameMode.CREATIVE && !e.getPlayer().isFlying()) {
            e.getPlayer().setAllowFlight(true);
        }
    }

    //Knockback physics

    // Nummer van knockback; Player als key?

    // Een andere hashmap voor een int array die de nummers van de array pakt?

    // Nieuwe hashmap voor de knockback waarde, de key is de player.
    // Als beide entities players zijn dan telt hij één op bij de waarde van knockback bij diegene die geslagen is
    //
    //


    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        Player p = (Player) e.getEntity();
        HashMap<Player, Object > playerKb = new HashMap<Player, Object>();
        Player[] players = Bukkit.getServer().getOnlinePlayers();
        List<Integer> x = new ArrayList<Integer>();
        for(int i = 0; i<= players.length; i++) {
            x.add(1);
        }
        if(e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            for(int i = 0; i<= players.length; i++) {
                Object[] z = x.toArray();
                playerKb.put(players[i], z[i]);
            }
            int val = (Integer) playerKb.get(e.getEntity());
            playerKb.put(p, val+1);


        }
    }
}
