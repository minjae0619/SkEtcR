package kor.riga.sketcr.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import kor.riga.sketcr.Main;
import kor.riga.sketcr.Util.Update;
import kor.riga.sketcr.Util.Variables;
import kor.riga.sketcr.Util.VersionCheck;

public class MainCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(!cmd.getName().equalsIgnoreCase("sketcr")) return false;
		/*if(args.length == 0) {
			sender.sendMessage("§a[ SkEtcR ] /sketcr update");
			return false;
		}
		if(args[0].equalsIgnoreCase("update")) {
			new VersionCheck().start();
			if(Variables.getInstance().check && !Main.getInstance().getConfig().getBoolean("Auto Update"))
				Update.start();
			else sender.sendMessage("§a[ SkEtcR ] 현재 최신 버전입니다.");
		}*/
		return false;
	}
	
}
