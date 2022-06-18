package com.revature.personalproject.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.personalproject.daos.NetflixAccDaoPostgres;
import com.revature.personalproject.dtos.ResourceCreationResponse;
import com.revature.personalproject.models.NetflixAcc;
import com.revature.personalproject.services.AuthService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;



public class NetflixAccServlet extends HttpServlet {

    private final ObjectMapper mapper;
    private final NetflixAccDaoPostgres accDAO;
    private final AuthService authService;

    public NetflixAccServlet(ObjectMapper mapper, NetflixAccDaoPostgres accDAO, AuthService authService)
    {
        this.mapper = mapper;
        this.accDAO = accDAO;
        this.authService = authService;
    }

    public void init() throws ServletException
    {
        System.out.println("[LOG] - NetflixAccServlet instantiated!");
        System.out.println("[LOG] - Init param, test-init-key: " + this.getServletConfig().getInitParameter("test-init-key"));
        System.out.println("[LOG] - Context param, test-context-key: " + this.getServletContext().getInitParameter("test-context-key"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            NetflixAcc newAcc = mapper.readValue(req.getInputStream(), NetflixAcc.class);
            ResourceCreationResponse payload = authService.register(newAcc);
            resp.setStatus(201);
            resp.setContentType("/application/json");
            resp.getWriter().write(mapper.writeValueAsString(payload));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String potentialId = req.getParameter("id");
        String potentialUsername = req.getParameter("username");
        if(potentialUsername != null)
        {
            NetflixAcc feedbacks = accDAO.getByUsername(potentialUsername);
            resp.setContentType("application/json");
            resp.getWriter().write(String.valueOf(feedbacks));
        }
        else if(potentialId != null)
        {
            NetflixAcc idz = accDAO.getById(Integer.parseInt(potentialId));
            resp.setContentType("application/json");
            resp.getWriter().write(String.valueOf(idz));
        }
         else{
             List<NetflixAcc> accs = NetflixAccDaoPostgres.getAccs();
             String respPayload = mapper.writeValueAsString(accs);
             resp.setContentType("application/json");
             resp.getWriter().write(respPayload);
        }
    }
}
