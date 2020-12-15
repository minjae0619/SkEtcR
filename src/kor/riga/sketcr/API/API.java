package kor.riga.sketcr.API;

import kor.riga.sketcr.API.MagicSpell.MagicSpell;
import kor.riga.sketcr.API.MythicMobs.MythicMobs;
import kor.riga.sketcr.API.ProtocolLib.ProtocolLib;

public class API {

	
	public static void registerAPIs() {
		MagicSpell.register();
		ProtocolLib.register();
		MythicMobs.register();
	}
}
