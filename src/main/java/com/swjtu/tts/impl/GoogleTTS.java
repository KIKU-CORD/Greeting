package com.swjtu.tts.impl;

import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.swjtu.lang.LANG;
import com.swjtu.tts.AbstractTTS;

public final class GoogleTTS extends AbstractTTS{
    private final static String url = "https://translate.google.cn/translate_tts";

    public GoogleTTS() {
        super(url);
    }

    @Override
    public void setFormData(LANG source, String text) {
        formData.put("ie", "UTF-8");
        formData.put("q", text);
        formData.put("tl", source.getCode());
        formData.put("total", String.valueOf(1));
        formData.put("idx", String.valueOf(0));
        formData.put("textlen", String.valueOf(11));
        formData.put("tk", token(text));
        formData.put("client", "t");
    }

    private String token(String text) {
        String tk = "";
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
        try {
            FileReader reader = new FileReader("./tk/Google.js");
            engine.eval(reader);

            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable)engine;
                tk = String.valueOf(invoke.invokeFunction("token", text));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
}
