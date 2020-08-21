package kor.riga.sketcr.Effect;



import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.Note.Tone;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;



public class EFFNoteBlockFlat extends Effect{
	private Expression<String> ins;
	private Expression<String> tone;
	private Expression<Player> player;
	private Expression<Location> loc;
	
	@Override
	public String toString(Event event, boolean b)
	{
		
	  return "play noteblock flat instrument %string% and tone %string% at %location% to %player%";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.ins = (Expression<String>) expressions[0];
		this.tone = (Expression<String>) expressions[1];
		this.loc = (Expression<Location>) expressions[2];
		this.player = (Expression<Player>) expressions[3];
		return true;
	}
	@Override
	protected void execute(Event event) {
		Player player = this.player.getSingle(event);
		Location loc = this.loc.getSingle(event);
		String ins = this.ins.getSingle(event);
		Tone tone = Tone.valueOf(this.tone.getSingle(event).toUpperCase());
		player.playNote(loc, Instrument.valueOf(ins.toUpperCase()), Note.flat(1, tone));
	}

}

