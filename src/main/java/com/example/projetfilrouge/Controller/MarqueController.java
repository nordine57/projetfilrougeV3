package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.MarqueDAO;
import com.example.projetfilrouge.Model.MarqueVoiture;
import com.example.projetfilrouge.securiter.IsAdmin;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class MarqueController {

    @Autowired
    MarqueDAO marquevoitureDAO;

    @GetMapping("/marquevoiture")
    public List<MarqueVoiture> liste() {

        return marquevoitureDAO.findAll();

    }

    @GetMapping("/marquevoiture/{id}")
    public ResponseEntity<MarqueVoiture> get(@PathVariable long id) {

        Optional<MarqueVoiture> MarqueVoitureOptional = marquevoitureDAO.findById(id);

        if(MarqueVoitureOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(MarqueVoitureOptional.get(),HttpStatus.OK);
    }

}