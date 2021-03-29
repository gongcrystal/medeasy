package com.medimpact.medeasy.web.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medimpact.medeasy.service.file.FileService;

@Controller
public class FileCtrl {

	/*@Resource
	private FileService fs;*/

/*	@Value("${file.download.location}")
	private String downloadLocation;*/

	@RequestMapping("/download")
	public void fileDownload(HttpServletRequest request, HttpServletResponse response) {
		
	/*	if (fs.getDownloadFileLocation() == null || fs.getDownloadFileLocation().split(",").length != 2)
			return;*/
		
		String fullLocation;
		String fileName = "GoogleChromeframeStandaloneEnterprise.4144293914.msi";
		/*fullLocation = fs.getDownloadFileLocation().split(",")[0];
		fileName = fs.getDownloadFileLocation().split(",")[1];*/
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("multipart/form-data");

		response.setContentType("application/octet-stream");  		
		/*response.setContentType("application/force-download");*/
		response.setHeader("Content-Disposition",
				"attachment;fileName=" + fileName);
		
		try {
			/*InputStream inputStream = new FileInputStream(
					fullLocation);	*/
			InputStream inputStream = this.getClass().getResourceAsStream("/download/GoogleChromeframeStandaloneEnterprise.4144293914.msi");
			
			OutputStream os = response.getOutputStream();
			

			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {				
				os.write(b, 0, length);
			}
			os.close();
			inputStream.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
