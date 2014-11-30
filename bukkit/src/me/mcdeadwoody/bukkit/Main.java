package me.mcdeadwoody.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

/**
 * Created by Tim on 30-11-2014.
 */


public class Main extends JavaPlugin
{


    Logger logger = Bukkit.getLogger();
    @Override
    public void onEnable()
    {
        for(int i=0;i<5;i++)
        {
            logger.info("Test plugin <Mcdeadwoody> starting..");
        }
    }
    @Override
    public void onDisable()
    {
        logger.info("Test plugin <Mcdeadwoody> shutting down..");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(label.equalsIgnoreCase("fly"))
        {
            Player p = (Player) sender;
            if(p.isFlying())
            {
                p.setFlying(true);
            }else
            {
                p.setFlying(false);
            }
        }
        return true;
    }







}