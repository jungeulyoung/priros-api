package com.lnt.priros.model.io;

import com.lnt.priros.resource.Constant;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

@Slf4j
public class HashOutputStream extends BufferedOutputStream {
    final MessageDigest messageDigest;

    public HashOutputStream(String algType, OutputStream outputStream) throws IOException {
        super(outputStream);

        try {
            messageDigest = MessageDigest.getInstance(algType);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    public HashOutputStream(OutputStream outputStream) throws IOException {
        this(Constant.SHA256, outputStream);
    }

    @Override
    public synchronized void write(int b) throws IOException {
        messageDigest.update((byte) b);
        super.write(b);
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        messageDigest.update(b, off, len);
        super.write(b, off, len);
    }

    public byte[] digest() {
        return messageDigest.digest();
    }

    public String getBase64EncodedHash() {
        return Base64.encodeBase64String(digest());
    }
}
