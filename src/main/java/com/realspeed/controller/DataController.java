package com.realspeed.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realspeed.model.Data;
import com.realspeed.util.DataExporterUtil;
import com.realspeed.util.ExcelDataExporter;

@RestController
@RequestMapping("/data")
public class DataController {

	@PostMapping("/export")
	public ResponseEntity<String> exportData(@RequestBody List<Data> dataList) throws IOException {
		File tempDir = DataExporterUtil.getTemporaryDirectoryLocation();
		String status = ExcelDataExporter.writeObjects2ExcelFile(dataList, tempDir);
		if (status != null) {
			return new ResponseEntity<>("Exported Successfilly to : " + tempDir.getAbsolutePath(), HttpStatus.OK);
		}
		return new ResponseEntity<>("Please Try Again", HttpStatus.BAD_REQUEST);

	}

}
