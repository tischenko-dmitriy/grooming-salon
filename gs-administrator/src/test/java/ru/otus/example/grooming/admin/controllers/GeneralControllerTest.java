package ru.otus.example.grooming.admin.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.otus.example.grooming.gsadmin.controllers.GeneralController;
import ru.otus.example.grooming.gsadmin.services.GeneralControllerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
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
    public void doTestApplication() throws Exception {
        //given
        String uri = "/grooming/admin/test";
        String appName = "{\"programName\":\"gs-admin\"}";

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