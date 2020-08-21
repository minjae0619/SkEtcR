package kor.riga.sketcr.Expression;

import java.io.File;
import java.util.ArrayList;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class File_List extends SimpleExpression<String> {
	
	private Expression<String> name;
    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean, SkriptParser.ParseResult parseResult){
		this.name = (Expression<String>) expressions[0];
    	return true;
    }
    @Override
    public String toString(@Nullable Event event, boolean b){
    	return "file list %string%";
    }
    @Override
    protected String[] get(Event event) {
    	File file = new File(name.getSingle(event));
    	String[] s = file.list();
    	ArrayList<String> al = new ArrayList<String>();
    	for(String t : s) {
    		al.add(name.getSingle(event) + "\\" + t);
    	}
    	String str = al.toString();
    	String str2 = str.replace("[", "");
    	String str3 = str2.replace("]", "");
    	String[] b = str3.split(", ");
    	return b;
    }
    @Override
    public boolean isSingle(){
    	return true;
    }
    
    @Override
    public Class<? extends String> getReturnType(){
    	return String.class;
    }
 
}
