package cn.zzpoyo.io.server;

import cn.zzpoyo.io.handler.ServerHandlerNIO;

public class ServerNIO {
    private static int DEFAULT_PORT = 12345;
    private static ServerHandlerNIO serverHandle;
    
    public static void start() {
        start(DEFAULT_PORT);
    }
    
    public static synchronized void start(int port) {
        if (serverHandle != null)
            serverHandle.stop();
        serverHandle = new ServerHandlerNIO(port);
        new Thread(serverHandle, "Server").start();
    }
    
    public static void main(String[] args) {
        start();
    }
    
}
