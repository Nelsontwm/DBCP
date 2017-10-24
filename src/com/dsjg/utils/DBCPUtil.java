package com.dsjg.utils;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCPUtil {
	//连接池对象 初始化值null
	private static DataSource ds = null;
	//静态代码块
	static{
		//属性对象
		Properties prop = new Properties();
		try {
			//读取属性文件
			//public void load(InputStream inStream)
	          //throws IOException
			prop.load(DBCPUtil.class.getClassLoader().getResourceAsStream("dbcpconfig.properties"));//根据DBCPUtil的classes的路径，加载配置文件
		
		    ds = BasicDataSourceFactory.createDataSource(prop);//得到一个数据源 (得到连接池)
		} catch (Exception e) {
			throw new ExceptionInInitializerError("初始化错误，请检查配置文件");
		}
	}
	
	public static Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("服务器忙。。。");
		}
	}
	
	public static void release(Connection conn,Statement stmt,ResultSet rs){
		//关闭资源
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
						conn.close();//关闭
					} catch (Exception e) {
						e.printStackTrace();
					}
					conn = null;
				}
	}
	
}
