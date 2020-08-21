package kor.riga.sketcr.Event;

import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerMoveEvent;

import ch.njol.skript.lang.Literal;
import ch.njol.skript.lang.SkriptEvent;
import ch.njol.skript.lang.SkriptParser.ParseResult;

public class EvtPlayerMove extends SkriptEvent {
	
	//player move [of] [%string%][,] [%string%][,] [%string%]
	Literal<String> x;
	Literal<String> y;
	Literal<String> z;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Literal<?>[] args, int matchedPattern, ParseResult parseResult) {
		x = (Literal<String>) args[0];
		y = (Literal<String>) args[1];
		z = (Literal<String>) args[2];
		return true;
	}

	@Override
	public boolean check(Event e) {
		PlayerMoveEvent event = ((PlayerMoveEvent) e);
		double x = event.getFrom().getX();
		double z = event.getFrom().getZ();
		double y = event.getFrom().getY();
		double newX = event.getTo().getX();
		double newZ = event.getTo().getZ();
		double newY = event.getTo().getY();
		String cx = null;
		String cy = null;
		String cz = null;
		if(this.x != null)
			cx = this.x.getSingle(e).toLowerCase();
		if(this.y != null)
			cy = this.y.getSingle(e).toLowerCase();
		if(this.z != null)
			cz = this.z.getSingle(e).toLowerCase();
		if(cx == null && cy == null && cz == null) {
			if (x != newX)
				return true;
			if (y != newY)
				return true;
			if (z != newZ)
				return true;
		}
		try {
		if(cx.equals("x") || cy.equals("x") || cz.equals("x"))
			if (x != newX)
				return true;
		}catch (Exception ev) {
		}
		try {
		if(cx.equals("z") || cy.equals("z") || cz.equals("z"))
			if (z != newZ)
				return true;
		}catch (Exception ev) {
		}
		try {
		if(cx.equals("y") || cy.equals("y") || cz.equals("y"))
			if (y != newY)
				return true;
		}catch (Exception ev) {
		}
		return false;
	}

	@Override
	public String toString(Event e, boolean debug) {
		return "";
	}
}
