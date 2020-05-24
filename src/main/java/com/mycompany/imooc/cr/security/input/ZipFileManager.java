package com.mycompany.imooc.cr.security.input;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipFileManager {

    public void unzip(InputStream in) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(in);
        ZipEntry zipEntry;
        byte[] buffer = new byte[1024];

        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            FileOutputStream out = new FileOutputStream(zipEntry.getName());
            int readCount;
            while ((readCount = in.read(buffer)) != -1) {
                out.write(buffer, 0, readCount);
            }
            out.close();
        }

        in.close();
    }

    private static final int MAX_ENTRY_SIZE = 10 * 1024 * 1024;

    public void safeUnzip(InputStream in) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(in);
        ZipEntry zipEntry;
        byte[] buffer = new byte[1024];

        while ((zipEntry = zipInputStream.getNextEntry()) != null) {
            if (zipEntry.getSize() > MAX_ENTRY_SIZE) {
                throw new IllegalStateException("压缩文件解压后超过阈值");
            }
            FileOutputStream out = new FileOutputStream(zipEntry.getName());
            int readCount;
            while ((readCount = in.read(buffer)) != -1) {
                out.write(buffer, 0, readCount);
            }
            out.close();
        }

        in.close();
    }
}
