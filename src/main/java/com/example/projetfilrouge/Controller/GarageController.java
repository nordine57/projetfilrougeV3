package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.GarageDAO;
import com.example.projetfilrouge.DAO.TacheDAO;
import com.example.projetfilrouge.Model.Tache;
import com.example.projetfilrouge.view.TacheView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class GarageController {

    @Autowired
    GarageDAO garageDAODAO;




}