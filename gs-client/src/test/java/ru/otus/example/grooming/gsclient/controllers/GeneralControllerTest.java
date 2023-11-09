package ru.otus.example.grooming.gsclient.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.otus.example.grooming.gsclient.configuration.security.ApiSecurityConfiguration;
import ru.otus.example.grooming.gsclient.repositories.UserRepository;
import ru.otus.example.grooming.gsclient.services.GeneralControllerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(locations="file:src/test/resources/gs-client.test.properties")
public class GeneralControllerTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Autowired
    GeneralController generalController;

    @MockBean
    GeneralControllerService generalControllerService;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(generalController)
                .defaultRequest(get("/"))
                .addFilters(springSecurityFilterChain)
                .dispatchOptions(true)
                .build();
    }

    @Test
    @DisplayName("Checking application context.")
    public void testContextLoads() {
        assertThat(generalController).isNotNull();
        assertThat(generalControllerService).isNotNull();
        assertThat(applicationContext.getBean("apiSecurityConfiguration")).isInstanceOf(ApiSecurityConfiguration.class);
    }

    @Test
    @DisplayName("HTTP request \"get /grooming/client/test\" (application accessibility)")
    public void doTestApplication() throws Exception {
        //given
        String uri = "/grooming/client/test";
        String appName = "{\"programName\":\"gs-client\"}";

        when(generalControllerService.testApplication())
                .thenReturn(appName);

        //when
        MvcResult result = this.mockMvc.perform(get(uri)
                        .with(SecurityMockMvcRequestPostProcessors.user("user1")))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertThat(result).isNotNull();
        assertThat(HttpStatus.OK.value()).isEqualTo(result.getResponse().getStatus());
    }

}