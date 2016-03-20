package com.slimeflow.slimeengin.commands;

import com.slimeflow.slimeengin.SlimeEngin;
import com.slimeflow.slimeengin.SlimePlayer;
import com.slimeflow.slimeengin.SlimePlayerManager;
import com.slimeflow.slimeengin.deadpool.DeadPool;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.UUID;

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
        else if (args[0].equalsIgnoreCase("slime")) //Get SlimeManager infos
        {
            SlimePlayerManager man = SlimePlayer.Manager();
            Bukkit.getLogger().info("- Registered Slimes : " + man.collectionSize());
            return true;
        }
        else if (args[0].equalsIgnoreCase("list"))
        {
            Collection<SlimePlayer> col = SlimePlayer.Manager().getSlimePlayers();
            composeList(col, sender);
            return true;
        }
        else if (args[0].equalsIgnoreCase("dp"))
        {
            sender.sendMessage("==DeadPools==");

            for(DeadPool d : SlimeEngin.deadPools().getTopTen())
            {
                sender.sendMessage(String.format("- %s : %f", d.m_actor, d.m_poolAmount));
            }

            return true;
        }
        else if (args[0].equalsIgnoreCase("dpadd"))
        {
            if (sender instanceof Player)
            {
                SlimePlayer sPlayer = SlimePlayer.Manager().getSlime(((Player)sender).getUniqueId());

                if (sPlayer != null)
                {
                    sPlayer.getDeadPool().add(25d);
                }
            }
            return true;
        }
        else if (args[0].equalsIgnoreCase("dpfill"))
        {
            for (int i = 0; i < 25; i++)
            {
                DeadPool d = new DeadPool();
                d.m_id = UUID.randomUUID();
                d.m_actor = "Bot" + i;
                d.m_poolAmount = Math.random();
                SlimeEngin.deadPools().add(d);
            }
        }

        return false;
    }

    private void composeList(Collection<SlimePlayer> buffer, CommandSender sender)
    {

        int size = 0;
        int index = 0;

        sender.sendMessage(SlimePlayer.totalOnline() + " Slimes connected.");

        StringBuilder stb = new StringBuilder();

        for (SlimePlayer p : buffer)
        {
            if (p.isOnline())
            {
                stb.append(p.getOrigin().getName());

                size++;
                if (size < buffer.size()) stb.append(", ");

                index++;

                if (index >= 10)
                {
                    sender.sendMessage(stb.toString());
                    stb = new StringBuilder();
                    index = 0;
                }
            }
        }

        String result = stb.toString();

        if (result.length() > 0)
        {
            sender.sendMessage(result);
        }

    }
}
