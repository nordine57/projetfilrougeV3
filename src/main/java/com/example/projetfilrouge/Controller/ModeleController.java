package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.MarqueDAO;
import com.example.projetfilrouge.DAO.ModeleDAO;
import com.example.projetfilrouge.Model.MarqueVoiture;
import com.example.projetfilrouge.Model.ModeleVoiture;
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
public class ModeleController {

    @Autowired
    ModeleDAO modelevoitureDAO;

    @GetMapping("/modelevoiture")
    public List<ModeleVoiture> liste() {

        return modelevoitureDAO.findAll();

    }

    @GetMapping("/modelevoiture/{id}")
    public ResponseEntity<ModeleVoiture> get(@PathVariable long id) {

        Optional<ModeleVoiture> ModeleVoitureOptional = modelevoitureDAO.findById(id);

        if(ModeleVoitureOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ModeleVoitureOptional.get(),HttpStatus.OK);
    }

}