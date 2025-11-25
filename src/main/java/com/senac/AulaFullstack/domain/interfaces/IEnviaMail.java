package com.senac.AulaFullstack.domain.interfaces;

import org.springframework.stereotype.Repository;

@Repository
public interface IEnviaMail {
    void enviarEmailSimples(String para, String assunto, String corpo);
    void voidEnviarEmailComTemplate(String para, String assunto, String corpo);

}
