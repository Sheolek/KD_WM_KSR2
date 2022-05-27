package com.example.KSR2.logic.service;

import com.example.KSR2.logic.model.House;
import com.example.KSR2.logic.repository.HouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HouseService {

    private HouseRepository houseRepository;

    public List<House> getHouses() {
        return houseRepository.findAll();
    }
}
