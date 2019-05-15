package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import ok.JDBCTools;
import org.junit.Test;

public class JDBCTest {

	/**
	 * ��ȡ blob ����: 
	 * 1. ʹ�� getBlob ������ȡ�� Blob ����
	 * 2. ���� Blob �� getBinaryStream() �����õ�����������ʹ�� IO ��������. 
	 */
	@Test
	public void readBlob(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = JDBCTools.getConnection();
			String sql = "SELECT id, name customerName, email, birth, picture " 
					+ "FROM customers WHERE id = 13";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				
				System.out.println(id + ", " + name  + ", " + email);
				Blob picture = resultSet.getBlob(5);
				
				InputStream in = picture.getBinaryStream();
				System.out.println(in.available()); 
				
				OutputStream out = new FileOutputStream("flower.jpg");
				
				byte [] buffer = new byte[1024];
				int len = 0;
				while((len = in.read(buffer)) != -1){
					out.write(buffer, 0, len);
				}
				
				in.close();
				out.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.releaseDB(resultSet, preparedStatement, connection);
		}
	}
	
	/**
	 * ���� BLOB ���͵����ݱ���ʹ�� PreparedStatement����Ϊ BLOB ����
	 * ������ʱ�޷�ʹ���ַ���ƴд�ġ�
	 * 
	 * ���� setBlob(int index, InputStream inputStream)
	 */
	@Test
	public void testInsertBlob(){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = JDBCTools.getConnection();
			String sql = "INSERT INTO customers(name, email, birth, picture)" 
					+ "VALUES(?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, "ABCDE");
			preparedStatement.setString(2, "abcde@atguigu.com");
			preparedStatement.setDate(3, 
					new Date(new java.util.Date().getTime()));
			
			InputStream inputStream = new FileInputStream("Hydrangeas.jpg");
			preparedStatement.setBlob(4, inputStream);
			
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.releaseDB(null, preparedStatement, connection);
		}
	}
	
	/**
	 * ȡ�����ݿ��Զ����ɵ�����
	 */
	@Test
	public void testGetKeyValue() {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = JDBCTools.getConnection();
			String sql = "INSERT INTO customers(name, email, birth)" +
					"VALUES(?,?,?)";
//			preparedStatement = connection.prepareStatement(sql);

			//ʹ�����ص� prepareStatement(sql, flag) 
			//������ PreparedStatement ����
			preparedStatement = connection.prepareStatement(sql, 
					Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, "ABCDE");
			preparedStatement.setString(2, "abcde@atguigu.com");
			preparedStatement.setDate(3, 
					new Date(new java.util.Date().getTime()));
			
			preparedStatement.executeUpdate();
			
			//ͨ�� getGeneratedKeys() ��ȡ�����������ɵ������� ResultSet ����
			//�� ResultSet ��ֻ��һ�� GENERATED_KEY, ���ڴ�������ɵ�����ֵ.
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if(rs.next()){
				System.out.println(rs.getObject(1));
			}
			
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 0; i < rsmd.getColumnCount(); i++){
				System.out.println(rsmd.getColumnName(i + 1)); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			JDBCTools.releaseDB(null, preparedStatement, connection);
		}
		
	}

}
