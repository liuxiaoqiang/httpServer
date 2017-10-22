package com.light.server;

import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * �����ļ���
 * @author Light
 *
 */
public class WebApp {

	private static ServletContext context;
	
	static {
		context = new ServletContext();
		Map<String,String> servletMap = context.getServletMap();
		Map<String,String> mappingMap = context.getMappingMap();
		
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser sax = factory.newSAXParser();
			
			WebHandler handler = new WebHandler();
			
			sax.parse(Thread
					.currentThread()
					.getContextClassLoader()
					.getResourceAsStream("com/light/server/web.xml"), 
					handler);
			
			// ע�� servlet
			List<ServletXml> servletXmlList = handler.getServletXmlList();
			for (ServletXml servletXml : servletXmlList) {
				servletMap.put(servletXml.getServletName(), servletXml.getServletClazz());
			}
			
			// ע�� mapping
			List<ServletMappingXml> mappingXmlList = handler.getMappingXmlList();
			for (ServletMappingXml mapping : mappingXmlList) {
				List<String> urls = mapping.getUrlPattern();
				for (String url : urls) {
					mappingMap.put(url, mapping.getServletName());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  ͨ������ url ��ȡ��Ӧ�� Servlet ����
	 * @param url
	 * @return
	 */
	public static String getServletClazz(String url) {
		if (url == null || "".equals(url.trim())) {
			return null;
		}
		
		String servletName = context.getMappingMap().get(url);
		String servletClazz = context.getServletMap().get(servletName);
		
		return servletClazz;
	}
}
