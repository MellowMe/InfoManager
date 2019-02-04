package cn.yufan.infomanager.dao;

import cn.yufan.infomanager.utils.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

class BaseDao<T> {
	private QueryRunner queryRunner = new QueryRunner();
	private Class<T> clazz;

	BaseDao() {
		Type aClass = this.getClass().getGenericSuperclass();
		if (aClass instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) aClass;
			Type[] arr = type.getActualTypeArguments();
			clazz = (Class<T>) arr[0];
		}

	}

	T getFirst(String sql, Object... args) {
		Connection conn = DBUtils.getConnection();
		T t = null;
		try {
			t = queryRunner.query(conn, sql, new BeanHandler<>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return t;
	}

	List<T> getList(String sql, Object... args) {
		Connection conn = DBUtils.getConnection();
		List<T> list = null;
		try {
			list = queryRunner.query(conn, sql, new BeanListHandler<>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeConnection(conn);
		}
		return list;
	}

	int update(String sql ,Object... args){
		Connection conn = DBUtils.getConnection();
		int rows = 0;
		try{
			rows = queryRunner.update(conn,sql,args);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.closeConnection(conn);
		}
		return rows;
	}

	Object getValue(String sql,Object... args){
		Connection conn = DBUtils.getConnection();
		Object value = null;
		try{
			value = queryRunner.query(conn,sql, new ScalarHandler<>(),args);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtils.closeConnection(conn);
		}
		return value;
	}
}
