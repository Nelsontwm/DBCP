package com.dsjg.utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtil {
	//���ӳض��� ��ʼ��ֵnull
	private static DataSource ds = null;
	//��̬�����
	static{
		//���Զ���
		Properties prop = new Properties();
		try {
			//��ȡ�����ļ�
			//public void load(InputStream inStream)
	          //throws IOException
			prop.load(DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));//����DBCPUtil��classes��·�������������ļ�
		
		    ds = BasicDataSourceFactory.createDataSource(prop);//�õ�һ������Դ (�õ����ӳ�)
		} catch (Exception e) {
			throw new ExceptionInInitializerError("��ʼ���������������ļ�");
		}
	}
	
	public static Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("������æ������");
		}
	}
	
	public static void release(Connection conn,Statement stmt,ResultSet rs){
		//�ر���Դ
				if(rs!=null){
					try {
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					rs = null;
				}
				if(stmt!=null){
					try {
						stmt.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					stmt = null;
				}
				if(conn!=null){
					try {
						conn.close();//�ر�
					} catch (Exception e) {
						e.printStackTrace();
					}
					conn = null;
				}
	}
	
}
