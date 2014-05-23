package org.anne.junittest;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void insert(Test test, Class clazz) {

		Connection connection;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from " + test.getA());
			ResultSetMetaData metaData = prepareStatement.getMetaData();
			String u1 = "update " + test.getA() + " set ";
			String u2 = "update1 " + test.getA() + " set ";
			String insert = "insert into  " + test.getA() + "";
			String insert0 = "";
			String insert1 = "";
			String insertvalue = "";
			Class c = clazz;
			Method[] method = c.getDeclaredMethods();
			Map mvvv = new HashMap();
			for (int i = 0; i < method.length; i++) {
				Method m = method[i];
				if (m.getName().indexOf("set") == 0) {
					m.getName().substring(3);
					mvvv.put(m.getName().substring(3).toUpperCase(),
							m.getName());
				}
			}
			Map gets = new HashMap();
			for (int i = 0; i < method.length; i++) {
				Method m = method[i];
				if (m.getName().indexOf("get") == 0) {
					m.getName().substring(3);
					gets.put(m.getName().substring(3).toUpperCase(),
							m.getName());
				}
			}
			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				// System.out.println(metaData.getColumnTypeName(i));
				insert0 += metaData.getColumnName(i) + ",";
				insert1 += "?,";
				u2 += " " + metaData.getColumnName(i) + "=? ,";

				insertvalue += "entity."
						+ gets.get(metaData.getColumnName(i)
								.replaceAll("_", "").toString().toUpperCase())
						+ "(),";
				String setmethod = (String) mvvv.get(metaData.getColumnName(i)
						.replaceAll("_", "").toString().toUpperCase());
				if (metaData.getColumnTypeName(i).equalsIgnoreCase("INT")) {
					System.out.println("entity." + setmethod + "(rs.getInt(\""
							+ metaData.getColumnName(i) + "\"));");
				} else if (metaData.getColumnTypeName(i).equalsIgnoreCase(
						"VARCHAR")) {
					System.out.println("entity." + setmethod
							+ "(rs.getString(\"" + metaData.getColumnName(i)
							+ "\"));");

				} else if (metaData.getColumnTypeName(i).equalsIgnoreCase(
						"DATATIME")) {
					System.out.println("entity." + setmethod + "(rs.getDate(\""
							+ metaData.getColumnName(i) + "\"));");

				}

			}
			System.out.println(u1 + u2.substring(0, u2.length() - 1)
					+ " where ");
			System.out.println(insert + " ("
					+ insert0.substring(0, insert0.length() - 1) + ") values ("
					+ insert1.substring(0, insert1.length() - 1) + ")");
			System.out.println(insertvalue.substring(0,
					insertvalue.length() - 1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// jdbcTemplate.update("insert into test values(?,?)",
		// new Object[] { test.getA(), test.getB() });
	}

	public void update(Test test) {
		jdbcTemplate.update("update test set b=? where a=?", new Object[] {
				test.getB(), test.getA() });
	}

	public void delete(Test test) {
		jdbcTemplate.update("delete from test where a=?",
				new Object[] { test.getB() });
	}

	public List<Test> query(Test test) {
		final List<Test> result = new ArrayList<Test>();
		jdbcTemplate.query("select * from test where b=?",
				new Object[] { test.getB() }, new RowCallbackHandler() {

					@Override
					public void processRow(ResultSet rs) throws SQLException {
						Test t = new Test();
						t.setA(rs.getString("A"));
						t.setB(rs.getInt("B"));
						result.add(t);
					}
				});
		return result;
	}
}
