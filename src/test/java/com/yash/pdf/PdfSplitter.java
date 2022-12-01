package com.yash.pdf;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfSplitter {

	/**
	 * @author-Yash Dixit
	 * @author-Email-yashdixit927@gmail.com
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		Scanner s=new Scanner(System.in);
		//to extract the file from source location
		System.out.println("Enter file path");
		File file = new File(s.next());


		//to convert into pdf file
		PDDocument pdfDocument = Loader.loadPDF(file); 

		//to create a new file
		System.out.println("Enter the path where you want to save the file");
		File newFile = new File(s.next());

		//to split the pdf
		Splitter splitter = new Splitter(); 
		List<PDDocument> splitPages = splitter.split(pdfDocument);
		
		//to create a new pdf
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
			//keep adding pages until endPage is found out
			if (firstPage && !lastPage) {
				newDoc.addPage(mydoc.getPage(0)); 
			}
			
			//if we get both startPage and endPage of single worker
			if (firstPage && lastPage) {
				newDoc.save(newFile + "" + count + ".pdf"); // save file to directory
				count++;
				
				//Create new document
				newDoc = new PDDocument(); 
				
				//Add first page of new pdf
				newDoc.addPage(mydoc.getPage(0)); 
				lastPage = false;
			}
		}
		//to save the last page
		newDoc.save(newFile + "" + count + ".pdf");
		System.out.println("The given file has been split into 2");
	}
}
