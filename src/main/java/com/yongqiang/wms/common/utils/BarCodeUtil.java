package com.yongqiang.wms.common.utils;

import org.apache.commons.lang.ObjectUtils;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 条形码生成的工具类
 * @author yang.shang
 * @create 2019-06-02 09:07
 **/
public class BarCodeUtil {

    /**
     * 生成code128条形码
     *
     * @param height        条形码的高度
     * @param width         条形码的宽度
     * @param message       要生成的文本
     * @param withQuietZone 是否两边留白
     * @param hideText      隐藏可读文本
     * @return 图片对应的字节码
     */
    public static void generateBarCode128(String message, Double height, Double width, boolean withQuietZone, boolean hideText, OutputStream ous) {
        Code128Bean bean = new Code128Bean();
        // 分辨率
        int dpi = 150;
        // 设置两侧是否留白
        bean.doQuietZone(withQuietZone);

        // 设置条形码高度和宽度
//        bean.setBarHeight((double) ObjectUtils.defaultIfNull(height, 9.0D));
//        if (width != null) {
//            bean.setModuleWidth(width);
//        }
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
        bean.setModuleWidth(moduleWidth);
        // 设置文本位置（包括是否显示）
        if (hideText) {
            bean.setMsgPosition(HumanReadablePlacement.HRP_NONE);
        }
        // 设置图片类型
        String format = "image/png";

//        ous = new ByteArrayOutputStream();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi,
                BufferedImage.TYPE_BYTE_BINARY, false, 0);

        // 生产条形码
        bean.generateBarcode(canvas, message);
        try {
            canvas.finish();
        } catch (IOException e) {
            //ByteArrayOutputStream won't happen
        }
//        System.out.println((ByteArrayOutputStream)ous.toChar);

    }
}
