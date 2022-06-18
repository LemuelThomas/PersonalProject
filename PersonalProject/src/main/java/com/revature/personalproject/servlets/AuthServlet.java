package com.revature.personalproject.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.personalproject.dtos.Credentials;
import com.revature.personalproject.models.NetflixAcc;
import com.revature.personalproject.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final AuthService authService;
    public AuthServlet( AuthService authService,ObjectMapper mapper) {
        this.mapper = mapper;
        this.authService = authService;
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        resp.setStatus(204);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Use the ObjectMapper to convert the JSON request payload to a Java object
        Credentials creds = mapper.readValue(req.getInputStream(), Credentials.class);
        //try {
            // Use the AuthService to validate that the provided credentials meet business rules
                if (creds.getUsername() != null && creds.getPassword() != null)
                {
                    NetflixAcc netflixAcc = authService.login(creds.getUsername(), creds.getPassword());
                    authService.login(creds.getUsername(), creds.getPassword());
                    String z = String.valueOf(authService.login(creds.getUsername(), creds.getPassword()));
                    HttpSession session = req.getSession(); // use req.getSession(false) to prevent a session from being made
                    session.setAttribute("auth-user", z); // this attribute is visible on any requests with this session attached
                    resp.setContentType("application/json");
                    resp.getWriter().write(mapper.writeValueAsString(netflixAcc));
                }
        {

        }
    }

}




