package org.anne.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.anne.base.FileProcess;
import org.anne.util.vo.FileInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 
 * 
 */
@Controller
@RequestMapping(value = "/fileHandler")
public class FileHandleController {

	/**
	 * 单文件上传
	 * 
	 * @param name
	 * @RequestParam
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/singleFileUpload.html", method = RequestMethod.POST)
	public @ResponseBody
	FileInfoVO singleFileUpload(@RequestParam("file") MultipartFile file)
			throws IOException {
		FileInfoVO fiv = new FileInfoVO();
		String newname = UUID.randomUUID().toString();
		fiv.setFileId(newname);
		fiv.setFileName(file.getOriginalFilename());
		if (!file.isEmpty()) {
			FileProcess.writeFile(file.getInputStream(), newname);

		}
		return fiv;
	}
	
	/**
	 * 单文件上传
	 * 
	 * @param name
	 * @RequestParam
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/scriptFileUpload.html", method = RequestMethod.POST)
	public @ResponseBody
	void scriptFileUpload(@RequestParam("file") MultipartFile file,HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String scriptCallback = request.getParameter("scriptCallback");
		String fileId = UUID.randomUUID().toString();
		if (!file.isEmpty()) {
			FileProcess.writeFile(file.getInputStream(), fileId);
		}
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		out.write("<script type='text/javascript'>"+scriptCallback+"('" + fileId
				+ "','" +file.getOriginalFilename() + "')</script>");
		out.flush();
		out.close();
	}

	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/multiFileUpload.html", method = RequestMethod.POST)
	public @ResponseBody
	List<FileInfoVO> multiFileUpload(MultipartHttpServletRequest request)
			throws Exception {
		List<MultipartFile> files = request.getFiles("file");
		List<FileInfoVO> fileinfos = new ArrayList<FileInfoVO>();
		for (int i = 0; i < files.size(); i++) {
			if (!files.get(i).isEmpty()) {
				FileInfoVO fiv = new FileInfoVO();
				String newname = UUID.randomUUID().toString();
				FileProcess.writeFile(files.get(i).getInputStream(), newname);
				fiv.setFileId(newname);
				fiv.setFileName(files.get(i).getOriginalFilename());
				fileinfos.add(fiv);
			}
		}
		return fileinfos;
	}

	/**
	 * 下载图片
	 * 
	 * @param fileId
	 *            文件ID
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/downloadImage.html")
	public void downloadImage(FileInfoVO vo, HttpServletResponse response)
			throws IOException {
		ServletOutputStream outputStream = response.getOutputStream();
		response.reset();

		response.setContentType("image/jpeg");
		byte[] b = FileCopyUtils.copyToByteArray(FileProcess.getFile(vo
				.getFileId()));
		try {
			outputStream.write(b, 0, b.length);
			outputStream.flush();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}