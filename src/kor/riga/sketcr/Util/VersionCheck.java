package kor.riga.sketcr.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import kor.riga.sketcr.Main;

public class VersionCheck extends Thread implements Runnable {

	
	@Override
	public void run() {
		String u = "https://pastebin.com/raw/gmyCL1Eg";
		BufferedReader ur;
		try {
			ur = new BufferedReader(new InputStreamReader((new URL(u)).openStream(), "UTF-8"));
			String[] sy = ur.lines().<String>toArray(paramInt -> new String[paramInt]);
			if (!sy[0].equals(Main.getInstance().getDescription().getVersion())) {
				Variables.getInstance().check = true;
				Variables.getInstance().version = sy[0];
				System.out.println("[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 최신버전이 존재합니다 ( 문의 : _R#8668 )");
				System.out.println("[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 최신버전이 존재합니다 ( 문의 : _R#8668 )");
				System.out.println("[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 최신버전이 존재합니다 ( 문의 : _R#8668 )");
				System.out.println("[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 최신버전이 존재합니다 ( 문의 : _R#8668 )");
				System.out.println("[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 최신버전이 존재합니다 ( 문의 : _R#8668 )");
				System.out.println("[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 최신버전 다운로드 주소1 : https://www.spigotmc.org/resources/skript-addon-sketcr.83787/");
				System.out.println("[SkEtcR v"+Main.getInstance().getDescription().getVersion()+"] 최신버전 다운로드 주소2 : https://forums.skunity.com/resources/sketcr.1227/");
			} 
		} catch (UnsupportedEncodingException e) {
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return;
	}
}
