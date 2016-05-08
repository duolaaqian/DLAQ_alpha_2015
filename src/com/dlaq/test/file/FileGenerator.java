package com.dlaq.test.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class FileGenerator {
	
	private StringBuffer createDirs = new StringBuffer();
	
	private long createFileTime = 0;
	private long starttime = 0;
	private long endtime = 0;
	
	public void createDirs(String path){
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
			createDirs.append(path);
			createDirs.append("\r\n");
		}
	}
	public String getCreateDirs(){
		return createDirs.toString();
	}
	public String getCreateFileTime(){
		return createFileTime+"";
	}
	public void createFile(String path,String content){
		starttime = System.currentTimeMillis();
		FileOutputStream out = null;
		byte[] buff = new byte[]{};
		try {
			buff = content.getBytes();
			out = new FileOutputStream(path);
			out.write(buff, 0, buff.length);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		buff = null;
		endtime = System.currentTimeMillis();
		createFileTime += (endtime-starttime);
	}
	
	//test
	public void createFile(Map<String,Object> m){
		String path = "C:/Users/Toswhiba/Desktop/files/";
		FileGenerator fg = new FileGenerator();
		String name,code,content;
		String con = fg.readFile("C:/Users/Toswhiba/Desktop/aaaa.shtml");
		
		if(null==m){
			System.out.println("=== m is null ! ===");
		}
		if(null==m.get("NAME_")){
			System.out.println("=== NAME_ is null ! ==="+"[ "+m+" ]");
		}
		
		if(m.containsKey("NAME_")
				&&StringUtils.isNotBlank(m.get("NAME_").toString())){
			name = m.get("LINK_").toString();
			code = m.get("CODE_").toString();
			content = m.get("CONTENT_").toString();
			fg.createDirs(path+code);
			fg.createFile(path+code+"/"+wrapLink(name), con+content);
			fg.createFile(path+code+"/"+wrapLink(name)+".css", con+content);
		}
	}
	public String wrapLink(String link){
		link = link.replace("\\", "/");
		String arr[] = link.split("/");
		return arr[arr.length-1];
	}
	
	public String readFile(String filePath){
		String lineTxt = "";
		String txt = "";
		try {
			File file=new File(filePath);
			if(file.isFile() && file.exists()){ //判断文件是否存在
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				while((lineTxt = bufferedReader.readLine()) != null){
					txt += lineTxt;
				}
				bufferedReader.close();
				return txt;
			}else{
				System.out.println("找不到指定的文件");
				return "";
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		} 
		return "";
	}

}
