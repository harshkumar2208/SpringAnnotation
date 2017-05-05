package com.pyramid.utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Utility {

	public static final String[] MONTH = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
	public static String saveDir = "C:\\Users\\harshk\\Desktop\\Test\\";
	public static String fileURL = "http://192.168.56.1:280/testFiles/";
	public static final int BUFFER_SIZE = 4096;
	
	public static void main(String[] args) throws Exception {
//		downloadFile();
		downloadFileUsingJSOUP();
	}

	public static void downloadFile() throws Exception {
		
		URL url = new URL(fileURL);
		InputStream is = null;
		try {
			is = url.openStream();
			byte[] buffer = new byte[1024];
			int bytesRead = -1;
			StringBuilder page = new StringBuilder(1024);
			while ((bytesRead = is.read(buffer)) != -1) {
				page.append(new String(buffer, 0, bytesRead));
			}
			// Spend the rest of your life using String methods
			// to parse the result...
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
		}

	}

	public static void downloadFileUsingJSOUP() {
		
		try {
			URL url = new URL(fileURL);
	        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			Document doc = Jsoup.connect(fileURL).get();
			Elements links = doc.getElementsByTag("a");
			System.out.println(links.size());
			for (int i = 0; i < links.size(); i++) {
				if(links.get(i).attr("href").indexOf(".zip") > -1){
					System.out.println(links.get(i).attr("href"));
				}
//				System.out.println(getFileSize(new URL(fileURL+link.attr("href"))));
			}
			for (Element link : links) {
				
				
				// System.out.println(link.attr("href") + " - " + link.text());
				
				/*if(link.attr("href").indexOf(".org") < 1){
					String fileName = link.attr("href").substring(link.attr("href").lastIndexOf("/")+1).trim();
					if(fileName.indexOf(".zip") > -1){
						System.out.println("fileName==>> "+fileName);
						saveFile(httpConn, fileName);
					}
				}*/
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public static int getFileSize(URL url) {
		System.out.println(url);
	    HttpURLConnection conn = null;
	    try {
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("HEAD");
	        conn.getInputStream();
	        
	        long date = conn.getLastModified();
	        if (date == 0)
	          System.out.println("No last-modified information.");
	        else
	          System.out.println("Last-Modified: " + new Date(date));
	        
	        return conn.getContentLength();
	    } catch (Exception e) {
	        return -1;
	    } finally {
	        conn.disconnect();
	    }
	}
	
	public static void saveFile(HttpURLConnection httpConn, String fileName) throws Exception{
		InputStream inputStream = httpConn.getInputStream();
        String saveFilePath = saveDir + fileName;
         
        // opens an output stream to save into file
        FileOutputStream outputStream = new FileOutputStream(saveFilePath);
        int bytesRead = -1;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
	}

}
