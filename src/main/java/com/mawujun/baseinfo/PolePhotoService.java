package com.mawujun.baseinfo;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mawujun.exception.BusinessException;
import com.mawujun.permission.ShiroUtils;
import com.mawujun.repository.cnd.Cnd;
import com.mawujun.service.AbstractService;
import com.mawujun.utils.DateUtils;
import com.mawujun.utils.M;
import com.mawujun.utils.file.ThumbUtils;



/**
 * @author mawujun qq:16064988 e-mail:mawujun1234@163.com 
 * @version 1.0
 * @since 1.0
 */
@Service
@Transactional(propagation=Propagation.REQUIRED)
public class PolePhotoService extends AbstractService<PolePhoto, String>{

	@Autowired
	private PolePhotoRepository polePhotoRepository;
	
	@Override
	public PolePhotoRepository getRepository() {
		return polePhotoRepository;
	}
	
	private static String pole_photos_ctx="pole_photos";
	private static String pole_photos_thumb="thumb";
	public String upload(String realpath,CommonsMultipartFile uploadfile,String pole_id,String id) {

		String dirpath=realpath+pole_photos_ctx;
		File dir=new File(dirpath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		PolePhoto polePhoto=new PolePhoto();
		//String id=DateUtils.format4Id();
		polePhoto.setId(id);
		polePhoto.setUser_id(ShiroUtils.getUserId());
		polePhoto.setUploadDate(new Date());
		polePhoto.setPole_id(pole_id);
		String filenmae=uploadfile.getFileItem().getName();
		filenmae=id+filenmae.substring(filenmae.lastIndexOf("."));
		polePhoto.setUrl("/"+pole_photos_ctx+"/"+filenmae);
		//
		
		//System.out.println(uploadfile.getOriginalFilename());
		//System.out.println(uploadfile.getFileItem().getName());
		//File upload_image=new File(dirpath+File.pathSeparator+);
		//System.out.println(file.getFileItem().getInputStream());
		//FileUtils.writeByteArrayToFile(file, file.getFileItem().get());
		
		String thumb_url=store_photo(dirpath,filenmae,uploadfile);
		polePhoto.setThumb_url(thumb_url);
		polePhotoRepository.create(polePhoto);
		return id;
	}
	
	public String store_photo(String dirpath,String filenmae,CommonsMultipartFile uploadfile){
		File file=new File(dirpath+File.separator+filenmae);
		try {
			FileUtils.writeByteArrayToFile(file, uploadfile.getFileItem().get());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("图片写入服务器失败!");
		}
		
		//生成缩略图
		String thumb_dirpath=dirpath+File.separator+pole_photos_thumb;
		File dir=new File(thumb_dirpath);
		if(!dir.exists()){
			dir.mkdirs();
		}
		String thumb_path= thumb_dirpath+File.separator+filenmae;
		try {
			ThumbUtils.zoomImageScale(file,thumb_path, 300);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException("生成缩略图失败!");
		}
		return "/"+pole_photos_ctx+"/"+pole_photos_thumb+"/"+filenmae;
		
//		//生成base64
//		String base64=null;
//		try {
//			base64=Base64Utils.encodeImage(thumb_dirpath+File.separator+filenmae);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new BusinessException("生成缩略图编码失败!");
//		}
//		return base64;
	}
	
	
	public List<PolePhoto> queryByPole(String pole_id) {
		return polePhotoRepository.query(Cnd.select().andEquals(M.PolePhoto.pole_id, pole_id));
	}

}
