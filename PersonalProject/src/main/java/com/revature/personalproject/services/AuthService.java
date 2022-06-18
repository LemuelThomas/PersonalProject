package com.revature.personalproject.services;

import com.revature.personalproject.daos.NetflixAccDaoPostgres;
import com.revature.personalproject.dtos.ResourceCreationResponse;
import com.revature.personalproject.models.NetflixAcc;
import com.revature.personalproject.util.InvalidCredentialsException;
import com.revature.personalproject.util.UsernameNotAvailableException;
import java.util.List;

public class AuthService {
    static NetflixAccDaoPostgres accDAO = new NetflixAccDaoPostgres();
    private final NetflixAccDaoPostgres netflixAccDAO;
    public AuthService(NetflixAccDaoPostgres netflixAccDAO) {
        this.netflixAccDAO = netflixAccDAO;
    }

    public NetflixAcc login(String username, String password)
    {
        List<NetflixAcc> users = new NetflixAccDaoPostgres().getAccs();
        for(NetflixAcc user : users){
            if(username.equals(user.getUsername()) && password.equals(user.getPassword()))
            {
                System.out.println("Logged In Successfully!");
                return user;

            }
            else if (username.equals(user.getUsername()) && !password.equals(user.getPassword()))
            {
                System.out.println("Wrong Password!");
                throw new InvalidCredentialsException("Wrong Password Entered");
            }
            else{
                System.out.println("Account does not exist.");
                throw new InvalidCredentialsException("Account does not exist.");
            }
        }
        return null;
    }
    public ResourceCreationResponse register(NetflixAcc accToBeRegistered)
    {
        List<NetflixAcc> users = new NetflixAccDaoPostgres().getAccs();
        for(NetflixAcc user : users)
        {
            if(accToBeRegistered.getUsername().equals(user.getUsername())) {
                System.out.println("Username is already taken, please give a different username.");
                throw new UsernameNotAvailableException("Username is already taken");
            } else if (accToBeRegistered.getEmail().equals(user.getEmail())) {
                System.out.println("Email is already taken, please give a different email.");
                throw new InvalidCredentialsException("Email is already taken.");
            }
                return new ResourceCreationResponse(accDAO.save(accToBeRegistered).getId());
        }
        return null;
    }

}
