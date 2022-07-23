package Translate;

import java.util.Arrays;
import java.util.List;

import com.swjtu.lang.LANG;
import com.swjtu.querier.Querier;
import com.swjtu.trans.AbstractTranslator;
import com.swjtu.trans.impl.GoogleTranslator;

public class Translator {
	
	//�|�󌳂̌���
	public static List<String> lang = Arrays.asList("�������o", "���{��", "������", "�؍���", "�p��", "�t�����X��", "�X�y�C����", "�h�C�c��", "���V�A��", "�C�^���A��", "�n���C��", "�|���g�K����", "�C���h�l�V�A��", "�A���r�A��", "�E�N���C�i��");
	//�|�󂷂錾��
	public static List<String> do_lang = Arrays.asList("���{��", "������", "�؍���", "�p��", "�t�����X��", "�X�y�C����", "�h�C�c��", "���V�A��", "�C�^���A��", "�n���C��", "�|���g�K����", "�C���h�l�V�A��", "�A���r�A��", "�E�N���C�i��");
	
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
