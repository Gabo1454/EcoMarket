package com.msvc.boleta.init;

import com.github.javafaker.Faker;
import com.msvc.boleta.models.entities.Boleta;
import com.msvc.boleta.repositories.BoletaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Locale;

@Profile("dev")
@Component
public class LoadDatabase implements CommandLineRunner {

    @Autowired
    private BoletaRepository boletaRepository;

    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(Locale.of("es","CL"));

        if(boletaRepository.count()==0){
            for(int i=0;i<100;i++){
                Boleta boleta = new Boleta();

                boleta.setFechaBoleta(LocalDate.now().minusDays(faker.number().numberBetween(0, 90)));
                boleta.setTotal(faker.number().randomDouble(2, 1000, 50000)); // Total entre 1.000,00 y 50.000,00
                boleta.setDescripcionBoleta("Boleta por compra de " + faker.commerce().productName()); // Descripción con producto
                boleta.setIdClientePojo((long) faker.number().numberBetween(1, 50)); // ID de cliente entre 1 y 50


                logger.info("El nombre que agregas es {}", boleta.getDescripcionBoleta());
                boleta = boletaRepository.save(boleta);
                logger.info("EL boleta creado es: {}", boleta);

            }
        }

    }
}
