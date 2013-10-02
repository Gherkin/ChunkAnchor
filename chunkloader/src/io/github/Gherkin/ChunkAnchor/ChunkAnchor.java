package io.github.Gherkin.ChunkAnchor;

import java.util.List;

import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public final class ChunkAnchor extends JavaPlugin {
    Chunk chunkArray[] = new Chunk[1];
    

    @Override
    public void onEnable () {
        getLogger().info("onEnable has been invoked!");
        this.saveDefaultConfig();
        List<String> worlds = ChunkAnchor.this.getConfig().getStringList("worlds");
        World worldArray[] = new World[worlds.size()];
        for(int i = 0; i < worlds.size(); i++) {  
            worldArray[i] = getServer().getWorld(worlds.get(i));
        }
        for(int i = 0; i < worldArray.length; i++) {
           list<String> worldChunks  worldArray[i]
        }
    }
    @Override
    public void onDisable () {
        getLogger().info("onDisable has been invoked!");
    }
    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("chunkanchor")) {
            if(args[0] == "anchor") {
                if(args[1] == "here")
                    if(!(sender instanceof Player)) {
                        sender.sendMessage("Only players can use this command");
                        return true;
                    }
                    Player player = (Player) sender;
                    Location loc = player.getLocation();
                    
            }
        }
        return false;
    }
}
