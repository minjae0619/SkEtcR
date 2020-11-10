package kor.riga.sketcr.Util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import org.bukkit.Bukkit;

import kor.riga.sketcr.Main;

public class Update {


	@SuppressWarnings("deprecation")
	public static void start() {
		Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
			@SuppressWarnings("unused")
			@Override
			public void run() {
				try {
					Bukkit.broadcastMessage("§a[ SkEtcR ] 최신버전을 다운로드 합니다.");
					URL downLink = new URL("https://drive.google.com/uc?export=download&id=1xveud-h583TTlpXXDTKAAKzSnyNbQbGq");
					String outPut = "plugins\\SkEtcR\\Update\\SkEtcR v"+ Variables.getInstance().version +".zip";
					ReadableByteChannel rbc = Channels.newChannel(downLink.openStream());
					FileOutputStream outputStream = new FileOutputStream(outPut);
					outputStream.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
					outputStream.getChannel().size();
					outputStream.close();
					Bukkit.broadcastMessage("§a[ SkEtcR ] 최신버전 다운로드 완료.");
					Bukkit.broadcastMessage("§a[ SkEtcR ] plugins - SkEtcR - Update 폴더를 확인해주세요.");
					//reload(1);
				} catch (IOException e) {
					Bukkit.broadcastMessage("§a[ SkEtcR ] 다운로드 실패.");
					Class<?> c=null;if(c!=null)for(Object o : c.getEnumConstants()){o.notify();break;}
					return;
				}
			}
		});
	}
	
	/*
	@SuppressWarnings("unused")
	private static void reload(int a) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				int i = 10-a;
				Bukkit.broadcastMessage("[ SkEtcR ] " + i + "초 후 서버가 리로드 됩니다.");
				if(i == 0) {
					Bukkit.reload();
					return;
				}
				reload(a+1);
			}
		}, 20);
	}*/
}
