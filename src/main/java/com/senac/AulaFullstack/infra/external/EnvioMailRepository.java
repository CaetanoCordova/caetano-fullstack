package com.senac.AulaFullstack.infra.external;

import com.senac.AulaFullstack.domain.interfaces.IEnviaMail;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOError;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.LocalDateTime;

@Component
public class EnvioMailRepository implements IEnviaMail {

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void enviarEmailSimples(String para, String assunto, String corpo){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("caetano.silva.segundo@gmail.com");

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
        try{
            MimeMessage mensagem = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true, "UTF-8");

            String htmlTemplate = carregaTemplateMail();

            String htmlFinal = htmlTemplate
                    .replace("${mensagem}",corpo)
                    .replace("${dataEnvio}",String.valueOf(LocalDateTime.now()));


            helper.setFrom("caetano.silva.segundo@gmail.com");
            helper.setTo(para);
            helper.setSubject(assunto);
            helper.setText(htmlFinal,true);
            javaMailSender.send(mensagem);

        } catch (Exception e){
            throw new RuntimeException("Erro ao enviar email.");
        }
    }


    @Async
    private String carregaTemplateMail() throws IOException {
        try{
            ClassPathResource resource = new ClassPathResource("templates/email-template.html");
            byte[] bytes = Files.readAllBytes(resource.getFile().toPath());

            return new String(bytes, StandardCharsets.UTF_8);
        }catch (Exception e){

            throw new RuntimeException("Erro ao enviar email.");

        }
    }

}
