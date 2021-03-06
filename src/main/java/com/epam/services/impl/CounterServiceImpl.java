package com.epam.services.impl;

import com.epam.dao.CounterDao;
import com.epam.domain.Event;
import com.epam.services.CounterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Olga Bogutska on 26.02.2016.
 */
@Service
public class CounterServiceImpl implements CounterService {
    @Autowired
    private CounterDao dao;

    public void updateCountForEventByName(Event event) {
        if (checkIfRecordByNameExist(event)) {
            dao.updateCounterByName(event);
        } else {
            dao.createCounterByName(event);
        }
    }

    public void updateCountForEventByPrice(Event event) {
        if (checkIfRecordByPriceExist(event)) {
            dao.updateCounterByPrice(event);
        } else {
            dao.createCounterByPrice(event);
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
