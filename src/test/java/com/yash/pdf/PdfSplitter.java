package com.yash.pdf;

import java.io.File;
import java.util.List;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import junit.framework.Assert;

public class PdfSplitter {

	public static void main(String[] args) throws Exception {
		
//		 to extract the file from source location
		File file = new File("C:\\Users\\Yash\\Downloads\\P_I_7_I_IP11_Hollander.pdf"); 
		
//		to convert into pdf file
		PDDocument pdfDocument = Loader.loadPDF(file); 

//		to create a new file 
		File newFile = new File("C:\\Users\\Yash\\Downloads\\split.pdf\\split"); 
		
//		to get total no. of pages
//		System.out.println(pdfDocument.getPages().getCount());

//		to split the pdf 
		Splitter splitter = new Splitter(); 
		List<PDDocument> splitPages = splitter.split(pdfDocument);
		
//		to create a new pdf
		PDDocument newDoc = new PDDocument();
		int count = 1;
		boolean firstPage = false;
		boolean lastPage = false;
		
		for (PDDocument mydoc : splitPages) {
			PDFTextStripper pdfTextStripper = new PDFTextStripper(); // to read the pdf
			String docText = pdfTextStripper.getText(mydoc);

			if (docText.contains("Works number/department")) {
				if (!firstPage) {
					firstPage = true;
				} else if (!lastPage) {
					lastPage = true;
				}

			}
//			keep adding pages until endPage is found out
			if (firstPage && !lastPage) {
				newDoc.addPage(mydoc.getPage(0)); 
			}
			
//			if we get both startPage and endPage of single worker
			if (firstPage && lastPage) {
				newDoc.save(newFile + "" + count + ".pdf"); // save file to directory
				count++;
				
//				Create new document 
				newDoc = new PDDocument(); 
				
//				Add first page of new pdf
				newDoc.addPage(mydoc.getPage(0)); 
				lastPage = false;
			}
		}
//		to save the last page
		newDoc.save(newFile + "" + count + ".pdf");
		System.out.println("The given file has been split into 2");
	}
}
