/*
 * Copyright 2014 benhaile.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.benhaile.javase8.sample.chapter9;

/*
 * #%L
 * javase8-sample
 * %%
 * Copyright (C) 2013 - 2014 benhaile
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 *
 * @author benhail
 */
public class Base64Demo {

    static void basicCode() throws UnsupportedEncodingException {
        // 编码
        String asB64 = Base64.getEncoder().encodeToString("some string".getBytes("utf-8"));
        System.out.println(asB64); // 输出为: c29tZSBzdHJpbmc=

        // 解码
        byte[] asBytes = Base64.getDecoder().decode("c29tZSBzdHJpbmc=");
        System.out.println(new String(asBytes, "utf-8"));
    }

    // 输出为:
//Using Basic Alphabet: c3ViamVjdHM/YWJjZA==
//Using URL Alphabet: c3ViamVjdHM_YWJjZA==
    static void urlCode() throws UnsupportedEncodingException {
        String basicEncoded = Base64.getEncoder().encodeToString("subjects?abcd".getBytes("utf-8"));
        System.out.println("Using Basic Alphabet: " + basicEncoded);

        String urlEncoded = Base64.getUrlEncoder().encodeToString("subjects?abcd".getBytes("utf-8"));
        System.out.println("Using URL Alphabet: " + urlEncoded);

    }

    static void mimeCode() throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 10; ++t) {
            sb.append(UUID.randomUUID().toString());
        }

        byte[] toEncode = sb.toString().getBytes("utf-8");
        String mimeEncoded = Base64.getMimeEncoder().encodeToString(toEncode);
        System.out.println(mimeEncoded);
    }

    public static void wrapping() throws IOException {
        String src = "This is the content of any resource read from somewhere"
                + " into a stream. This can be text, image, video or any other stream.";

        // 编码器封装OutputStream, 文件/tmp/buff-base64.txt的内容是BASE64编码的形式
        try (OutputStream os = Base64.getEncoder().wrap(new FileOutputStream("buff-base64.txt"))) {
            os.write(src.getBytes("utf-8"));
        }

        // 解码器封装InputStream, 以及以流的方式解码, 无需缓冲
        // is being consumed. There is no need to buffer the content of the file just for decoding it.
        try (InputStream is = Base64.getDecoder().wrap(new FileInputStream("buff-base64.txt"))) {
            int len;
            byte[] bytes = new byte[100];
            while ((len = is.read(bytes)) != -1) {
                System.out.print(new String(bytes, 0, len, "utf-8"));
            }
        }
    }

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        basicCode();
        urlCode();
        mimeCode();
        wrapping();
    }
}
