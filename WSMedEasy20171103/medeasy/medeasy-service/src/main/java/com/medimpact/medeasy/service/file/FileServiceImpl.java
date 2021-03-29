package com.medimpact.medeasy.service.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.medimpact.medeasy.common.DataModel;
@Service
public class FileServiceImpl implements FileService{
	
	@Value("${file.download.location}")	
	private String downloadLocation;
	
	@Value("${file.download.filename}")	
	private String filename;

	@Override
	public String getDownloadFileLocation() {
		// TODO Auto-generated method stub
		return downloadLocation+","+filename;
	}
}
