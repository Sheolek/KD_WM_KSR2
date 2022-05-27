package com.example.KSR2.logic.service;

import com.example.KSR2.logic.repository.SummarizerRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
public class SummarizerService {
    SummarizerRepository summarizerRepository;


}
