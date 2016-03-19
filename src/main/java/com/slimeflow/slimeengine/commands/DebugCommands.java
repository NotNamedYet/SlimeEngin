package com.slimeflow.slimeengine.commands;

import com.slimeflow.slimeengine.SlimePlayer;
import com.slimeflow.slimeengine.SlimePlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by x9litch on 19/03/2016. - slimeflow.com
 */
public class DebugCommands implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if(args.length == 0)
        {

            return true;
        }
        else if (args[0].equalsIgnoreCase("slime"))
        {
            SlimePlayerManager man = SlimePlayer.Manager();

            Bukkit.getLogger().info("- Connected Slimes : " + man.collectionSize());
        }

        return false;
    }
}
