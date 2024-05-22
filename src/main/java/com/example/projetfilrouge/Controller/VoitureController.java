package com.example.projetfilrouge.Controller;

import com.example.projetfilrouge.DAO.VoitureDAO;
import com.example.projetfilrouge.Model.Role;
import com.example.projetfilrouge.Model.Utilisateur;
import com.example.projetfilrouge.Model.Voiture;
import com.example.projetfilrouge.securiter.AppUserDetails;
import com.example.projetfilrouge.securiter.IsAdmin;
import com.example.projetfilrouge.view.VoitureView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class VoitureController {
    @Autowired
    VoitureDAO voitureDao;

    @GetMapping("/voiture/liste")
    public List<Voiture> liste() {

        return voitureDao.findAll();

    }

    @JsonView({VoitureView.class})
    @GetMapping("/voiture/{id}")
    public ResponseEntity<Voiture> get(@PathVariable long id) {

        Optional<Voiture> voitureOptional = voitureDao.findById(id);

        if(voitureOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(voitureOptional.get(),HttpStatus.OK);
    }


    @PostMapping("/voiture")
    @JsonView(VoitureView.class)
    public ResponseEntity<Voiture> addVoiture(@RequestBody Voiture voiture, @AuthenticationPrincipal AppUserDetails user) {
        voiture.setIdutilisateur(user.getUtilisateur());
        Voiture savedVoiture = voitureDao.save(voiture);
        return new ResponseEntity<>(savedVoiture, HttpStatus.CREATED);
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

    @DeleteMapping("/voiture/{id}")
    public ResponseEntity<Voiture> delete (@PathVariable long id) {

        Optional<Voiture> voitureOccasionOptional = voitureDao.findById(id);

        if(voitureOccasionOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        voitureDao.deleteById(id);

        return new ResponseEntity<>(voitureOccasionOptional.get(),HttpStatus.OK);
    }
}
