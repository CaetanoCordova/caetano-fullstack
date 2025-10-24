package com.senac.AulaFullstack.infra.external;

import com.senac.AulaFullstack.domain.interfaces.IEnviaMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EnvioMailRepository implements IEnviaMail {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void enviarEmailSimples(String para, String assunto, String corpo){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("naoresponsa@caetanofullstack.com");

            message.setTo(para);
            message.setSubject(assunto);
            message.setText(corpo);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Deu pau");
        }
    }

    @Async
    public void voidEnviarEmailComTemplate(String para, String assunto, String corpo){

    }

}
