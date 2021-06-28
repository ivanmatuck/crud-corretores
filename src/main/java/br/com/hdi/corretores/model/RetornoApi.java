package br.com.hdi.corretores.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RetornoApi <T>{

    public RetornoApi adicionarMsgErro (String message) {
        this.message = message;
        this.success = false;
        return this;
    }

    public RetornoApi adicionarEntitadeRetorno (T body) {
        this.body = body;
        return this;
    }

    String message = "";

    Boolean success = true;

    T body;


}
