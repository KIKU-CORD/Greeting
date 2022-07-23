package com.swjtu.trans.impl;

import java.io.FileReader;
import java.io.IOException;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.swjtu.lang.LANG;
import com.swjtu.trans.AbstractTranslator;

public final class GoogleTranslator extends AbstractTranslator {
    private static final String url = "https://translate.google.cn/translate_a/single";

    public GoogleTranslator(){
        super(url);
    }

    @Override
    public void setFormData(LANG from, LANG to, String text) {
        formData.put("client", "t");
        formData.put("sl", from.getCode());
        formData.put("tl", to.getCode());
        formData.put("hl", "zh-CN");
        formData.put("dt", "at");
        formData.put("dt", "bd");
        formData.put("dt", "ex");
        formData.put("dt", "ld");
        formData.put("dt", "md");
        formData.put("dt", "qca");
        formData.put("dt", "rw");
        formData.put("dt", "rm");
        formData.put("dt", "ss");
        formData.put("dt", "t");
        formData.put("ie", "UTF-8");
        formData.put("oe", "UTF-8");
        formData.put("source", "btn");
        formData.put("ssel", "0");
        formData.put("tsel", "0");
        formData.put("kc", "0");
        formData.put("tk", token(text));
        formData.put("q", text);
    }

    @Override
    public String query() throws Exception {
        URIBuilder uri = new URIBuilder(url);
        for (String key : formData.keySet()) {
            String value = formData.get(key);
            uri.addParameter(key, value);
        }
        HttpUriRequest request = new HttpGet(uri.toString());
        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();

        String result = EntityUtils.toString(entity, "utf-8");

        EntityUtils.consume(entity);
        response.getEntity().getContent().close();
        response.close();

        return result;
    }

    @Override
    public String parses(String text) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(text).get(0).get(0).get(0).toString();
    }

    private String token(String text) {
        String tk = "";
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            FileReader reader = new FileReader("./tk/Google.js");
            engine.eval(reader);

            if (engine instanceof Invocable) {
                Invocable invoke = (Invocable)engine;
                tk = String.valueOf(invoke.invokeFunction("token", text));
            }
        } catch (Exception e) {}
        return tk;
    }
}
