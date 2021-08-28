package cn.mldn.mldnspring.action;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
@Controller										// 定义控制器
@RequestMapping("/pages/photo/*")				// 定义该类的访问父路径，与方法中的路径进行组合为完整路径
public class UploadAction {						// 自定义Action程序类
	@PostMapping("/upload")
	@ResponseBody								// 使用Restful风格返回
	public Object upload(String msg, MultipartFile photo) throws Exception { // 接收请求参数
		Map<String,Object> result = new HashMap<String,Object>() ; 	// 保存结果
		result.put("msg", msg) ;									// 保存信息
		if (photo == null || photo.isEmpty()) {
			result.put("photo-name", "nophoto") ;					// 没有文件，保存默认名称
		} else {	// 现在有上传文件
			String photoName = UUID.randomUUID() + "." + photo.getContentType().substring(photo.getContentType().lastIndexOf("/") + 1) ;	// 创建保存文件名称
			String photoPath = ContextLoader.getCurrentWebApplicationContext().getServletContext()
					.getRealPath("/WEB-INF/upload/") + photoName;	// 定义文件保存路径
			result.put("photo-size", photo.getSize()) ;				// 保存上传文件信息
			result.put("photo-mime", photo.getContentType()) ;		// 保存上传文件信息
			result.put("photo-name", photoName) ;					// 保存上传文件信息
			result.put("photo-path", photoPath) ;					// 保存上传文件信息
			photo.transferTo(new File(photoPath)); 					// 进行文件转存
		} 
		return result ;												// 返回文件信息
	}
	@GetMapping("/upload_pre")
	public String uploadPre() {
		return "photo/photo_upload";								// 上传表单
	}

}
