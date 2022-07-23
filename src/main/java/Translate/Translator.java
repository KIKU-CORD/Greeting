package Translate;

import java.util.Arrays;
import java.util.List;

import com.swjtu.lang.LANG;
import com.swjtu.querier.Querier;
import com.swjtu.trans.AbstractTranslator;
import com.swjtu.trans.impl.GoogleTranslator;

public class Translator {
	
	//翻訳元の言語
	public static List<String> lang = Arrays.asList("自動検出", "日本語", "中国語", "韓国語", "英語", "フランス語", "スペイン語", "ドイツ語", "ロシア語", "イタリア語", "ハワイ語", "ポルトガル語", "インドネシア語", "アラビア語", "ウクライナ語");
	//翻訳する言語
	public static List<String> do_lang = Arrays.asList("日本語", "中国語", "韓国語", "英語", "フランス語", "スペイン語", "ドイツ語", "ロシア語", "イタリア語", "ハワイ語", "ポルトガル語", "インドネシア語", "アラビア語", "ウクライナ語");
	
    public static String trans(String text, LANG from, LANG to) {
    	
    	String trans = "";
    	
        Querier<AbstractTranslator> querierTrans = new Querier<>();

        querierTrans.setParams(from, to, text);
        
        querierTrans.attach(new GoogleTranslator());

        List<String> result = querierTrans.execute();

        for (String str : result) {
            trans += str;
        }
        
        trans = trans.substring(1, trans.length() - 1);
        
        return trans;
    }
}
