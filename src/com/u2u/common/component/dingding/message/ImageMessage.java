package com.u2u.common.component.dingding.message;


public class ImageMessage extends Message {
	
	public String media_id;
	
	public ImageMessage(String mediaId) {
		super();
		media_id = mediaId;
	}
	
	@Override
	public String type() {
		return "image";
	}
}
