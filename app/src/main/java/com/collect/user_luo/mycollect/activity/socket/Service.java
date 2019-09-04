package com.collect.user_luo.mycollect.activity.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket  连接测试服务 ，这是服务端代码， 可在eclipse 中运行
 * 0~1023：分配给系统的端口号    我们不可以乱用
 * 1024~49151：登记端口号，主要是让第三方应用使用
 *
 * 但是必须在IANA（互联网数字分配机构）按照规定手续登记，
 * 49152~65535：短暂端口号，是留给客户进程选择暂时使用，一个进程使用完就可以供其他进程使用。
 * 在Socket使用时，可以用1024~65535的端口号
 */
public class Service {

    public static void main(String[] args) {
        Socket socket = null; // 定义接受用的socket
        try {
            ServerSocket serversocket = new ServerSocket(9998); // 创建服务器类，设置端口，以连接客户端进行通信
            while (true) {
                System.out.println("服务器启动，等待客户端连接");
                socket = serversocket.accept(); // 接收客户端传来的socket，赋值给本地socket
                System.out.println("cooncet success"); // 显示是否连接成功
                ServerThread serverthread = new ServerThread(socket); // 创建多线程类把socket传入
                serverthread.start(); // 开始线程
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


class ServerThread extends Thread { // 自定义线程类继承Thread类
    Socket socket = null; // 定义本地的socket

    public ServerThread(Socket socket) { // 用构造方法对本地的socket赋值
        this.socket = socket;
    }

    public void run() { // 重写run方法
        try {
            InputStream is = socket.getInputStream(); // 定义输入流用来接收socket的
            InputStreamReader isr = new InputStreamReader(is);// 把字节流转成字符流
            BufferedReader br = new BufferedReader(isr);
            String str = null;
            while ((str = br.readLine()) != null) { // 把从客户端传来的信息赋给str
                System.out.println(str); // 输出信息
            }
            socket.shutdownInput(); // 关闭socket
            is.close();
            isr.close();
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
        }
    }
}

