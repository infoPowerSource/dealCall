package com.deal.util;

import java.io.File;
import java.io.IOException;

/**
 * 创建文件工具类
 * 
 * @author zhipeng.xu
 */
public class FileUtils {
	/**
	 * 创建文件，包含文件的路径，可以是相对路径或绝对路径
	 * 
	 * @param fileName 文件名称和路径
	 * @return 创建的文件
	 */
	public static File createFile(String fileName) throws IOException {
		if (fileName == null) {
			throw new NullPointerException("fileName is null");
		}
		return createFile(new File(fileName));
	}

	/**
	 * 创建文件，包含创建文件的路径
	 * 
	 * @param file
	 * @return 创建的文件
	 * @throws IOException
	 */
	public static File createFile(File file) throws IOException {
		file = file.getCanonicalFile();
		if (!file.exists()) {
			File parent = file.getParentFile();

			boolean b = parent.mkdirs() | file.createNewFile();
			if (!b) {
				throw new IllegalArgumentException("file not exist");
			}
		}
		return file;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param file
	 * @return
	 */
	public static String getExtend(String fileName) {
		if (fileName != null) {
			int start = fileName != null ? fileName.lastIndexOf('.') + 1 : 0;
			if (start > 0) {
				return fileName.substring(start);
			}
		}
		return null;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param file
	 * @return
	 */
	public static String getExtend(File file) {
		if (file != null) {
			return getExtend(file.getName());
		}
		return null;
	}
	
	/**
	 * 获取文件名,不包含扩展名
	 * @param fileName
	 * @return
	 */
	public static String getName(String fileName){
		String name = fileName;
		if (fileName != null) {
			int end = fileName.lastIndexOf(".");
			if (end > 0) {
				name = fileName.substring(0,end);
			}
		}
		return name;
	}
	
	/**
	 * 从文件路径中获取文件名，要求文件路径格式:\file\excel\xxxx.xx
	 * @param filePath
	 * @return
	 */
	public static String getFileNameByPath(String filePath){
		String name = filePath;
		if(filePath!=null){
			String[] paths = filePath.split("\\\\");
			name = paths[paths.length-1];
		}
		return name;
	}
}
