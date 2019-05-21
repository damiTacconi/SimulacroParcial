package com.example.SimulacroParcial.domain;

import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Candidato {
    @Id @GeneratedValue
    private int id;
    private String nombre;
    private String apellido;
    private LocalDateTime fecha;

    @PrePersist
    public void agregarFecha(){
        if(Objects.isNull(this.fecha)){
            this.fecha = LocalDateTime.now();
        }
    }
}
