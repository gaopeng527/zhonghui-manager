package com.zhonghui.controller;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

public class FTPTest {
	
	@Test
	public void testFtpClient() throws Exception {
		// 创建一个FTPClient对象
		FTPClient ftpClient = new FTPClient();
		// 创建Ftp连接，默认端口为21
		ftpClient.connect("192.168.25.113", 21);
		// 登录Ftp服务器，使用用户名和密码
		ftpClient.login("ftpuser", "ftpuser");
		// 上传文件
		// 读取本地文件
		FileInputStream fis = new FileInputStream(new File("F:\\壁纸\\8-100GR24312[1].jpg"));
		// 设置上传的路径
		ftpClient.changeWorkingDirectory("/home/ftpuser/www/images");
		// 修改上传文件的格式，默认为文本
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		// 第一个参数：服务端文档名
		// 第二个参数：上传文件的InputStream
		ftpClient.storeFile("hello.jpg", fis);
		// 关闭连接
		ftpClient.logout();
		
	}
}
