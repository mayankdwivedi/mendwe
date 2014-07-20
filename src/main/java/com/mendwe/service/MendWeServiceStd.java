package com.mendwe.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface MendWeServiceStd {

	public boolean uploadFile(CommonsMultipartFile file,String username);
	
	public boolean uploadPostFile(CommonsMultipartFile file,Long postId);
}
