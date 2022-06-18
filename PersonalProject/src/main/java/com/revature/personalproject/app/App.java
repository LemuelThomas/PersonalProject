package com.revature.personalproject.app;

import com.revature.personalproject.daos.NetflixAccDaoPostgres;
import com.revature.personalproject.util.ConnectionFactory;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        //System.out.println(ConnectionFactory.getInstance().getConnection());

        System.out.println(NetflixAccDaoPostgres.getByUsername("lemstry"));
    }
}
