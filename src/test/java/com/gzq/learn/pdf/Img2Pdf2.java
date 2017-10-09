package com.gzq.learn.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.pdfbox.util.PDFMergerUtility;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-28 9:28.
 */
public class Img2Pdf2 {

    private static String imgFolder = "D:\\img2pdf\\img\\";
    private static String pdfFolder = "D:\\img2pdf\\pdf\\";
    private static String targetFolder = "D:\\img2pdf\\target\\";
    private static String targetName = "target.pdf";

    public static void main(String[] args) throws Exception {
        Instant start = Instant.now();
        Img2Pdf2 img2Pdf2 = new Img2Pdf2();
        img2Pdf2.img2Pdf(imgFolder, pdfFolder);
        //mergePdf(pdfFolder, targetName);
        testMerge(pdfFolder, targetName);
        Instant end = Instant.now();
        System.out.println("转换结束,花费时间为"+ Duration.between(start, end).getSeconds()+"s");

    }

    public static String[] getFiles(String folder) throws IOException {
        File _folder = new File(folder);
        String[] filesInFolder;

        if (_folder.isDirectory()) {
            filesInFolder = _folder.list();
            return filesInFolder;
        } else {
            throw new IOException("Path is not a directory");
        }
    }

    public static void mergePdf(String sourceFolder, String targetName) throws Exception {
        //pdf合并工具类
        PDFMergerUtility mergePdf = new PDFMergerUtility();
        String[] filesInFolder = getFiles(sourceFolder);
        for (int i = 0; i < filesInFolder.length; i++) {
            //循环添加要合并的pdf存放的路径
            mergePdf.addSource(sourceFolder + File.separator + filesInFolder[i]);
        }
        //设置合并生成pdf文件名称
        mergePdf.setDestinationFileName(targetFolder + File.separator + targetName);
        //合并pdf
        mergePdf.mergeDocuments();
    }


    public  void img2Pdf(String sourceFolder,String targetFolder) {
        FileOutputStream fos = null;
        try {
            String[] filesInFolder = getFiles(sourceFolder);
            for (int i = 0; i < filesInFolder.length; i++) {
                String imagePath = sourceFolder + File.separator + filesInFolder[i];
                //去掉文件名后缀
                String fileName = filesInFolder[i].substring(0, filesInFolder[i].lastIndexOf("."));
                String pdfPath = targetFolder + fileName + ".pdf";
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


    public static void testMerge(String sourceFolder, String targetName) throws Exception {
        String[] filesInFolder = getFiles(sourceFolder);


        ArrayList<PdfReader> readers = new ArrayList<PdfReader>();
        int totalPages = 0;
        for (int i = 0; i < filesInFolder.length; i++) {
            //循环添加要合并的pdf存放的路径
            PdfReader reader = new PdfReader(sourceFolder+ filesInFolder[i]);
            totalPages += reader.getNumberOfPages();
            readers.add(reader);
        }

        FileOutputStream out = new FileOutputStream(targetFolder + targetName);
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, out);

        document.open();
        PdfContentByte cb = writer.getDirectContent();

        int pageOfCurrentReaderPDF = 0;
        Iterator<PdfReader> iteratorPDFReader = readers.iterator();

// Loop through the PDF files and add to the output.
        while (iteratorPDFReader.hasNext()) {
            PdfReader pdfReader = iteratorPDFReader.next();

            // Create a new page in the target for each source page.
            while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                document.newPage();
                pageOfCurrentReaderPDF++;
                PdfImportedPage page = writer.getImportedPage(pdfReader, pageOfCurrentReaderPDF);
                cb.addTemplate(page, 0, 0);
            }
            pageOfCurrentReaderPDF = 0;
        }

        out.flush();
        document.close();
        out.close();
    }




}
