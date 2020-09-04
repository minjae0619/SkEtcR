package kor.riga.sketcr.Effect;



import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;



public class EFFNotePlayPlayer extends Effect{
	private Expression<String> ins;
	private Expression<Float> pitch;
	private Expression<Float> volume;
	private Expression<Player> player;
	
	@Override
	public String toString(Event event, boolean b)
	{
		
	  return "play noteblock %string% of %float% with volume %float% for %player%";
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.ins = (Expression<String>) expressions[0];
		this.pitch = (Expression<Float>) expressions[1];
		this.volume = (Expression<Float>) expressions[2];
		this.player = (Expression<Player>) expressions[3];
		return true;
	}
	@Override
	protected void execute(Event event) {
		Player player = this.player.getSingle(event);
		String ins = this.ins.getSingle(event).toUpperCase();
		float volume = this.volume.getSingle(event);
		float pitch = this.pitch.getSingle(event);
		Sound sound = null;
		try {
			sound = Sound.valueOf("BLOCK_NOTE_BLOCK_"+ins);
		} catch (Exception e) {
			try {
				sound = Sound.valueOf("BLOCK_NOTE_" + ins);
			} catch (Exception e2) {
				try {
					sound = Sound.valueOf("NOTE_" + ins);
				} catch (Exception e3) {
				}
			}
		}
		player.playSound(player.getLocation(), sound, volume, pitch);
	}

}

