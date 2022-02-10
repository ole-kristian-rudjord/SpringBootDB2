package com.example.springbootdb2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class KundeController {

    @Autowired
    KundeRepository rep;

    Logger logger = LoggerFactory.getLogger(KundeRepository.class);

    private boolean validerKunde(Kunde innKunde) {
        String regexNavn = "^[a-zA-ZæøåÆØÅ. \\-]{2,20}$";
        String regexAdresse = "^[0-9a-zA-zæøåÆØÅ. \\-]{2,50}$";
        boolean navnOK = innKunde.getNavn().matches(regexNavn);
        boolean adresseOK = innKunde.getAdresse().matches(regexAdresse);
        if (navnOK && adresseOK) {
            return true;
        }
        logger.error("Valideringsfeil");
        return false;
    }

    @PostMapping("/lagre")
    public void registrerKunde(Kunde innKunde, HttpServletResponse response) throws IOException {
        if (!validerKunde(innKunde)) {
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
        }
        else {
            if (!rep.registrerKunde(innKunde)) {
                response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
            }
        }
    }

    @GetMapping("/hentAlle")
    public List<Kunde> hentAlleKunder(HttpServletResponse response) throws IOException {
        if (rep.hentAlleKunder() == null) {
            response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
        return rep.hentAlleKunder();
    }

    @GetMapping("/slettAlle")
    public void slettAlleKunder(HttpServletResponse response) throws IOException {
        if (!rep.slettAlleKunder()) {
            response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
    }

    @GetMapping("/hentEnKunde")
    public Kunde hentEnKunde(int id, HttpServletResponse response) throws IOException {
        /*return rep.hentEnKunde(id);*/
        if (rep.hentEnKunde(id) == null) {
            response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
        return rep.hentEnKunde(id);
    }

    @PostMapping("/endreEnKunde")
    public void endreEnKunde(Kunde innKunde, HttpServletResponse response) throws IOException {
        if (!validerKunde(innKunde)) {
            response.sendError(HttpStatus.NOT_ACCEPTABLE.value());
        }
        else {
            if (!rep.endreEnKunde(innKunde)) {
                response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
            }
        }
    }

    @GetMapping("/slettEnKunde")
    public void slettEnKunde(int id, HttpServletResponse response) throws IOException {
        if (!rep.slettEnKunde(id)) {
            response.sendError(HttpStatus.UNPROCESSABLE_ENTITY.value());
        }
    }
}
