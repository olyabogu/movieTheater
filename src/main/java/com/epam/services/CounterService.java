package com.epam.services;

import com.epam.dao.impl.CounterDao;
import com.epam.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@Service
public class CounterService {
    @Autowired
    private CounterDao dao;

    public void updateCountForEventByName(Event event) {
        if (checkIfRecordByNameExist(event)) {
            dao.updateByName(event);
        } else {
            dao.saveByName(event);
        }
    }

    public void updateCountForEventByPrice(Event event) {
        if (checkIfRecordByPriceExist(event)) {
            dao.updateByPrice(event);
        } else {
            dao.saveByPrice(event);
        }
    }

    public int getCounterByName(Event event) {
        return dao.getCounterByName(event);
    }

    public int getCounterByPrice(Event event) {
        return dao.getCounterByPrice(event);
    }

    private boolean checkIfRecordByNameExist(Event event) {
        Integer count = dao.selectCountByName(event);
        return count > 0;
    }

    private boolean checkIfRecordByPriceExist(Event event) {
        Integer count = dao.selectCountByPrice(event);
        return count > 0;
    }
}
