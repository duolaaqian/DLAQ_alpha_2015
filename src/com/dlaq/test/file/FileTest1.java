package com.dlaq.test.file;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.webframe.support.util.SystemLogUtils;

import com.dlaq.test.jdbc.spring.JdbcDao;
import com.dlaq.test.jdbc.spring.SpringJdbcTest1;

public class FileTest1 {
	public static void main(String args[]){
/*		String path = "C:/Users/Toswhiba/Desktop/files/";
		String name = "123.text";
		String content = "123445665434";
		
		FileGenerator fg = new FileGenerator();
		fg.createFile(path+name, content);*/
		List<Map<String, Object>> l = getList();
		SystemLogUtils.rootPrintln("start");
		create1(l);
		SystemLogUtils.rootPrintln("end");
	}

	public static List<Map<String, Object>> getList(){
		JdbcDao jdbcDao = SpringJdbcTest1.getJdbcDao();
		String sql = "SELECT C.NAME_,C.CODE_,A.TITLE_,A.LINK_,A.CONTENT_ FROM T_CMS_ARTICLE A ";
		sql += " LEFT JOIN t_cms_catalog c ";
		sql += " ON a.CATALOG_ID_ = c.id_ ";
		sql += " WHERE c.code_ IS NOT NULL ";
		sql += " AND a.CONTENT_ IS NOT NULL ";
		sql += " LIMIT 4000 ";
		List<Map<String, Object>> l = jdbcDao.getJdbcTemplate().queryForList(sql);
		
		return l;
	}

	public static void create1(List<Map<String, Object>> l){
		String path = "C:/Users/Toswhiba/Desktop/files/";
		FileGenerator fg = new FileGenerator();
		String name,code,content;
		String con = fg.readFile("C:/Users/Toswhiba/Desktop/aaaa.shtml");
		
		for(Map<String, Object> m : l){
//			System.out.println("--------------------------");
/*			System.out.println(m.get("NAME_"));
			System.out.println(m.get("CODE_"));
			System.out.println(m.get("TITLE_"));
			System.out.println(m.get("LINK_"));
			System.out.println(m.get("CONTENT_"));*/
//			System.out.println(m.get("LINK_"));
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
		
//		System.out.println(fg.getCreateDirs());
		System.out.println(fg.getCreateFileTime());
	}
	
	public static void createByList(List<Map<String, Object>> l){
		
	}
	
	
	public static String wrapLink(String link){
		link = link.replace("\\", "/");
		String arr[] = link.split("/");
		return arr[arr.length-1];
	}
	
}
