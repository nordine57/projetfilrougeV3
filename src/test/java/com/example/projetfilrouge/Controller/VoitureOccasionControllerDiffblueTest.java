package com.example.projetfilrouge.Controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.projetfilrouge.DAO.GarageDAO;
import com.example.projetfilrouge.DAO.UtilisateurDao;
import com.example.projetfilrouge.DAO.VoitureOccasionDao;
import com.example.projetfilrouge.Model.Garage;
import com.example.projetfilrouge.Model.VoitureOccasion;
import com.example.projetfilrouge.Service.EmailService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {VoitureOccasionController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class VoitureOccasionControllerDiffblueTest {
    @MockBean
    private EmailService emailService;

    @MockBean
    private GarageDAO garageDAO;

    @MockBean
    private UtilisateurDao utilisateurDao;

    @Autowired
    private VoitureOccasionController voitureOccasionController;

    @MockBean
    private VoitureOccasionDao voitureOccasionDao;

    /**
     * Method under test: {@link VoitureOccasionController#delete(long)}
     */
    @Test
    void testDelete() throws Exception {
        // Arrange
        Garage garage = new Garage();
        garage.setIdGarage(1);
        garage.setNomGarage("Nom Garage");
        garage.setReservations(new ArrayList<>());
        garage.setVille("Ville");

        VoitureOccasion voitureOccasion = new VoitureOccasion();
        voitureOccasion.setDateArriver(LocalDate.of(1970, 1, 1));
        voitureOccasion.setDescription("The characteristics of someone or something");
        voitureOccasion.setGarage(garage);
        voitureOccasion.setId(1L);
        voitureOccasion.setImage("Image");
        voitureOccasion.setLienurl("https://example.org/example");
        voitureOccasion.setNomVoitureOccasion("Nom Voiture Occasion");
        voitureOccasion.setPrixVoitureOccasion(1);
        Optional<VoitureOccasion> ofResult = Optional.of(voitureOccasion);
        doNothing().when(voitureOccasionDao).deleteById(Mockito.<Long>any());
        when(voitureOccasionDao.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/voitureoccasion/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(voitureOccasionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"nomVoitureOccasion\":\"Nom Voiture Occasion\",\"prixVoitureOccasion\":1,\"dateArriver\":[1970,1"
                                        + ",1],\"description\":\"The characteristics of someone or something\",\"image\":\"Image\",\"lienurl\":\"https"
                                        + "://example.org/example\",\"garage\":{\"idGarage\":1,\"nomGarage\":\"Nom Garage\",\"ville\":\"Ville\",\"reservations"
                                        + "\":[]}}"));
    }

    /**
     * Method under test: {@link VoitureOccasionController#delete(long)}
     */
    @Test
    void testDelete2() throws Exception {
        // Arrange
        doNothing().when(voitureOccasionDao).deleteById(Mockito.<Long>any());
        Optional<VoitureOccasion> emptyResult = Optional.empty();
        when(voitureOccasionDao.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/voitureoccasion/{id}", 1L);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(voitureOccasionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test:
     * {@link VoitureOccasionController#inscription(VoitureOccasion)}
     */
    @Test
    void testInscription() throws Exception {
        // Arrange
        doNothing().when(emailService).sendNotificationToAllUsers(Mockito.<String>any(), Mockito.<String>any());

        Garage garage = new Garage();
        garage.setIdGarage(1);
        garage.setNomGarage("Nom Garage");
        garage.setReservations(new ArrayList<>());
        garage.setVille("Ville");
        Optional<Garage> ofResult = Optional.of(garage);
        when(garageDAO.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Garage garage2 = new Garage();
        garage2.setIdGarage(1);
        garage2.setNomGarage("Nom Garage");
        garage2.setReservations(new ArrayList<>());
        garage2.setVille("Ville");

        VoitureOccasion voitureOccasion = new VoitureOccasion();
        voitureOccasion.setDateArriver(LocalDate.of(1970, 1, 1));
        voitureOccasion.setDescription("The characteristics of someone or something");
        voitureOccasion.setGarage(garage2);
        voitureOccasion.setId(1L);
        voitureOccasion.setImage("Image");
        voitureOccasion.setLienurl("https://example.org/example");
        voitureOccasion.setNomVoitureOccasion("Nom Voiture Occasion");
        voitureOccasion.setPrixVoitureOccasion(1);
        when(voitureOccasionDao.save(Mockito.<VoitureOccasion>any())).thenReturn(voitureOccasion);

        Garage garage3 = new Garage();
        garage3.setIdGarage(1);
        garage3.setNomGarage("Nom Garage");
        garage3.setReservations(new ArrayList<>());
        garage3.setVille("Ville");

        VoitureOccasion voitureOccasion2 = new VoitureOccasion();
        voitureOccasion2.setDateArriver(null);
        voitureOccasion2.setDescription("The characteristics of someone or something");
        voitureOccasion2.setGarage(garage3);
        voitureOccasion2.setId(1L);
        voitureOccasion2.setImage("Image");
        voitureOccasion2.setLienurl("https://example.org/example");
        voitureOccasion2.setNomVoitureOccasion("Nom Voiture Occasion");
        voitureOccasion2.setPrixVoitureOccasion(1);
        String content = (new ObjectMapper()).writeValueAsString(voitureOccasion2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/voitureoccasion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(voitureOccasionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"voiture ajouter crÃ©Ã©\"}"));
    }

    /**
     * Method under test:
     * {@link VoitureOccasionController#inscription(VoitureOccasion)}
     */
    @Test
    void testInscription2() throws Exception {
        // Arrange
        doNothing().when(emailService).sendNotificationToAllUsers(Mockito.<String>any(), Mockito.<String>any());
        Optional<Garage> emptyResult = Optional.empty();
        when(garageDAO.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

        Garage garage = new Garage();
        garage.setIdGarage(1);
        garage.setNomGarage("Nom Garage");
        garage.setReservations(new ArrayList<>());
        garage.setVille("Ville");

        VoitureOccasion voitureOccasion = new VoitureOccasion();
        voitureOccasion.setDateArriver(LocalDate.of(1970, 1, 1));
        voitureOccasion.setDescription("The characteristics of someone or something");
        voitureOccasion.setGarage(garage);
        voitureOccasion.setId(1L);
        voitureOccasion.setImage("Image");
        voitureOccasion.setLienurl("https://example.org/example");
        voitureOccasion.setNomVoitureOccasion("Nom Voiture Occasion");
        voitureOccasion.setPrixVoitureOccasion(1);
        when(voitureOccasionDao.save(Mockito.<VoitureOccasion>any())).thenReturn(voitureOccasion);

        Garage garage2 = new Garage();
        garage2.setIdGarage(1);
        garage2.setNomGarage("Nom Garage");
        garage2.setReservations(new ArrayList<>());
        garage2.setVille("Ville");

        VoitureOccasion voitureOccasion2 = new VoitureOccasion();
        voitureOccasion2.setDateArriver(null);
        voitureOccasion2.setDescription("The characteristics of someone or something");
        voitureOccasion2.setGarage(garage2);
        voitureOccasion2.setId(1L);
        voitureOccasion2.setImage("Image");
        voitureOccasion2.setLienurl("https://example.org/example");
        voitureOccasion2.setNomVoitureOccasion("Nom Voiture Occasion");
        voitureOccasion2.setPrixVoitureOccasion(1);
        String content = (new ObjectMapper()).writeValueAsString(voitureOccasion2);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/voitureoccasion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(voitureOccasionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"error\":\"Le garage n'existe pas.\"}"));
    }

    /**
     * Method under test: {@link VoitureOccasionController#liste()}
     */
    @Test
    void testListe() throws Exception {
        // Arrange
        when(voitureOccasionDao.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/voitureoccasion/liste");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(voitureOccasionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
