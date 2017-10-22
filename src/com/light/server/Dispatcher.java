package com.light.server;

import java.io.IOException;
import java.net.Socket;

public class Dispatcher implements Runnable {
	// socket �ͻ���
	private Socket socket;
	// �������
	private Request request;
	// ��Ӧ����
	private Response response;
	// ��Ӧ��
	private int code = 200;
	
	public Dispatcher(Socket socket) {
		this.socket = socket;
		try {
			this.request = new Request(socket.getInputStream());
			this.response = new Response(socket.getOutputStream());
		} catch (IOException e) {
			code = 500;
			return;
		}
		
	}

	@Override
	public void run() {
		try {
			// ����������
			String servletClazz = WebApp.getServletClazz(this.request.getUrl());
			Servlet servlet = (Servlet) Class.forName(servletClazz).newInstance();
			// ��������
			servlet.service(request, response);
			this.response.pushToClient(code);
			this.socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
