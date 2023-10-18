package ru.otus.example.grooming.gsclient.controllers;

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

import ru.otus.example.grooming.gsclient.services.UserControllerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserControllerService userControllerService;

    @Spy
    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .defaultRequest(post("/grooming/client/user"))
                .build();

    }

    @Test
    public void testCreateRole() throws Exception {
        //given
        String uri = "/role/create";
        String roleName = "Client";

        doNothing().when(userControllerService).createUserRole(roleName);

        //when
        MvcResult result = this.mockMvc.perform(post(uri, roleName)
                        .param("userRoleName", roleName))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        //then
        assertThat(result).isNotNull();
        assertThat(result.getRequest().getHeader("X-Real-Host")).isNotNull();
        assertThat(HttpStatus.OK.value()).isEqualTo(result.getResponse().getStatus());

    }

}
