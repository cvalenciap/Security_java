package pe.com.sedapal.seguridad.web.controlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import pe.com.sedapal.seguridad.web.bean.FileUploadForm;

@Controller
public class FileUploadController {

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String displayForm() {
		return "file_upload_form";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("uploadForm") FileUploadForm uploadForm, Model map) {

		List<MultipartFile> files = uploadForm.getFiles();

		List<String> fileNames = new ArrayList<String>();
		File toWrite;
		FileOutputStream output;
		if (null != files && files.size() > 0) {
			for (MultipartFile multipartFile : files) {
 
				String fileName = multipartFile.getOriginalFilename();
				
				try {
					toWrite = new File("D:\\pruebasseguridadsedapal\\" + fileName );					
					if (!toWrite.exists()) {
						if(toWrite.createNewFile()){
							multipartFile.transferTo(toWrite);	
							fileNames.add(fileName);
						}											
					} else {
						output = new FileOutputStream("D:\\pruebasseguridadsedapal\\" + fileName, false);
						output.write(multipartFile.getBytes());
						output.close();
						fileNames.add(fileName);
					}
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				// Handle file content - multipartFile.getInputStream()

			}
		}

		map.addAttribute("files", fileNames);
		return "file_upload_success";
	}
}
