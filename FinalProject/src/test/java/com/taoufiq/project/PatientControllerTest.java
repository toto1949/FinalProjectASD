package com.taoufiq.project;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taoufiq.project.Controllers.PatientController;
import com.taoufiq.project.Mappers.PatientMapper;
import com.taoufiq.project.Models.Patient;
import com.taoufiq.project.Security.JWTManagementUtilityService;
import com.taoufiq.project.Security.JwtAuthenticationFilter;
import com.taoufiq.project.Security.SecurityConfig;
import com.taoufiq.project.Security.UserDetailsServiceImpl;
import com.taoufiq.project.Services.PatientService;
import com.taoufiq.project.Services.UserService;

@WebMvcTest(controllers = PatientController.class)
@Import({ SecurityConfig.class, JwtAuthenticationFilter.class })
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    // Mock required security-related beans
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserService userService;

    @MockBean
    private JWTManagementUtilityService jwtManagementUtilityService;

    @MockBean
    private PatientMapper patientMapper;

    @Autowired
    private ObjectMapper objectMapper;

    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    void setUp() {
        patient1 = new Patient();
        patient1.setId(1L);
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setEmail("john.doe@example.com");

        patient2 = new Patient();
        patient2.setId(2L);
        patient2.setFirstName("Jane");
        patient2.setLastName("Doe");
        patient2.setEmail("jane.doe@example.com");
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetAllPatients() throws Exception {
        List<Patient> patients = Arrays.asList(patient1, patient2);
        Mockito.when(patientService.findAll()).thenReturn(patients);

        mockMvc.perform(MockMvcRequestBuilders.get("/adsweb/api/v1/patients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetAllPatientsWithUserRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/adsweb/api/v1/patients")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testCreatePatient() throws Exception {
        Mockito.when(patientService.save(Mockito.any(Patient.class))).thenReturn(patient1);

        mockMvc.perform(MockMvcRequestBuilders.post("/adsweb/api/v1/patients")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient1)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testCreatePatientWithUserRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/adsweb/api/v1/patients")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient1)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testGetPatientById() throws Exception {
        Mockito.when(patientService.findById(1L)).thenReturn(patient1);

        mockMvc.perform(MockMvcRequestBuilders.get("/adsweb/api/v1/patients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testGetPatientByIdWithUserRole() throws Exception {
        Mockito.when(patientService.findById(1L)).thenReturn(patient1);

        mockMvc.perform(MockMvcRequestBuilders.get("/adsweb/api/v1/patients/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testUpdatePatient() throws Exception {
        Mockito.when(patientService.save(Mockito.any(Patient.class))).thenReturn(patient1);

        mockMvc.perform(MockMvcRequestBuilders.put("/adsweb/api/v1/patients/patient/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient1)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testUpdatePatientWithUserRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/adsweb/api/v1/patients/patient/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(patient1)))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void testDeletePatient() throws Exception {
        Mockito.doNothing().when(patientService).deleteById(1L);

        mockMvc.perform(MockMvcRequestBuilders.delete("/adsweb/api/v1/patients/patient/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = "USER")
    void testDeletePatientWithUserRole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/adsweb/api/v1/patients/patient/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    // @Test
    // void testNoAuthentication() throws Exception {
    // mockMvc.perform(MockMvcRequestBuilders.get("/adsweb/api/v1/patients"))
    // .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    // }
}