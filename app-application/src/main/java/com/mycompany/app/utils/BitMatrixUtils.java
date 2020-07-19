package com.mycompany.app.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description:    //TODO 生成二维码配置
 * @Author:         john
 * @CreateDate:     2020/7/16 9:16
 * @UpdateUser:     john
 */
public final class BitMatrixUtils {

    public static void getBitMatrix(HttpServletResponse response, String codeUrl) throws WriterException, IOException {

        if(codeUrl == null) {
            throw new  NullPointerException();
        }

        //生成二维码配置
        Map<EncodeHintType,Object> hints =  new HashMap<>();
        //设置纠错等级
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        //编码类型
        hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");

        BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,400,400,hints);
        OutputStream out =  response.getOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix,"png",out);
    }
}
