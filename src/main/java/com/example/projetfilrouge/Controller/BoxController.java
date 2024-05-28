package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.BoxDAO;
import com.example.projetfilrouge.DAO.TacheDAO;
import com.example.projetfilrouge.Model.Box;
import com.example.projetfilrouge.Model.Tache;
import com.example.projetfilrouge.view.BoxView;
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
public class BoxController {

    @Autowired
    BoxDAO boxDAO;
    @JsonView(BoxView.class)
    @GetMapping("/box")

    public List<Box> liste() {

        return boxDAO.findAll();

    }

    @PostMapping("/box")
    public ResponseEntity<Map<String, Object>> inscription (@RequestBody Box nouveauBox) throws MessagingException {


        boxDAO.save(nouveauBox);

        return new ResponseEntity<>(Map.of("message","Box ajouter créé"), HttpStatus.CREATED);

        // Envoyez des notifications par email à tous les utilisateurs



    }



}