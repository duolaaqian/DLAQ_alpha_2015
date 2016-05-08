package com.dlaq.test.tasted.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Test01 {
	public static void main(String[] args) {
		String path = Test01.class.getClassLoader().getResource("").getPath();
        String resource = "com/dlaq/test/tasted/freemarker/templates/";
		Configuration cfg = new Configuration();
		cfg.setDefaultEncoding("utf-8");
		try {
			cfg.setDirectoryForTemplateLoading(new File(path+resource));
			Template template = cfg.getTemplate("1.ftl");
			Map<String ,String> root = new HashMap<String ,String>();
			root.put("user", "老高");
			Writer out = new StringWriter();
			template.process(root, out);
			String str = out.toString();
			System.out.println("====================");
			System.out.println(str);
			System.out.println("====================");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} 
		
	}
}
