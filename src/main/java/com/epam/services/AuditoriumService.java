package com.epam.services;

import com.epam.domain.Auditorium;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class AuditoriumService {

    private List<Auditorium> auditorias = new ArrayList<>();

    @PostConstruct
    private void init() throws IOException, URISyntaxException {
        File[] auditories = new File(AuditoriumService.class.getClassLoader().getResource("auditories").toURI()).listFiles();
        for (File auditoryFile : auditories) {
            Properties properties = new Properties();
            properties.load(new FileInputStream(auditoryFile));

            Auditorium auditorium = new Auditorium();
            auditorium.setName(properties.getProperty("auditorium.name"));
            auditorium.setSeats(Integer.valueOf(properties.getProperty("auditorium.seats")));
            auditorium.setVipSeats(Integer.valueOf(properties.getProperty("auditorium.vipSeats")));

            auditorias.add(auditorium);
        }
    }

    public List<Auditorium> getAuditoriums() {
        return auditorias;
    }

    public int getSeatsNumber() {
        int seats = 0;
        for (Auditorium auditorium : auditorias) {
            seats = +auditorium.getSeats();
        }
        return seats;
    }

    public int getVipSeats() {
        int vipSeats = 0;
        for (Auditorium auditorium : auditorias) {
            vipSeats = +auditorium.getVipSeats();
        }
        return vipSeats;
    }
}
