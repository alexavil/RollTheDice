package org.rollthedice;
 
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
 
public class rtd extends JavaPlugin {
	public static rtd plugin;
	FileConfiguration config = getConfig();
	
 
    @Override
    public void onEnable() {
    	plugin = this;
    	config.addDefault("Speed Time", "30");
        config.addDefault("Invisibility Time", "15");
    	config.addDefault("Strength Time", "30");
        config.addDefault("Blind Time", "30");
        config.addDefault("Glowing Time", "20");
    	config.addDefault("Dizzy Time", "30");
        config.addDefault("Snail Time", "15");
        config.addDefault("Fast Hands Time", "30");
        config.addDefault("Weakness Time", "30");
        config.options().copyDefaults(true);
        saveConfig();
        @SuppressWarnings("unused")
		Metrics metrics = new Metrics(this);
        new UpdateChecker(this).checkForUpdate();
       
    }
   
    @Override
    public void onDisable() {
       
    }
   
    ArrayList<Player> cooldown = new ArrayList<Player>();
    
    @Override
    public boolean onCommand(CommandSender sender,
            Command command,
            String label,
            String[] args) {
        if (command.getName().equalsIgnoreCase("rtdinfo")) {
            sender.sendMessage("[RTD] This server is running Roll The Dice v0.3");
            return true;
        }
        if (command.getName().equalsIgnoreCase("rtd")) {
        	if (!(sender instanceof Player)) {
                sender.sendMessage("You rolled... wait, you are not a player!");
                return true;
            }
        	Player player = Bukkit.getPlayer(sender.getName());
        	if (cooldown.contains(player)) {
                player.sendMessage("[RTD] You cannot use RTD at this time.");
                return true;
        }
        	if (!(player.hasPermission("rtd.use"))) {
        		player.sendMessage("[RTD] You cannot use RTD at this time.");
                return true;
        	}
            sender.sendMessage("[RTD] Rolling the dice...");
            Random fortune = new Random();
            int fate = (fortune.nextInt(15)+1);
            Random healthboost = new Random();
            int boost = (healthboost.nextInt(5)+1);
            Random exp = new Random();
            int xp = (exp.nextInt(10)+1);
            switch (fate) {
            case 1: 
            	sender.sendMessage("[RTD] You rolled: speed for " + config.getString("Speed Time") + " seconds!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:speed" + " " + config.getString("Speed Time") + " 2");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Speed!");
            	cooldown.add(player);
            	break;
            case 2:
            	sender.sendMessage("[RTD] You rolled: invisibility for " + config.getString("Invisibility Time") + " seconds!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:invisibility"  + " " + config.getString("Invisibility Time") + " 2");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Invisibility!");
            	cooldown.add(player);
                break;
            case 3:
            	sender.sendMessage("[RTD] You rolled: Lucky Melon!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:health_boost" + " 300 " + boost);
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:instant_health" + " 15" + " 99");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Lucky Melon!");
            	cooldown.add(player);
            	break;
            case 4:
            	sender.sendMessage("[RTD] You rolled: instant death!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "kill " + player.getName());
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Instant Death!");
            	cooldown.add(player);
            	break;
            case 5:
            	sender.sendMessage("[RTD] You rolled: strength for " + config.getString("Strength Time") + " seconds!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:strength" + " " + config.getString("Strength Time") + " 3");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Strength!");
            	cooldown.add(player);
            	break;
            case 6:
            	sender.sendMessage("[RTD] You rolled: experience boost!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "experience " + "add " + player.getName() + " " + xp + " levels");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Experience Boost!");
            	cooldown.add(player);
            	break;
            case 7:
            	sender.sendMessage("[RTD] You rolled: blind for " + config.getString("Blind Time") + " seconds!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:blindness" + " " + config.getString("Blind Time") + " 2");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Blind!");
            	cooldown.add(player);
            	break;
            case 8:
            	sender.sendMessage("[RTD] You rolled: EVERYONE: glow for " + config.getString("Glowing Time") + " seconds!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + "@a" + " minecraft:glowing" + " " + config.getString("Glowing Time") + " 2");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Glow for everyone!");
            	cooldown.add(player);
            	break;
            case 9:
            	sender.sendMessage("[RTD] You rolled: dizzy for " + config.getString("Dizzy Time") + " seconds!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:nausea" + " " + config.getString("Dizzy Time") + " 2");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Dizzy!");
            	cooldown.add(player);
            	break;
            case 10:
            	sender.sendMessage("[RTD] You rolled: snail for " + config.getString("Snail Time") + " seconds!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:slowness" + " " + config.getString("Snail Time") + " 5");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Snail!");
            	cooldown.add(player);
            	break;
            case 11:
            	sender.sendMessage("[RTD] Looks like you rolled nothing. Try again.");
            	break;
            case 12:
            	sender.sendMessage("[RTD] You rolled: fast hands for " + config.getString("Fast Hands Time") + " seconds!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:haste" + " " + config.getString("Fast Hands Time") + " 2");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Fast Hands!");
            	cooldown.add(player);
            	break;
            case 13:
            	sender.sendMessage("[RTD] You rolled: free punch!");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:instant_damage" + " 1" + " 1");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Free Punch!");
            	cooldown.add(player);
            	break;
            case 14:
            	sender.sendMessage("[RTD] You rolled: spontaneous combustion!");
            	player.setFireTicks(140);
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Spontaneous Combustion!");
            	cooldown.add(player);
            	break;
            case 15:
            	sender.sendMessage("[RTD] You rolled: weakness for " + config.getString("Weakness Time") + " seconds!" );
            	getServer().dispatchCommand(getServer().getConsoleSender(), "effect give " + player.getName() + " minecraft:weakness" + " " + config.getString("Weakness Time") + " 1");
            	getServer().dispatchCommand(getServer().getConsoleSender(), "say " + player.getName() + " rolled Weakness!");
            	cooldown.add(player);
            	break;        
}
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
                public void run() {
                        cooldown.remove(player);                        
                }
        }, 600L);
        return true;
        }
		return true;
    }
}