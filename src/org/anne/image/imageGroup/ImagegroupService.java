package org.anne.image.imageGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagegroupService {
	private static final Logger log = LoggerFactory
			.getLogger(ImagegroupService.class);
	@Autowired
	ImagegroupDAO imagegroupDAO;

	public void addImagegroup(Imagegroup entity) {
		imagegroupDAO.addImagegroup(entity);
	}

	public void updateImagegroup(Imagegroup entity) {
		imagegroupDAO.updateImagegroup(entity);
	}

	public void delImagegroup(Imagegroup entity) {
		imagegroupDAO.delImagegroup(entity);
	}

	public Imagegroup getImagegroupById(String id) {
		return imagegroupDAO.getImagegroupById(id);
	}

	/**
	 * 取得当前用户图片组的图片数
	 * 
	 * @param entity
	 *            userid
	 * @return
	 */
	public ImagegroupVo getImageCountByUserid(Imagegroup entity) {
		return imagegroupDAO.getImageCountByUserid(entity);
	}

}