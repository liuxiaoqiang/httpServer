package com.light.controller;

import com.light.server.Request;
import com.light.server.Response;
import com.light.server.Servlet;

public class LoginServlet extends Servlet {

	@Override
	protected void doPost(Request request, Response response) {
		response.println("<!DOCTYPE html>")
        .println("<html lang=\"zh\">")
        .println("    <head>      ")
        .println("        <meta charset=\"UTF-8\">")
        .println("        <title>����</title>")
        .println("    </head>     ")
        .println("    <body>      ")
        .println("        <h3>Hello " + request.getParameter("username") + "</h3>")// ��ȡ��½��
		.println("    </body>     ")
		.println("</html>");
	}

}
