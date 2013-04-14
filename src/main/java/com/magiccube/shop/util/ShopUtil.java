package com.magiccube.shop.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import com.magiccube.common.model.EnvironmentInfoVO;
import com.magiccube.core.util.tools.DateTimeFormatUtils;

public class ShopUtil {
	
	public static final String IMG_UPLOAD_RELATIVE_PATH = "web" + File.separator + "upload";

	public static final String IMG_UPLOAD_PATH = EnvironmentInfoVO.WEBROOT + IMG_UPLOAD_RELATIVE_PATH;

	/**
	 * save image to server under the /web/upload folder.
	 * 
	 * @param filename
	 * @return
	 */
	public static String saveImage(MultipartFile file) {
		String filename = file.getOriginalFilename();
		if (!isImage(filename))
			return "";
		// append a timestamp to fileName
		String basename = FilenameUtils.getBaseName(filename), 
			extension = FilenameUtils.getExtension(filename), 
			newname = basename + "_" + _timestamp(new Date()) + "." + extension;
		String toSave = IMG_UPLOAD_PATH + File.separator + newname;
		File image = new File(toSave);
		try {
			file.transferTo(image);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
		String uploadPath = File.separator.equals("\\")? 
				IMG_UPLOAD_RELATIVE_PATH.replaceAll("\\\\", "/"): IMG_UPLOAD_RELATIVE_PATH;
		return "/" + uploadPath + "/" + newname;
	}

	private static String _timestamp(Date date) {
		return DateTimeFormatUtils.formatDateTime(date, "yyyyMMddHHmmss");
	}

	public static boolean isImage(String filename) {
		String[] extensions = new String[] { 
				"jpg", "bmp", "jpeg", "gif", "png", 
				"JPG", "BMP", "JPEG", "GIF", "PNG" };
		return FilenameUtils.isExtension(filename, extensions);
	}
	
	/**
	 * force create a new file
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public static File forceNewFile(String name) throws IOException {
		File file = new File(name);
		if(!file.exists()) {
			File parentFile = file.getParentFile();
			if(parentFile != null) {
				parentFile.mkdirs();
			}
			file.createNewFile();
		}
		return file;
	}
}
