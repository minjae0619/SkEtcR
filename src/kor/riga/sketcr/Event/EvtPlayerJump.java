package kor.riga.sketcr.Event;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

public class EvtPlayerJump extends SkriptEvent {

	// player move [of] [%string%][,] [%string%][,] [%string%]

	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		return true;
	}

	@Override
	public boolean check(Event e) {
		PlayerMoveEvent event = ((PlayerMoveEvent) e);
		double y = event.getFrom().getY();
		double newY = event.getTo().getY();
		String str = y + " ";
		char[] arr = str.toCharArray();
		char ch = 'n';
		int i = 0;
		for (char c : arr) {
			if (ch == '.' && c == '0' && newY - y != 0 && arr[i + 1] == ' ') {
				return true;
			}
			if (c == '.')
				ch = c;
			else
				ch = 'n';
			i++;

		}
		// if(event.getPlayer().isOnGround() && newY-y != 0)
		return false;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "";
	}

}
