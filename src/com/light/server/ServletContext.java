package com.light.server;

import java.util.HashMap;
import java.util.Map;

/**
 *  web ����������
 * @author Light
 *
 */
public class ServletContext {

	// �洢����ͬ����� servlet
	// servletName -> servlet �����ȫ��
	private Map<String,String> servletMap;
	
	// �洢���� url �� servlet �Ķ�Ӧ��ϵ
	// ���� url -> servletName
	private Map<String,String> mappingMap;
	
	public ServletContext() {
		this.servletMap = new HashMap<String, String>();
		this.mappingMap = new HashMap<String, String>();
	}

	public Map<String, String> getServletMap() {
		return servletMap;
	}

	public void setServletMap(Map<String, String> servletMap) {
		this.servletMap = servletMap;
	}

	public Map<String, String> getMappingMap() {
		return mappingMap;
	}

	public void setMappingMap(Map<String, String> mappingMap) {
		this.mappingMap = mappingMap;
	}
	
}
