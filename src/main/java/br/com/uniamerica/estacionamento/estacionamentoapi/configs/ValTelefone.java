package br.com.uniamerica.estacionamento.estacionamentoapi.configs;

import org.springframework.stereotype.Component;

@Component
public class ValTelefone {

        public static boolean ValTelefone(String tel) {
            String telBR = "[1-9][0-9]\\ [2-9]{2}[0-9]{3}\\-[0-9]{4}";
            return tel.matches(telBR);
        }
    }


