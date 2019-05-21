package com.example.SimulacroParcial.controller;

import com.example.SimulacroParcial.domain.Candidato;
import com.example.SimulacroParcial.repository.CandidatoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("candidatos")
public class CandidatoControlador {

    @Autowired
    private CandidatoJpaRepository candidatosJpaRepository;


    @PostMapping("")
    public void save(@RequestBody Candidato candidato){
        candidatosJpaRepository.save(candidato);
        System.out.println(candidato);

    }

    @GetMapping("votes")
    public List<String> votes(){
        List<Candidato> c = candidatosJpaRepository.findAll();

        //agrupo por nombre y apellido (Pair), seguido de la cantidad de candidatos del mismo tipo
        Map<Pair<String, String>, Long> map = c.stream().collect(Collectors.groupingBy(p ->
                Pair.of(p.getNombre(), p.getApellido()), Collectors.counting()));

        //creo lista de string
        List<String> candidatos = new ArrayList<>();

        //recorro el mapa con su Pair y su cantidad y las formateo en el string, luego lo agrego a la lista
        map.forEach((p,l) -> {
            String candidato = String.format("%s %s : %d" , p.getFirst(), p.getSecond(),l);
            candidatos.add(candidato);
        });

        return candidatos;
    }

    @Scheduled(fixedRate = 5000)
    public void eliminarCandidato(){
        List<Candidato> candidatos = candidatosJpaRepository.findAll();
        candidatos.forEach( c -> {
            long minutes = ChronoUnit.MINUTES.between(c.getFecha(), LocalDateTime.now());
            if(minutes >= 5){
                candidatosJpaRepository.deleteById(c.getId());
            }
        });
    }

    @GetMapping("")
    public List<Candidato> getCandidatos(){
        return candidatosJpaRepository.findAll();

    }

}
