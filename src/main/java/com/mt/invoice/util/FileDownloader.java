package com.mt.invoice.util;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.net.URL;

@Component
public class FileDownloader {

    public byte[] download(String url) {
        try (InputStream in = new URL(url).openStream()) {
            return in.readAllBytes();
        } catch (Exception e) {
            throw new RuntimeException("图片下载失败", e);
        }
    }
}

