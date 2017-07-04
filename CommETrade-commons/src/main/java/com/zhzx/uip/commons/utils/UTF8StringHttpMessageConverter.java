package com.zhzx.uip.commons.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class UTF8StringHttpMessageConverter extends StringHttpMessageConverter {

    public static final MediaType utf8TextPlain = new MediaType("text", "plain", Charset.forName("UTF-8"));
    public static final MediaType utf8TextHtml = new MediaType("text", "html", Charset.forName("UTF-8"));

    private boolean writeAcceptCharset = true;

    @Override
    protected MediaType getDefaultContentType(String dumy) {
    	if(StringUtils.startsWith(dumy, "<html>")){
    		return utf8TextHtml;
    	}
        return utf8TextPlain;
    }

    protected List<Charset> getAcceptedCharsets() {
        return Arrays.asList(utf8TextPlain.getCharSet());
    }

    
    @Override
    protected Long getContentLength(String s, MediaType contentType) {
    	
		if (contentType != null && contentType.getCharSet() != null) {
			Charset charset = contentType.getCharSet();
			try {
				return (long) s.getBytes(charset.name()).length;
			} catch (UnsupportedEncodingException ex) {
				throw new InternalError(ex.getMessage());
			}
		} else {
			return null;
		}
	}

    protected void writeInternal(String s, HttpOutputMessage outputMessage) throws IOException {
        if (this.writeAcceptCharset) {
            outputMessage.getHeaders().setAcceptCharset(getAcceptedCharsets());
        }
        Charset charset = utf8TextPlain.getCharSet();
        FileCopyUtils.copy(s, new OutputStreamWriter(outputMessage.getBody(), charset));
    }

    public boolean isWriteAcceptCharset() {
        return writeAcceptCharset;
    }

    public void setWriteAcceptCharset(boolean writeAcceptCharset) {
        this.writeAcceptCharset = writeAcceptCharset;
    }
}
