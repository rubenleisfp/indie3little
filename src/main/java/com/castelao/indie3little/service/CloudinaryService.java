package com.castelao.indie3little.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class CloudinaryService {

	private static Logger LOG = LoggerFactory.getLogger(ImageService.class);
	private Cloudinary cloudinary;
	
	public CloudinaryService() {
		cloudinary= new Cloudinary(ObjectUtils.asMap(
				"cloud_name", "dbus18fik",
				"api_key", "742732978442861",
				"api_secret", "Xp25Vyo93GCX-Ixb98M0JIOzfUo",
				"secure", true));
	}
	
	public void upload(String base64Data) throws IOException {
		Map<String,String> options = new HashMap<String, String>();
		options.put("folder", "indie3");
		options.put("display_name", "hola");
				
		
		Map upload = cloudinary.uploader().upload("data:image/jpg;base64," + base64Data,
				options);
		LOG.error(upload.toString());
		//{signature=a2fa3b8c0bcd90678d3b54484736fbd29046bc72, format=jpg, resource_type=image, secure_url=https://res.cloudinary.com/dbus18fik/image/upload/v1705753823/indie3/ws8xnos0olrv5e0faccy.jpg, created_at=2024-01-20T12:30:23Z, asset_id=3fefcf0428e15274f9755b0a90971b7b, version_id=ef9f0484386b7d52aae5fd6b4d56e631, type=upload, version=1705753823, url=http://res.cloudinary.com/dbus18fik/image/upload/v1705753823/indie3/ws8xnos0olrv5e0faccy.jpg, public_id=indie3/ws8xnos0olrv5e0faccy, tags=[], folder=indie3, api_key=742732978442861, bytes=7878, width=500, etag=1c1a52a1d3ecf9ccaafb34524517b2ec, placeholder=false, height=264}
		//http://res.cloudinary.com/dbus18fik/image/upload/v1705753823/indie3/ws8xnos0olrv5e0faccy.jpg
		//https://res.cloudinary.com/dbus18fik/image/upload/v1705753823/indie3/ws8xnos0olrv5e0faccy.jpg
		//secure_url
	}
}
