package com.dsjg.dbcpUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.dsjg.utils.DBCPUtil;

public class DbcpTest {
	// 删除
	@Test
	public void delete() throws Exception {
		Connection conn = DBCPUtil.getConnection();

		// 准备sql语句
		String sql = "delete from student where sid = ?";

		// 创建预编译对象
		PreparedStatement pstm = conn.prepareStatement(sql);
		// 设置sql语句里面的值
		pstm.setInt(1, 2);

		int n = pstm.executeUpdate();
		if (n > 0) {
			System.out.println("删除成功");
		} else {
			System.out.println("删除失败");
		}

		// 释放资源

		DBCPUtil.release(conn, pstm, null);

	}

}
