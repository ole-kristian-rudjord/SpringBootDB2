package com.example.springbootdb2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KundeRepository {

    @Autowired
    private JdbcTemplate db;

    Logger logger = LoggerFactory.getLogger(KundeRepository.class);

    public boolean registrerKunde(Kunde kunde) {
        String sql = "INSERT INTO Kunde (navn, adresse) VALUES(?,?)";
        try {
            db.update(sql, kunde.getNavn(), kunde.getAdresse());
            return true;
        }
        catch (Exception e) {
            logger.error("Feil i hentAlleKunde: " + e);
            return false;
        }
    }

    public List<Kunde> hentAlleKunder() {
        String sql = "SELECT * FROM Kunde";
        try {
            List<Kunde> alleKunder = db.query(sql, new BeanPropertyRowMapper(Kunde.class));
            return alleKunder;
        }
        catch (Exception e) {
            logger.error("Feil i hentAlleKunde: " + e);
            return null;
        }
    }

    public boolean slettAlleKunder() {
        String sql = "DELETE from Kunde";
        try {
            db.update(sql);
            return true;
        }
        catch (Exception e) {
            logger.error("Feil i hentAlleKunde: " + e);
            return false;
        }
    }

    public Kunde hentEnKunde(int id) {
        String sql = "SELECT * FROM Kunde WHERE id=?";
        try {
            Kunde enKunde = db.queryForObject(sql, BeanPropertyRowMapper.newInstance(Kunde.class), id);
            return enKunde;
        }
        catch (Exception e) {
            logger.error("Feil i hentEnKunde: " + e);
            return null;
        }
    }

    public boolean endreEnKunde(Kunde innKunde) {
        String sql =
                "UPDATE Kunde" +
                " SET navn = '" + innKunde.getNavn() +
                    "', adresse = '" + innKunde.getAdresse() +
                "' WHERE id = " + innKunde.getId();
        try {
            db.update(sql);
            return true;
        }
        catch (Exception e) {
            logger.error("Feil i endreEnKunde: " + e);
            return false;
        }
    }

    public boolean slettEnKunde(int id) {
        String sql = "DELETE FROM Kunde WHERE id = " + id;
        try {
            db.update(sql);
            return true;
        }
        catch (Exception e) {
            logger.error("Feil i slettEnKunde: " + e);
            return false;
        }
    }
}
