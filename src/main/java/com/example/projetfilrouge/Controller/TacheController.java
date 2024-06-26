package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.MarqueDAO;
import com.example.projetfilrouge.DAO.TacheDAO;
import com.example.projetfilrouge.Model.MarqueVoiture;
import com.example.projetfilrouge.Model.Tache;
import com.example.projetfilrouge.Model.VoitureOccasion;
import com.example.projetfilrouge.view.TacheView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @PostMapping("/tache")
    public ResponseEntity<Map<String, Object>> inscription (@RequestBody Tache nouveauTache) throws MessagingException {


        tacheDAO.save(nouveauTache);

        return new ResponseEntity<>(Map.of("message","Tache ajouter créé"), HttpStatus.CREATED);

        // Envoyez des notifications par email à tous les utilisateurs



    }



}