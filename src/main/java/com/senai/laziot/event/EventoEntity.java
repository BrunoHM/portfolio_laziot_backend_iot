package com.senai.laziot.event;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Entity
@Builder @Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@Table(name = "event")
public class EventoEntity {

    public EventoEntity(Long idUser, Long idAction){
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = localDate.format(formatter);

        formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.time = time.format(formatter);

        this.id = 0L;
        this.manual = false;
        this.executed = false;
        this.fkIdUser = idUser;
        this.fkIdAction = idAction;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "manual")
    private boolean manual;

    @Column(name = "executed")
    private boolean executed;

    @Column(name = "\"fkIdUser\"")
    private Long fkIdUser;

    @Column(name = "\"fkIdAction\"")
    private Long fkIdAction;
}
