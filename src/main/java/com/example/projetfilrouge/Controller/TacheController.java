package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.MarqueDAO;
import com.example.projetfilrouge.DAO.TacheDAO;
import com.example.projetfilrouge.Model.MarqueVoiture;
import com.example.projetfilrouge.Model.Tache;
import com.example.projetfilrouge.view.TacheView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TacheController {

    @Autowired
    TacheDAO tacheDAO;
    @JsonView(TacheView.class)
    @GetMapping("/tache")

    public List<Tache> liste() {

        return tacheDAO.findAll();

    }



}