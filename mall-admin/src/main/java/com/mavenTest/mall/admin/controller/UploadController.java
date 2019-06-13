package com.mavenTest.mall.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.mavenTest.mall.common.util.ImageServerUtil;
import com.mavenTest.mall.common.util.JsonUtils;
import com.mavenTest.mall.common.util.PropertiesUtil;
import com.mavenTest.mall.common.util.QiniuUtil;
import com.mavenTest.mall.common.util.ImageServerUtil.EnumImageServer;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

@Controller
@RequestMapping("/upload")
public class UploadController {

	@RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadPic(MultipartFile pictureFile) throws IOException {
		String fileName = upload(pictureFile);

		Map<String, Object> map = new HashMap<>();
		map.put("fileName", fileName);
		map.put("url", getUrl(fileName));
		return map;
	}

	/*
	 * url=服务器+fileName
	 */
	private Object getUrl(String fileName) {
		if (ImageServerUtil.getImageServer() == EnumImageServer.QINIU) {
			return QiniuUtil.getUrl(fileName);
		} else {
			return ImageServerUtil.getLocalUrl(fileName);
		}
	}

	/*
	 * 返回上传的图片的名字
	 */
	private String upload(MultipartFile multipartFile) throws IOException {
		if (ImageServerUtil.getImageServer() == EnumImageServer.QINIU) {
			return QiniuUtil.uploadImage(multipartFile.getBytes());
		} else {
			return uploadByLocal(multipartFile);
		}
	}

	private String uploadByLocal(MultipartFile pictureFile) {
		// 为了防止文件重名（重名文件会覆盖丢失）,所以生成一个不重复的随机的名字dcf38fd6925c44528d6c402d02c04d7b
		String name = UUID.randomUUID().toString().replace("-", "");
		// jpg、png
		String ext = FilenameUtils.getExtension(pictureFile.getOriginalFilename());
		String fileName = name + "." + ext;// dcf38fd6925c44528d6c402d02c04d7b.png
		String filePath = "D:\\pic\\" + fileName;
		try {
			pictureFile.transferTo(new File(filePath));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	@RequestMapping(value = "/multiPicUpload", produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public String multiPicUpload(MultipartFile pictureFile) {
		System.out.println("UploadManagerController.multiPicUpload()");
		try {
			String fileName = uploadByLocal(pictureFile);

			String url = "/pic/" + fileName;

			Map<String, Object> map = new HashMap<>();
			map.put("error", 0);
			map.put("fileName", fileName);
			map.put("url", url);
			return JsonUtils.objectToJson(map);
		} catch (Exception e) {
			Map<String, Object> map = new HashMap<>();
			map.put("error", 1);
			map.put("message", "图片上传失败");
			return JsonUtils.objectToJson(map);
		}
	}

	public static void main(String[] args) {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		System.out.println(uuid);
	}
}
