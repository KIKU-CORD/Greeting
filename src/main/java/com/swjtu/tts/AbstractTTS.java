package com.swjtu.tts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

import com.swjtu.http.AbstractHttpAttribute;
import com.swjtu.http.HttpParams;
import com.swjtu.lang.LANG;
import com.swjtu.util.Util;

/**
 * AbstractTTS is an abstract base class for all TTS,
 * including several (abstract) functions. By setting
 * parameters, the request is sent to the target server,
 * and the return is converted to the result of the speech.
 *
 * @see com.swjtu.tts.impl.BaiduTTS
 * @see com.swjtu.tts.impl.GoogleTTS
 * @see com.swjtu.tts.impl.SogouTTS
 * @see com.swjtu.tts.impl.TencentTTS
 * @see com.swjtu.tts.impl.YoudaoTTS
 */
public abstract class AbstractTTS extends AbstractHttpAttribute implements HttpParams{

    public AbstractTTS(String url) {
        super(url);
    }

    @Override
    public String run(LANG from, LANG to, String text) {
        return null;
    }

    @Override
    public String run(LANG source, String text) {
        String saveFile = null;
        setFormData(source, text);
        try {
            saveFile = query();
        } catch (IOException e) {
            e.printStackTrace();
        }
        close();
        return saveFile;
    }

    @Override
    public String query() throws IOException {
        String uri = Util.getUrlWithQueryString(url, formData);
        HttpGet request = new HttpGet(uri);

        HttpResponse response = httpClient.execute(request);
        InputStream is = response.getEntity().getContent();

        StringBuilder saveFile = new StringBuilder();
        saveFile.append("./tts/")
                .append(this.getClass().getName())
                .append("-")
                .append(Util.md5(uri))
                .append(".mp3");

        File file = new File(saveFile.toString());
        FileOutputStream fos = new FileOutputStream(file);
        byte[] buffer = new byte[4096];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        is.close();

        return saveFile.toString();
    }

    @Override
    public abstract void setFormData(LANG source, String text );

    @Override
    public void setFormData(LANG from, LANG to, String text) {}
}
