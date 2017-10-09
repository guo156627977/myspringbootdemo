package com.gzq.learn.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.gzq.learn.pdf.PdfMerge.getFiles;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-28 9:28.
 */
public class Img2Pdf {

    public static void main(String[] args) throws Exception {
        Img2Pdf img2Pdf = new Img2Pdf();
        img2Pdf.img2Pdf();
        PdfMerge pdfMerge = new PdfMerge();
        String destinationFileName = "C:\\Users\\think\\Desktop\\pdf";
        String targetName = "mergeTest.pdf";

        pdfMerge.mergePdf(destinationFileName, targetName);
        System.out.println("转换结束");

    }


    public void img2Pdf() {
        FileOutputStream fos = null;
        String folder = "C:\\Users\\think\\Desktop\\img";
        String destinationFileName = "C:\\Users\\think\\Desktop\\pdf\\";
        try {
            String[] filesInFolder = getFiles(folder);
            for (int i = 0; i < filesInFolder.length; i++) {
                String imagePath = folder + File.separator + filesInFolder[i];
                //去掉文件名后缀
                String fileName = filesInFolder[i].substring(0, filesInFolder[i].lastIndexOf("."));
                String pdfPath = destinationFileName + fileName + ".pdf";
                BufferedImage img = ImageIO.read(new File(imagePath));
                fos = new FileOutputStream(pdfPath);
                Document doc = new Document(PageSize.A4, 0, 0, 0, 0);
                //Document doc = new Document(null, 0, 0, 0, 0);
                Image image = Image.getInstance(imagePath);
                //合理压缩，h>w，按w压缩，否则按w压缩
                //int percent = getPercent(img.getHeight(), img.getWidth());
                //统一按照宽度压缩
                int percent = getPercent2(img.getHeight(), img.getWidth());
                //设置图片居中显示
                image.setAlignment(Image.MIDDLE);
                //按百分比压缩
                image.scalePercent(percent);
                //按固定比例压缩
                //image.scaleAbsolute(210.0f, 297.0f);
                //按照图片的原始大小设置页面大小
                //doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
                //按照压缩后的图片大小设置大小
                doc.setPageSize(new Rectangle(img.getWidth()* percent/100, img.getHeight() * percent / 100));
                //按照A4纸设置大小
                //doc.setPageSize(new Rectangle(PageSize.A4));
                PdfWriter.getInstance(doc, fos);
                doc.open();
                doc.add(image);
                doc.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 第一种解决方案
     * 在不改变图片形状的同时，判断，如果h>w，则按h压缩，否则在w>h或w=h的情况下，按宽度压缩
     *
     * @param h
     * @param w
     * @return
     */

    public int getPercent(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        if (h > w) {
            p2 = 297 / h * 100;
        } else {
            p2 = 210 / w * 100;
        }
        p = Math.round(p2);
        return p;
    }

    /**
     * 第二种解决方案，统一按照宽度压缩
     * 这样来的效果是，所有图片的宽度是相等的，自我认为给客户的效果是最好的
     *
     * @param h
     * @param w
     * @return
     */
    public int getPercent2(float h, float w) {
        int p = 0;
        float p2 = 0.0f;
        //p2 = 530 / w * 100;
        p2 = 595 / w * 100;
        p = Math.round(p2);
        return p;
    }


}
