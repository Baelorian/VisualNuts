package br.com.visualnuts.VisualNuts.service.impl;

import br.com.visualnuts.VisualNuts.constants.constants;
import br.com.visualnuts.VisualNuts.dto.World;
import br.com.visualnuts.VisualNuts.service.VisualNutsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
@Slf4j
public class VisualNutsServiceImpl implements VisualNutsService {

    public boolean authenticate(String token) {
        //TODO: IMPLEMENTAR JWT PARA CRIPTOGRAFIA DO HEADER COM A AUTENTICAÇÃO
        log.info("authenticate() - START: autentication code is {}",token);
        if (!token.equals(constants.autentication)) {
            log.error("authenticate(): Invalid Autentication");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid Autentication");
        }
        return true;
    }

    public String[] exercise1 () {
        String[] retorno = new String[constants.TimesToPrint];
        for (int cont = 1; cont <= constants.TimesToPrint; cont++) {
            boolean div3 = (cont % 3 == 0);
            boolean div5 = (cont % 5 == 0);
            if(div3 && div5){
                log.info("Visual Nuts");
                retorno[cont- 1] = "Visual Nuts";
            }
            else if(div3){
                log.info("Visual");
                retorno[cont- 1] = "Visual";
            }
            else if(div5){
                log.info("Nuts");
                retorno[cont- 1] = " Nuts";
            }
            else{
                log.info(String.valueOf(cont));
                retorno[cont- 1] = String.valueOf(cont);
            }
        }
        return retorno;
    }

    public void exercise2 (World world) {
        log.info(world.toString());
    }


    }
