package ru.otus.example.grooming.gsclient.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.example.grooming.gsclient.configuration.Constants;
import ru.otus.example.grooming.gsclient.model.dto.ClientDto;
import ru.otus.example.grooming.gsclient.model.dto.PetDto;
import ru.otus.example.grooming.gsclient.model.dto.ServiceDto;
import ru.otus.example.grooming.gsclient.model.dto.UserDto;
import ru.otus.example.grooming.gsclient.services.UserControllerService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/grooming/client")
public class UserController {

    private final UserControllerService userControllerService;

    public UserController(UserControllerService userControllerService) {
        this.userControllerService = userControllerService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Void> createClientUser(@RequestBody UserDto userDto,
                                                 HttpServletRequest httpServletRequest,
                                                 HttpServletResponse httpServletResponse) throws URISyntaxException {

        HttpHeaders httpHeaders = addHeaders(httpServletResponse, "CreateClient");
        httpHeaders.add("Authorization", httpServletRequest.getHeader("Authorization"));
        userControllerService.createClientUser(userDto, httpHeaders);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "/update",
        consumes = "application/json; charset = UTF-8",
        produces = "application/json; charset = UTF-8")
    public ResponseEntity<Void> updateClient(@RequestBody ClientDto clientDto,
                                             HttpServletRequest httpServletRequest,
                                             HttpServletResponse httpServletResponse) {

        HttpHeaders httpHeaders = addHeaders(httpServletResponse, "UpdateClient");
        httpHeaders.add("Authorization", httpServletRequest.getHeader("Authorization"));
        userControllerService.updateClient(clientDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{clientId}/pet/create",
            consumes = "application/json; charset = UTF-8",
            produces = "application/json; charset = UTF-8")
    public ResponseEntity<Void> createPet(@RequestBody PetDto petDto,
                                          HttpServletRequest httpServletRequest,
                                          HttpServletResponse httpServletResponse) {

        HttpHeaders httpHeaders = addHeaders(httpServletResponse, "UpdateClient");
        httpHeaders.add("Authorization", httpServletRequest.getHeader("Authorization"));

        userControllerService.createPet(petDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/list/services",
            consumes = "application/json; charset = UTF-8",
            produces = "application/json; charset = UTF-8")
    @ResponseBody
    public ResponseEntity<List<ServiceDto>> getServiceList(@RequestParam(name = "petKind", required = false) String petKindName,
                                                           HttpServletResponse httpServletResponse) {
        HttpHeaders headers = addHeaders(httpServletResponse, "GetServiceList");
        List<ServiceDto> serviceDtos = userControllerService.getServiceList(petKindName);

        return new ResponseEntity<>(serviceDtos, HttpStatus.OK);
    }

    private HttpHeaders addHeaders(HttpServletResponse response, String actionName) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Transact-Id", UUID.randomUUID().toString());
        headers.add("Source-App", Constants.APPLICATION_USER_TYPE);
        headers.add("Action", actionName);
        headers.forEach((k, v) -> response.addHeader(k, String.join(";", v)));
        return headers;
    }

}
