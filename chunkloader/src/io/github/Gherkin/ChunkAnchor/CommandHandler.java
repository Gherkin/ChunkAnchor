package io.github.Gherkin.ChunkAnchor;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class CommandHandler implements CommandExecutor {
    
    private ChunkAnchor plugin;
     
    public CommandHandler(ChunkAnchor plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("chunkanchor")) {
            if(args.length == 0) {
                sender.sendMessage("you need to pass more arguments");
                return false;
            }
            if(args[0].equalsIgnoreCase("anchor")) {
                if(args.length < 2) {
                    sender.sendMessage("you need to pass more arguments");
                    return false;
                }
                if(args[1].equalsIgnoreCase("here")) {
                    if(!(sender instanceof Player)) {
                        sender.sendMessage("Only players can use this command");
                        return true;
                    }
                    if(args.length < 3) {
                        sender.sendMessage("You need to name the chunk");
                        return false;
                    }
                    Player player = (Player) sender;
                    plugin.saveChunk(player.getLocation(), args[2]);
                    sender.sendMessage("Sucessfully added chunk " + args[2]);                    
                    return true;
                }
                if(args[1].equalsIgnoreCase("location")) {
                    plugin.saveChunk(new Location(plugin.getServer().getWorld(args[2]), Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5])), args[6]);
                    sender.sendMessage("Sucessfully added chunk " + args[6]);                    
                    return true;
                }                
            }
            if(args[0].equalsIgnoreCase("list")) {
                StringBuilder stringBuilder = new StringBuilder();
                for(int i = 0; i < plugin.chunksAnchored.size(); i++)
                    stringBuilder.append(plugin.chunksAnchored.get(i));
                sender.sendMessage(stringBuilder.toString());
                return true;
            }
            if(args[0].equalsIgnoreCase("info")) {
                if(args.length < 2) {
                    sender.sendMessage("You need to specify a chunk");
                    return false;
                }
                if(!(plugin.chunksAnchored.contains(args[1]))) {
                    sender.sendMessage("The specified chunk isn't saved");
                    return true;
                }
                sender.sendMessage("World: " + plugin.chunkArray[plugin.chunksAnchored.indexOf(args[1])].getWorld() +" x: " + plugin.chunkArray[plugin.chunksAnchored.indexOf(args[1])].getX() + " y: " + plugin.chunkArray[plugin.chunksAnchored.indexOf(args[1])].getZ());
            }
            if(args[0].equalsIgnoreCase("delete")) {
                if(args.length < 2) {
                    sender.sendMessage("you need to specify a chunk");
                    return false;
                }
                if(!(plugin.chunksAnchored.contains(args[1]))) {
                    sender.sendMessage("The specified chunk isn't saved");
                    return true;                    
                }
                Chunk[] chunkBuffer = new Chunk[plugin.chunkArray.length];
                for(int i = 0; i < plugin.chunkArray.length; i++) {
                    if(!(i == plugin.chunksAnchored.indexOf(args[1])))
                        chunkBuffer[i] = plugin.chunkArray[i];
                }
                plugin.chunkArray = chunkBuffer;
                plugin.chunksAnchored.remove(args[1]);
            }
            if(args[0].equalsIgnoreCase("load")) {
                if(args.length < 2) {
                    sender.sendMessage("You need to specify a chunk");
                    return false;
                }
                sender.sendMessage(String.valueOf(plugin.chunkArray[plugin.chunksAnchored.indexOf(args[1])].load()));
                return true;
            }
            if(args[0].equalsIgnoreCase("check")) {
                if(args.length < 2) {
                    sender.sendMessage("You need to specify a chunk");
                    return false;
                }
                sender.sendMessage(String.valueOf(plugin.chunkArray[plugin.chunksAnchored.indexOf(args[1])].isLoaded()));
                return true;
            }
            
                
                    
        }
        return false;
    }

}
