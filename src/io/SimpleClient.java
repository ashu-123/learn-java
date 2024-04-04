package io;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SimpleClient {

	public static void main(String[] args) throws IOException {

		Socket socket = new Socket();
		InetSocketAddress address = new InetSocketAddress("127.0.0.1", 12345);
		socket.connect(address);
		
		OutputStream outputStream = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(new OutputStreamWriter(outputStream));
		pw.println("list-fruits");
		pw.flush();
		
		InputStream inputStream = socket.getInputStream();
		LineNumberReader reader = new LineNumberReader(new InputStreamReader(inputStream));
		int number = Integer.parseInt(reader.readLine());
		for (int i = 0; i < number; i++) {
			String fruit = reader.readLine();
			System.out.println(fruit);
		}
		socket.close();
	}
}
