package io.github.Gherkin.ChunkAnchor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkUnloadEvent;

public final class ChunkAnchor extends JavaPlugin {
    List<String> chunksAnchored = new ArrayList<String>();
    Chunk [] chunkArray;
    
    @Override
    public void onEnable () {
        getLogger().info("onEnable has been invoked!");
        this.reloadConfig();
        getServer().getPluginManager().registerEvents(new chunkUnloadListener(), this);
        chunksAnchored = this.getConfig().getStringList("ChunksAnchored");
        chunkArray = new Chunk[chunksAnchored.size() + 40];
        for(int i = 0; i < chunksAnchored.size(); i ++) {
            List<String> chunkList = this.getConfig().getStringList(chunksAnchored.get(i));
            Location location = new Location(getServer().getWorld(chunkList.get(0)), Double.parseDouble(chunkList.get(1)), Double.parseDouble(chunkList.get(2)), Double.parseDouble(chunkList.get(3)));
            chunkArray[i] = location.getChunk();
        }
    }
    
    @Override
    public void onDisable () {
        getLogger().info("onDisable has been invoked!");
        this.saveConfig();
    }
    
    public void saveChunk (Location loc, String name) {
        List<String> chunkList = Arrays.asList(loc.getWorld().getName(), Double.toString(loc.getX()), Double.toString(loc.getY()), Double.toString(loc.getZ()));
        chunksAnchored.add(name);
        this.getConfig().set(name, chunkList);
        this.getConfig().set("ChunksAnchored", chunksAnchored);
        chunkArray[chunksAnchored.size()] = loc.getChunk();
        
    }

    public final class chunkUnloadListener implements Listener {
        @EventHandler(priority = EventPriority.HIGHEST)
        public void onUnload(ChunkUnloadEvent event) {
            if(Arrays.asList(chunkArray).contains(event.getChunk())) {
                event.setCancelled(true);
            }
        }
    }
}
    