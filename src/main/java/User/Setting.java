package User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Setting {
	
	private Map<String, String> setting;
	
	private File file;
		
	public Setting() {
		setting = new HashMap<>();
		file = new File("Setting.txt");
		if(!file.exists()) {
			try { file.createNewFile();
			} catch (IOException e) {}
			firstWrite();
		} else {
			load();
		}
	}
	
	/*
	 * 設定ファイルの読み込み
	 */
	
	void load() {
		addValue("chat_trans", readConfig("chat_trans"));
		addValue("msg_trans", readConfig("msg_trans"));
		addValue("your_mension", readConfig("your_mension"));
		addValue("all_mension", readConfig("all_mension"));
		addValue("html_mode", readConfig("html_mode"));
		addValue("c_trans_lang", readConfig("c_trans_lang"));
		addValue("c_trans_do_lang", readConfig("c_trans_do_lang"));
		addValue("m_trans_lang", readConfig("m_trans_lang"));
		addValue("m_trans_do_lang", readConfig("m_trans_do_lang"));
	}
	
	/*
	 * 初期設定に更新
	 */
		
	void firstWrite() {
		try {
		FileWriter writer = new FileWriter(file, false);
		writer.write("Greeting Platform Settings\n\n");
		writer.write("chat_trans:false\n");
		addValue("chat_trans", String.valueOf(false));
		writer.write("msg_trans:false\n");
		addValue("msg_trans", String.valueOf(false));
		writer.write("your_mension:false\n");
		addValue("your_mension", String.valueOf(false));
		writer.write("all_mension:false\n");
		addValue("all_mension", String.valueOf(false));
		writer.write("html_mode:false\n");
		addValue("html_mode", String.valueOf(false));
		writer.write("c_trans_lang:0\n");
		addValue("c_trans_lang", "0");
		writer.write("c_trans_do_lang:0\n");
		addValue("c_trans_do_lang", "0");
		writer.write("m_trans_lang:0\n");
		addValue("m_trans_lang", "0");
		writer.write("m_trans_do_lang:0");
		addValue("m_trans_do_lang", "0");
		writer.close();
		writer = null;
		} catch (IOException e) {}
	}
	
	/*
	 * 設定の書き換え
	 */
		
	void writeConfig(String name, String value) {
		try {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		String result = "";
		while((line = reader.readLine()) != null) {
			if(line.startsWith(name)) {
				result += line.replace(line.split(":")[1], value) + "\n";
		}else{result += line + "\n";}}
		reader.close();
		FileWriter writer = new FileWriter(file, false);
		writer.write(result);
		writer.close();
		} catch (IOException e) {}
	}
	
	/*
	 * 設定の読み取り
	 */
	
	String readConfig(String name) {
		try {
		@SuppressWarnings("resource")
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line;
		while((line = reader.readLine()) != null) {
			if(line.startsWith(name)) {
				reader.close();
				return line.split(":")[1];}}
		} catch (IOException e) {}
			return null;
	}
	
	void addValue(String name, String toggle) {
		this.setting.put(name, toggle);
	}
	
	public void setValue(String name, String toggle) {
		this.setting.replace(name, toggle);
		this.writeConfig(name, String.valueOf(toggle));
	}
	
	public String getValue(String name) {
		return this.setting.get(name);
	}
}