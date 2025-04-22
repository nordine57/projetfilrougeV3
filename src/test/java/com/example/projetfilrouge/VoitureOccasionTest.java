
package com.example.projetfilrouge;

import com.example.projetfilrouge.Controller.VoitureOccasionController;
import com.example.projetfilrouge.DAO.GarageDAO;
import com.example.projetfilrouge.DAO.VoitureOccasionDao;
import com.example.projetfilrouge.Model.Garage;
import com.example.projetfilrouge.Model.VoitureOccasion;
import com.example.projetfilrouge.Service.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import jakarta.mail.MessagingException;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VoitureOccasionTest {

    @Mock
    private VoitureOccasionDao voitureOccasionDao;

    @Mock
    private GarageDAO garageDao;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private VoitureOccasionController voitureOccasionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInscription() throws MessagingException {
        // Préparation des données
        VoitureOccasion voitureOccasion = new VoitureOccasion();
        voitureOccasion.setNomVoitureOccasion("Peugeot 208");
        voitureOccasion.setPrixVoitureOccasion(15000);
        voitureOccasion.setDescription("Très bon état.");
        voitureOccasion.setImage("image_url");

        // Simulation du garage existant
        Garage garage = new Garage();
        garage.setIdGarage(1);
        when(garageDao.findById(1)).thenReturn(Optional.of(garage));

        // Simulation de la sauvegarde de la voiture
        when(voitureOccasionDao.save(voitureOccasion)).thenReturn(voitureOccasion);

        // Simulation de l'envoi d'email
        doNothing().when(emailService).sendNotificationToAllUsers(anyString(), anyString());

        // Appel de la méthode du contrôleur
        ResponseEntity<Map<String, Object>> response = voitureOccasionController.inscription(voitureOccasion);

        // Vérification du résultat
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("voiture ajouter créé", response.getBody().get("message"));

        // Vérification des interactions avec les mocks
        verify(voitureOccasionDao).save(voitureOccasion);
        verify(emailService).sendNotificationToAllUsers(anyString(), anyString());
    }

    @Test
    public void testInscriptionGarageNotFound() throws MessagingException {
        // Préparation des données
        VoitureOccasion voitureOccasion = new VoitureOccasion();
        voitureOccasion.setNomVoitureOccasion("Peugeot 208");
        voitureOccasion.setPrixVoitureOccasion(15000);
        voitureOccasion.setDescription("Très bon état.");
        voitureOccasion.setImage("image_url");

        // Simulation du garage non trouvé
        when(garageDao.findById((int) anyLong())).thenReturn(Optional.empty());

        // Appel de la méthode du contrôleur
        ResponseEntity<Map<String, Object>> response = voitureOccasionController.inscription(voitureOccasion);

        // Vérification du résultat
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Le garage n'existe pas.", response.getBody().get("error"));

        // Vérification que la voiture n'a pas été sauvegardée
        verify(voitureOccasionDao, never()).save(voitureOccasion);
        verify(emailService, never()).sendNotificationToAllUsers(anyString(), anyString());
    }
}
