package cn.zzpoyo.io.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 12345;
    private static String DEFAULT_SERVER_IP = "127.0.0.1";
    
    public static void send(String expression) {
        send(DEFAULT_SERVER_PORT, expression);
    }
    
    public static void send(int port, String expression) {
        System.out.println("算术表达式为：" + expression);
        
        try (Socket socket = new Socket(DEFAULT_SERVER_IP, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println(expression);
            System.out.println("___结果为：" + in.readLine());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

