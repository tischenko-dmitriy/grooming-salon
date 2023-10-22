package ru.otus.example.grooming.gsclient.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.otus.example.grooming.gsclient.services.GeneralControllerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class GeneralControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GeneralControllerService generalControllerService;

    @Spy
    @InjectMocks
    private GeneralController generalController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(generalController)
                .defaultRequest(get("/"))
                .build();

    }

    @Test
    @WithMockUser(authorities = {"user:write"})
    public void doTestApplication() throws Exception {
        //given
        String uri = "/grooming/client/test";
        String appName = "{\"programName\":\"gs-client\"}";

        when(generalControllerService.testApplication())
                .thenReturn(appName);

        //when
        MvcResult result = this.mockMvc.perform(get(uri))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertThat(result).isNotNull();
        assertThat(HttpStatus.OK.value()).isEqualTo(result.getResponse().getStatus());
    }

}