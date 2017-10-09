package com.gzq.learn.pdf;


import org.apache.pdfbox.util.PDFMergerUtility;

import java.io.File;
import java.io.IOException;

/**
 * ${DESCRIPTION}
 *
 * @author think
 * @created 2017-06-26 22:34.
 */
public class PdfMerge {
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

    public static void main(String[] args) throws Exception {

        //pdf合并工具类
        PDFMergerUtility mergePdf = new PDFMergerUtility();

        //String folder = "F:/test";
        String folder = "C:\\Users\\think\\Desktop\\task";
        String destinationFileName = "mergedTest.pdf";

        String[] filesInFolder = getFiles(folder);


        for (int i = 0; i < filesInFolder.length; i++) {
            //循环添加要合并的pdf存放的路径
            mergePdf.addSource(folder + File.separator + filesInFolder[i]);
        }

        //设置合并生成pdf文件名称
        mergePdf.setDestinationFileName(folder + File.separator + destinationFileName);
        //合并pdf
        mergePdf.mergeDocuments();
    }

    public void mergePdf(String sourceFolder ,String targetName) throws Exception {
        //pdf合并工具类
        PDFMergerUtility mergePdf = new PDFMergerUtility();

        String folder = sourceFolder;
        String destinationFileName = targetName;

        String[] filesInFolder = getFiles(folder);


        for (int i = 0; i < filesInFolder.length; i++) {
            //循环添加要合并的pdf存放的路径
            mergePdf.addSource(folder + File.separator + filesInFolder[i]);
        }

        //设置合并生成pdf文件名称
        mergePdf.setDestinationFileName(folder + File.separator + destinationFileName);
        //合并pdf
        mergePdf.mergeDocuments();
    }
}
