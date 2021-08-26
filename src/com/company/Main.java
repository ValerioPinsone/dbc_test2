package com.company;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class Main {
    private Connection conn;
    public static void main(String[] args) {
	// write your code here
        Main test = new Main();
        /*
        try {
            test.getConnection().isClosed();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

         */
        try {
            test.insert("Carta");
            test.select();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private void select() throws SQLException {
        String sql = "select * from prodotto";
        PreparedStatement ps = getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            System.out.println(rs.getString(2));
        }

    }

    private void insert(String nome) throws SQLException {
        String sql = "insert into prodotto(nome) values ('"+nome+"')";
        PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        System.out.println("Inserimento numero: "+rs.getInt(1));
    }

    private Connection getConnection() throws SQLException {
        if (conn==null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setDatabaseName("testjava");
            dataSource.setPortNumber(3306);
            dataSource.setServerName("127.0.0.1");
            dataSource.setUser("root");
            dataSource.setPassword("");
            conn = dataSource.getConnection();
        }
        return conn;
    }
}
