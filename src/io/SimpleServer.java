package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(12345);
		
		while (true) {
			System.out.println("I am waiting for requests");
			
			Socket accept = serverSocket.accept();
			System.out.println("We have a request!");
			
			InputStream inputStream = accept.getInputStream();
			InputStreamReader in = new InputStreamReader(inputStream);
			LineNumberReader lineNumberReader = new LineNumberReader(in);
			String request = lineNumberReader.readLine();
			System.out.println("Request = " + request);
			
			if (request.equals("list-fruits")) {
				
				OutputStream outputStream = accept.getOutputStream();
				OutputStreamWriter out = new OutputStreamWriter(outputStream);
				PrintWriter pw = new PrintWriter(out);
				pw.println("3");
				pw.println("Apple");
				pw.println("Orange");
				pw.println("Banana");
				pw.flush();
			}
		}
	}
}
