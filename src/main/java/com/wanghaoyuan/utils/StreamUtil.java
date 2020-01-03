package com.wanghaoyuan.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {

	/**
	 * �ر����ķ���
	 * @Title: closeAll   
	 * @Description: �����������������ɾ������򿪵���   
	 * @param: @param autoCloseables      
	 * @return: void      
	 * @throws
	 */
	public static void closeAll(AutoCloseable... autoCloseables ) {
		if(autoCloseables!=null) {
			for(AutoCloseable autoCloseable:autoCloseables) {
				try {
					autoCloseable.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @Title: readTextFile   
	 * @Description: �����ķ�ʽ����ȡ�ı��ļ�����   
	 * @param: @param file
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String readTextFile(File file) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] b = new byte[1024];
			String str = null;
			while (inputStream.read(b)!=-1) {
				str += new String(b);
			}
			return str;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally {
			closeAll(inputStream);
		}
	}
	/**
	 * @Title: getFileContent   
	 * @Description: �����ļ�ȫ����ȡ�ļ�����   
	 * @param: @param fileFullName
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String readTextFile(String fileFullName) {
		return readTextFile(new File(fileFullName));
	}
	
	public static void writeTextFile(String content,File file,boolean append) {
		BufferedWriter writer = null;
		try {
			//�ж�д�ļ����ļ����Ƿ����
			String parent = file.getParent();
			File parentFile = new File(parent);
			if(!parentFile.exists()) {
				parentFile.mkdirs();
			}
			//д�ļ�
			writer = new BufferedWriter(new FileWriter(file,append));
			writer.write(content);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			closeAll(writer);
		}
	}
	
	public static void writeTextFile(String content,String fileFullName,boolean append) {
		writeTextFile(content,new File(fileFullName), append);
	}

	public static void main(String[] args) {
		String readTextFile = readTextFile("C:\\Users\\Administrator\\Desktop\\pom.xml");
		writeTextFile(readTextFile, "C:\\Users\\Administrator\\Desktop\\aa\\aa.xml",false);
	}
}
