package ok;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ok.JDBCTools;
import org.apache.commons.beanutils.BeanUtils;

public class DAO {

	/**
	 * 通用的基于PrepareStatement更新数据库的查询方法
	 * @param sql 更新语句
	 * @param args 替换 sql 占位符的可变参数
	 */
	public void update(String sql, Object... args) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseDB(null, preparedStatement, connection);
		}
	}

	/**
	 * 按照指定的要求，查询的结果取其中一条，感觉有点浪费
	 * @param clazz 返回值的类型
	 * @param sql 查询的语句
	 * @param args 作为 sql 语句的占位符的可变参数
	 * @param <T> 结果可能的泛型
	 * @return 第一个满足要求的对象
	 */
	public <T> T get(Class<T> clazz, String sql, Object... args) {
		List<T> result = getForList(clazz, sql, args);
		if(result.size() > 0){
			return result.get(0);
		}
		
		return null;
	}

	/**
	 * 传入 SQL 语句和 Class 对象, 询到的记返回 SQL 语句查录对应的 Class 类的对象的集合
	 * @param clazz: 对象的类型
	 * @param sql: SQL 语句
	 * @param args: 填充 SQL 语句的占位符的可变参数. 
	 * @return
	 */
	public <T> List<T> getForList(Class<T> clazz, 
			String sql, Object ... args) {

		List<T> list = new ArrayList<T>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			//1. 得到结果集
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}

			resultSet = preparedStatement.executeQuery();
			
			//2. 处理结果集, 得到 Map 的 List, 其中一个 Map 对象
			//就是一条记录. Map 的 key 为 reusltSet 中列的别名, Map 的 value
			//为列的值. 
			List<Map<String, Object>> values = 
					handleResultSetToMapList(resultSet);
			
			//3. 把 Map 的 List 转为 clazz 对应的 List
			//其中 Map 的 key 即为 clazz 对应的对象的 propertyName, 
			//而 Map 的 value 即为 clazz 对应的对象的 propertyValue
			list = transfterMapListToBeanList(clazz, values);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.releaseDB(resultSet, preparedStatement, connection);
		}

		return list;
	}

	/**
	 * 根据数据的List集合，得到最终的类的对象的List集合
	 * @param clazz 类对象
	 * @param values 从数据库中得到的数据的集合
	 * @param <T> 限定的泛型
	 * @return 指定泛型类的List集合
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public <T> List<T> transfterMapListToBeanList(Class<T> clazz,
			List<Map<String, Object>> values) throws InstantiationException,
			IllegalAccessException, InvocationTargetException {

		List<T> result = new ArrayList<T>();

		T bean = null;

		if (values.size() > 0) {
			for (Map<String, Object> m : values) {
				bean = clazz.newInstance();
				for (Map.Entry<String, Object> entry : m.entrySet()) {
					String propertyName = entry.getKey();
					Object value = entry.getValue();

					BeanUtils.setProperty(bean, propertyName, value);
				}
				// 13. 把 Object 对象放入到 list 中.
				result.add(bean);
			}
		}

		return result;
	}

	/**
	 * 处理结果集, 得到 Map 的一个 List, 其中一个 Map 对象对应一条记录
	 * 
	 * @param resultSet 结果集，根据结果集来得到一个个组形成的一个Map，再所有map放到List中
	 * @return 一个元素即一组数据的List集合
	 * @throws SQLException
	 */
	public List<Map<String, Object>> handleResultSetToMapList(
			ResultSet resultSet) throws SQLException {
		// 5. 准备一个 List<Map<String, Object>>:
		// 键: 存放列的别名, 值: 存放列的值. 其中一个 Map 对象对应着一条记录
		List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();

		List<String> columnLabels = getColumnLabels(resultSet);
		Map<String, Object> map = null;

		// 7. 处理 ResultSet, 使用 while 循环
		while (resultSet.next()) {
			map = new HashMap<String,Object>();

			for (String columnLabel : columnLabels) {
				Object value = resultSet.getObject(columnLabel);
				map.put(columnLabel, value);
			}

			// 11. 把一条记录的一个 Map 对象放入 5 准备的 List 中
			values.add(map);
		}
		return values;
	}

	/**
	 * 获取结果集的 ColumnLabel 对应的 List
	 * 
	 * @param rs 结果集
	 * @return 结果集一组数据
	 * @throws SQLException
	 */
	private List<String> getColumnLabels(ResultSet rs) throws SQLException {
		List<String> labels = new ArrayList<String>();

		ResultSetMetaData rsmd = rs.getMetaData();
		for (int i = 0; i < rsmd.getColumnCount(); i++) {
			labels.add(rsmd.getColumnLabel(i + 1));
		}

		return labels;
	}

	/**
	 *  返回某条记录的某一个字段的值 或 一个统计的值(一共有多少条记录等.)
	 * @param sql PrepareStatement的初始查询语句
	 * @param args 替换PrepareStatement占位符的参数
	 * @param <E> 单个的属性的符合要求的值
	 * @return
	 */
	public <E> E getForValue(String sql, Object... args) {
		
		//1. 得到结果集: 该结果集应该只有一行, 且只有一列
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			//1. 得到结果集
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);

			for (int i = 0; i < args.length; i++) {
				preparedStatement.setObject(i + 1, args[i]);
			}

			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()){
				return (E) resultSet.getObject(1);
			}
		} catch(Exception ex){
			ex.printStackTrace();
		} finally{
			JDBCTools.releaseDB(resultSet, preparedStatement, connection);
		}
		//2. 取得结果
		
		return null;
	}

}
