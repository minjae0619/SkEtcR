package kor.riga.sketcr.Condition;


import javax.annotation.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
 
public class CommandAynchronous extends Condition {
 
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, ParseResult parser) {
        setNegated(parser.mark == 1);
        return true;
    }
 
    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "error";
    }
 
    @Override
    public boolean check(Event event) {
    	if(!(event instanceof PlayerCommandPreprocessEvent))
    		return false;
    	String[] str = ((PlayerCommandPreprocessEvent)event).getMessage().replaceAll("/", "").split(" ");
    	Command cmd = Bukkit.getServer().getPluginCommand(str[0]);
    	//HelpTopic cmd = Bukkit.getHelpMap().getHelpTopic(str[0]);
    	return (cmd != null) ? isNegated() : (!isNegated());
    }
 
}