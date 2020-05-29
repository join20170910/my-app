package com.mycompany.app.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author john
 */
public class BitMatrixUtils {



    /**
     * @description:   //TODO 生成二维码配置
     * @author:        john
     * @return:
     * @exception:
     * @date:          2020/5/29 10:00
     */
    public static void getBitMatrix(HttpServletResponse response, String codeUrl){

        if(codeUrl == null) {
            throw new  NullPointerException();
        }

        try{
            //生成二维码配置
            Map<EncodeHintType,Object> hints =  new HashMap<>();

            //设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            //编码类型
            hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE,400,400,hints);
            OutputStream out =  response.getOutputStream();

            MatrixToImageWriter.writeToStream(bitMatrix,"png",out);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
