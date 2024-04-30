package com.example.projetfilrouge.Controller;


import com.example.projetfilrouge.DAO.VoitureOccasionDao;
import com.example.projetfilrouge.Model.VoitureOccasion;
import com.example.projetfilrouge.securiter.IsAdmin;
import com.example.projetfilrouge.securiter.IsUser;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class VoitureOccasionController {

    @Autowired
    VoitureOccasionDao voitureOccasionDao;

    @GetMapping("/voitureoccasion/liste")
    public List<VoitureOccasion> liste() {

        return voitureOccasionDao.findAll();

    }

    @GetMapping("/voitureoccasion/{id}")
    public ResponseEntity<VoitureOccasion> get(@PathVariable long id) {

        Optional<VoitureOccasion> voitureOccasionOptional = voitureOccasionDao.findById(id);

        if(voitureOccasionOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(voitureOccasionOptional.get(),HttpStatus.OK);
    }


    @PostMapping("/voitureoccasion")
    @IsAdmin
    public ResponseEntity<VoitureOccasion> add(@Valid @RequestBody VoitureOccasion nouveauVoitureOccasion) {

        //C'est une mise à jour
        if(nouveauVoitureOccasion.getId() != null) {

            Optional<VoitureOccasion> voitureOccasionOptional = voitureOccasionDao.findById(nouveauVoitureOccasion.getId());

            //l'utilisateur tente de modifier un voitureOccasion qui n'existe pas/plus
            if(voitureOccasionOptional.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            voitureOccasionDao.save(nouveauVoitureOccasion);

            return new ResponseEntity<>(voitureOccasionOptional.get(), HttpStatus.OK);
        }

        voitureOccasionDao.save(nouveauVoitureOccasion);

        return new ResponseEntity<>(nouveauVoitureOccasion, HttpStatus.CREATED);
    }

//    @PostMapping("/voitureOccasion")
//    public ResponseEntity<VoitureOccasion> add (@RequestBody VoitureOccasion nouveauVoitureOccasion) {
//        nouveauVoitureOccasion.setId(null);
//        voitureOccasionDao.save(nouveauVoitureOccasion);
//        return new ResponseEntity<>(nouveauVoitureOccasion, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/voitureOccasion/{id}")
//    public ResponseEntity<VoitureOccasion> update (@RequestBody VoitureOccasion voitureOccasionAmodifier, @PathVariable int id) {
//        voitureOccasionAmodifier.setId(id);
//
//        Optional<VoitureOccasion> voitureOccasionOptional = voitureOccasionDao.findById(voitureOccasionAmodifier.getId());
//
//        //l'utilisateur tente de modifier un voitureOccasion qui n'existe pas/plus
//        if(voitureOccasionOptional.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//        voitureOccasionDao.save(voitureOccasionAmodifier);
//        return new ResponseEntity<>(voitureOccasionOptional.get(), HttpStatus.OK);
//    }

    @DeleteMapping("/voitureoccasion/{id}")
    public ResponseEntity<VoitureOccasion> delete (@PathVariable long id) {

        Optional<VoitureOccasion> voitureOccasionOptional = voitureOccasionDao.findById(id);

        if(voitureOccasionOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        voitureOccasionDao.deleteById(id);

        return new ResponseEntity<>(voitureOccasionOptional.get(),HttpStatus.OK);
    }
}