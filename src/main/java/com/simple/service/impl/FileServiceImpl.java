package com.simple.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simple.dao.FileDao;
import com.simple.po.File;
import com.simple.service.FileService;

@Service("fileService")
public class FileServiceImpl implements FileService {
	@Resource
	private FileDao FileDao;
	
	@Override
	public List<File> list(Map<String, Object> map) {
		return FileDao.list(map);
	}

	@Override
	public Long getTotal(Map<String, Object> map) {
		return FileDao.getTotal(map);
	}

	@Override
	public int updateFile(File file) {
		return  FileDao.updateFile(file);
		
	}

	@Override
	public int deleteFile(Integer id) {
		return  FileDao.deletFile(id);
		
	}

	@Override
	public List<File> searchFile(String word) {
		return FileDao.searchFile(word);
		
	}

	@Override
	public int insertFile(File file) {
		return  FileDao.insertFile(file);
	}

}
