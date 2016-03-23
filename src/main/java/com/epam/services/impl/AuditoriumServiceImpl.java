package com.epam.services.impl;

import com.epam.domain.Auditorium;
import com.epam.services.AuditoriumService;

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
public class AuditoriumServiceImpl implements AuditoriumService {

    private List<Auditorium> auditoriums = new ArrayList<>();

    @PostConstruct
    private void init() throws IOException, URISyntaxException {
        File[] auditories = new File(AuditoriumServiceImpl.class.getClassLoader().getResource("auditories").toURI()).listFiles();
        for (File auditoryFile : auditories) {
            Properties properties = new Properties();
            properties.load(new FileInputStream(auditoryFile));

            Auditorium auditorium = new Auditorium();
            auditorium.setName(properties.getProperty("auditorium.name"));
            auditorium.setSeats(Integer.valueOf(properties.getProperty("auditorium.seats")));
            auditorium.setVipSeats(Integer.valueOf(properties.getProperty("auditorium.vipSeats")));

	        auditoriums.add(auditorium);
        }
    }

    @Override
    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    @Override
    public int getSeatsNumber() {
        int seats = 0;
        for (Auditorium auditorium : auditoriums) {
            seats = +auditorium.getSeats();
        }
        return seats;
    }

    @Override
    public int getVipSeats() {
        int vipSeats = 0;
        for (Auditorium auditorium : auditoriums) {
            vipSeats = +auditorium.getVipSeats();
        }
        return vipSeats;
    }
}
