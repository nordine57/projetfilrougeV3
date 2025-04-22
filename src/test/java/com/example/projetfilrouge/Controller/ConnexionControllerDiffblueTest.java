package com.example.projetfilrouge.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.projetfilrouge.DAO.GarageDAO;
import com.example.projetfilrouge.DAO.RoleDao;
import com.example.projetfilrouge.DAO.UtilisateurDao;
import com.example.projetfilrouge.Model.Garage;
import com.example.projetfilrouge.Model.Role;
import com.example.projetfilrouge.Model.Utilisateur;
import com.example.projetfilrouge.Service.EmailService;
import com.example.projetfilrouge.securiter.AppUserDetails;
import com.example.projetfilrouge.securiter.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ConnexionController.class, BCryptPasswordEncoder.class, AuthenticationProvider.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ConnexionControllerDiffblueTest {
    @MockBean
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private ConnexionController connexionController;

    @MockBean
    private EmailService emailService;

    @MockBean
    private GarageDAO garageDAO;

    @MockBean
    private JwtUtils jwtUtils;

    @MockBean
    private RoleDao roleDao;

    @MockBean
    private UtilisateurDao utilisateurDao;

    /**
     * Method under test: {@link ConnexionController#connexion(Utilisateur)}
     */
    @Test
    void testConnexion() throws Exception {
        // Arrange
        when(jwtUtils.generateToken(Mockito.<UserDetails>any())).thenReturn("ABC123");
        when(authenticationProvider.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken(new User("janedoe", "iloveyou", new ArrayList<>()), "Credentials"));

        Garage garage = new Garage();
        garage.setIdGarage(1);
        garage.setNomGarage("Nom Garage");
        garage.setReservations(new ArrayList<>());
        garage.setVille("Ville");

        Role role = new Role();
        role.setId(1);
        role.setNom("Nom");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setExecutantreservation(new ArrayList<>());
        utilisateur.setGarage(garage);
        utilisateur.setIdUser(1);
        utilisateur.setMotDePasse("Mot De Passe");
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prenom");
        utilisateur.setReservations(new ArrayList<>());
        utilisateur.setRole(role);
        utilisateur.setVoiture(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(utilisateur);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/connexion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(connexionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"jwt\":\"ABC123\"}"));
    }

    /**
     * Method under test: {@link ConnexionController#profil(AppUserDetails)}
     */
    @Test
    void testProfil() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ConnexionController connexionController = new ConnexionController();
        Utilisateur utilisateur = new Utilisateur();

        // Act
        ResponseEntity<Utilisateur> actualProfilResult = connexionController.profil(new AppUserDetails(utilisateur));

        // Assert
        HttpStatusCode statusCode = actualProfilResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals(200, actualProfilResult.getStatusCodeValue());
        assertEquals(HttpStatus.OK, statusCode);
        assertTrue(actualProfilResult.hasBody());
        assertTrue(actualProfilResult.getHeaders().isEmpty());
        assertSame(utilisateur, actualProfilResult.getBody());
    }

    /**
     * Method under test: {@link ConnexionController#profil(AppUserDetails)}
     */
    @Test
    void testProfil2() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ConnexionController connexionController = new ConnexionController();

        // Act
        ResponseEntity<Utilisateur> actualProfilResult = connexionController
                .profil(new AppUserDetails(mock(Utilisateur.class)));

        // Assert
        HttpStatusCode statusCode = actualProfilResult.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals(200, actualProfilResult.getStatusCodeValue());
        assertEquals(HttpStatus.OK, statusCode);
        assertTrue(actualProfilResult.hasBody());
        assertTrue(actualProfilResult.getHeaders().isEmpty());
    }

    /**
     * Method under test: {@link ConnexionController#connexion(Utilisateur)}
     */
    @Test
    void testConnexion2() throws Exception {
        // Arrange
        when(jwtUtils.generateToken(Mockito.<UserDetails>any())).thenThrow(new AccountExpiredException("jwt"));
        when(authenticationProvider.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken(new User("janedoe", "iloveyou", new ArrayList<>()), "Credentials"));

        Garage garage = new Garage();
        garage.setIdGarage(1);
        garage.setNomGarage("Nom Garage");
        garage.setReservations(new ArrayList<>());
        garage.setVille("Ville");

        Role role = new Role();
        role.setId(1);
        role.setNom("Nom");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setExecutantreservation(new ArrayList<>());
        utilisateur.setGarage(garage);
        utilisateur.setIdUser(1);
        utilisateur.setMotDePasse("Mot De Passe");
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prenom");
        utilisateur.setReservations(new ArrayList<>());
        utilisateur.setRole(role);
        utilisateur.setVoiture(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(utilisateur);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/connexion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(connexionController)
                .build()
                .perform(requestBuilder);

        // Assert
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    /**
     * Method under test: {@link ConnexionController#inscription(Utilisateur)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInscription() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@7b26a726 testClass = com.example.projetfilrouge.Controller.DiffblueFakeClass239, locations = [], classes = [com.example.projetfilrouge.Controller.ConnexionController, org.springframework.security.authentication.AuthenticationProvider, org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@4781e839, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7e950670, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@58789ce0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@6019ad7a], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        Garage garage = new Garage();
        garage.setIdGarage(1);
        garage.setNomGarage("Nom Garage");
        garage.setReservations(new ArrayList<>());
        garage.setVille("Ville");

        Role role = new Role();
        role.setId(1);
        role.setNom("Nom");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setExecutantreservation(new ArrayList<>());
        utilisateur.setGarage(garage);
        utilisateur.setIdUser(1);
        utilisateur.setMotDePasse("Mot De Passe");
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prenom");
        utilisateur.setReservations(new ArrayList<>());
        utilisateur.setRole(role);
        utilisateur.setVoiture(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(utilisateur);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/inscription")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(connexionController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ConnexionController#inscriptionAdmin(Utilisateur)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testInscriptionAdmin() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@1df4be62 testClass = com.example.projetfilrouge.Controller.DiffblueFakeClass530, locations = [], classes = [com.example.projetfilrouge.Controller.ConnexionController, org.springframework.security.authentication.AuthenticationProvider, org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@4781e839, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7e950670, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@58789ce0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@6019ad7a], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        Garage garage = new Garage();
        garage.setIdGarage(1);
        garage.setNomGarage("Nom Garage");
        garage.setReservations(new ArrayList<>());
        garage.setVille("Ville");

        Role role = new Role();
        role.setId(1);
        role.setNom("Nom");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail("jane.doe@example.org");
        utilisateur.setExecutantreservation(new ArrayList<>());
        utilisateur.setGarage(garage);
        utilisateur.setIdUser(1);
        utilisateur.setMotDePasse("Mot De Passe");
        utilisateur.setNom("Nom");
        utilisateur.setPrenom("Prenom");
        utilisateur.setReservations(new ArrayList<>());
        utilisateur.setRole(role);
        utilisateur.setVoiture(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(utilisateur);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/inscriptionadmin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act
        MockMvcBuilders.standaloneSetup(connexionController).build().perform(requestBuilder);
    }

    /**
     * Method under test: {@link ConnexionController#profil(AppUserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testProfil3() throws Exception {
        // TODO: Diffblue Cover was only able to create a partial test for this method:
        //   Reason: Failed to create Spring context.
        //   Attempt to initialize test context failed with
        //   java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded: skipping repeated attempt to load context for [MergedContextConfiguration@2e580f7f testClass = com.example.projetfilrouge.Controller.DiffblueFakeClass590, locations = [], classes = [com.example.projetfilrouge.Controller.ConnexionController, org.springframework.security.authentication.AuthenticationProvider, org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder], contextInitializerClasses = [], activeProfiles = [], propertySourceDescriptors = [], propertySourceProperties = [], contextCustomizers = [org.springframework.boot.test.context.filter.ExcludeFilterContextCustomizer@4781e839, org.springframework.boot.test.json.DuplicateJsonObjectContextCustomizerFactory$DuplicateJsonObjectContextCustomizer@7e950670, org.springframework.boot.test.mock.mockito.MockitoContextCustomizer@58789ce0, org.springframework.boot.test.autoconfigure.actuate.observability.ObservabilityContextCustomizerFactory$DisableObservabilityContextCustomizer@1f, org.springframework.boot.test.autoconfigure.properties.PropertyMappingContextCustomizer@0, org.springframework.boot.test.autoconfigure.web.servlet.WebDriverContextCustomizer@6019ad7a], contextLoader = org.springframework.test.context.support.DelegatingSmartContextLoader, parent = null]
        //       at org.springframework.test.context.cache.DefaultCacheAwareContextLoaderDelegate.loadContext(DefaultCacheAwareContextLoaderDelegate.java:145)
        //       at org.springframework.test.context.support.DefaultTestContext.getApplicationContext(DefaultTestContext.java:130)
        //   See https://diff.blue/R026 to resolve this issue.

        // Arrange
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/profil");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("userDetails",
                String.valueOf(new AppUserDetails(new Utilisateur())));

        // Act
        MockMvcBuilders.standaloneSetup(connexionController).build().perform(requestBuilder);
    }
}
