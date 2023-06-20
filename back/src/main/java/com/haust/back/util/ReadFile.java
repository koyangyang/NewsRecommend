package com.haust.back.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    public List readTxtFile(String filePath, String encoding) throws IOException {
        List res = new ArrayList();
        InputStream is = null;
        InputStreamReader isr = null;
            is = this.getClass().getClassLoader().getResourceAsStream(filePath);
            isr = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                res.add(lineTxt);
            }
        return res;
    }

}
