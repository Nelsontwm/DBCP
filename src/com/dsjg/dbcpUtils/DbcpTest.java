package com.dsjg.dbcpUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import com.dsjg.utils.DBCPUtil;

public class DbcpTest {
	// ɾ��
	@Test
	public void delete() throws Exception {
		Connection conn = DBCPUtil.getConnection();

		// ׼��sql���
		String sql = "delete from student where sid = ?";

		// ����Ԥ�������
		PreparedStatement pstm = conn.prepareStatement(sql);
		// ����sql��������ֵ
		pstm.setInt(1, 2);

		int n = pstm.executeUpdate();
		if (n > 0) {
			System.out.println("ɾ���ɹ�");
		} else {
			System.out.println("ɾ��ʧ��");
		}

		// �ͷ���Դ

		DBCPUtil.release(conn, pstm, null);

	}

}
