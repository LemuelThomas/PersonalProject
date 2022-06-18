package com.revature.personalproject.daos;

import com.revature.personalproject.models.NetflixAcc;
import com.revature.personalproject.util.ConnectionFactory;
import com.revature.personalproject.util.DataSourceException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NetflixAccDaoPostgres {
        public static List<NetflixAcc> getAccs(){
            List<NetflixAcc> netflixAccs = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getInstance().getConnection()){

            String sql = "select * from netflix_acc";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                NetflixAcc netflixAcc = new NetflixAcc();
                netflixAcc.setId(rs.getInt("id"));
                netflixAcc.setFirstName(rs.getString("firstname"));
                netflixAcc.setLastName(rs.getString("lastname"));
                netflixAcc.setEmail(rs.getString("email"));
                netflixAcc.setUsername(rs.getString("username"));
                netflixAcc.setPassword(rs.getString("password"));
                netflixAcc.setAcc_id(rs.getInt("acc_id"));
                netflixAccs.add(netflixAcc);
            }
        } catch (SQLException e){
            System.err.println("An error occurred within NetflixAccDAO#getAcc");
        }
        return netflixAccs;
    }
    public static NetflixAcc getByUsername(String username)
    {
        try (Connection conn = ConnectionFactory.getConnection()){

            String sql = "select * from netflix_acc where username = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                return new NetflixAcc(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("acc_id")
                );
            }
        }catch (SQLException e){
            System.err.println("An error occurred within NetflixAccDAO#getByUsername");
        }

        return null;
    }
    public NetflixAcc getById(int identity)
    {
        List<NetflixAcc> byId = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()){

            String sql = "select * from netflix_acc where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, identity);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                return new NetflixAcc(
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("acc_id")
                );
            }
        } catch (SQLException e){
            System.err.println("An error occurred withing NetflixAccDAO#getById");
        }

        return null;
    }

    public NetflixAcc getPassword(String password)
    {
        try (Connection conn = ConnectionFactory.getConnection()){
            String sql = "select * from netflix_acc where password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, password);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next())
            {
                return new NetflixAcc(
                        rs.getString("password")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.err.println("An error occurred within NetflixAccDAO#getPassword");
        }
       return null;
    }
    public NetflixAcc save(NetflixAcc newAccount) {

        try (Connection conn = ConnectionFactory.getConnection()) {

            String sql = "INSERT INTO netflix_acc (firstname, lastname, username, password, email, acc_id)VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, newAccount.getFirstName());
            pstmt.setString(2, newAccount.getLastName());
            pstmt.setString(3, newAccount.getUsername());
            pstmt.setString(4, newAccount.getPassword());
            pstmt.setString(5, newAccount.getEmail());
            pstmt.setInt(6, newAccount.getAcc_id());


            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted != 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
                rs.next();
                newAccount.setId(rs.getInt("id"));
                return newAccount;
            }

            // TODO clean up later
            throw new RuntimeException("Should never be here");


        } catch (SQLException e) {
            throw new DataSourceException("An error occurred during data access", e);
        }

    }
}
