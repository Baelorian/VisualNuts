package br.com.visualnuts.VisualNuts.service.impl;

import br.com.visualnuts.VisualNuts.constants.constants;
import br.com.visualnuts.VisualNuts.dto.World;
import br.com.visualnuts.VisualNuts.service.VisualNutsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@Service
@Slf4j
public class VisualNutsServiceImpl implements VisualNutsService {

    public boolean authenticate(String token) {
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

    public String exercise2 (World[] world) {
        String retorno;
        int numberOfCountries = 0;
        String mostOfficialLanguagesWhitDe = "";
        int officialLanguagesWhitDe = 0;
        String countryWMostOfficialLanguages = "";
        int officialLanguages = 0;
        Map<String,Integer> languages = new HashMap<>();
        StringBuilder allLanguages = new StringBuilder();
        StringBuilder mostCommonLanguage = new StringBuilder();
        int mostCommonLanguageIncidence = 0;

        for (World value : world) {

            numberOfCountries++;

            if (value.getLanguages().length > officialLanguages) {
                officialLanguages = value.getLanguages().length;
                countryWMostOfficialLanguages = value.getCountry();
            }

            for (String language : value.getLanguages()) {
                if (!languages.containsKey(language)) {
                    languages.put(language, 1);
                    allLanguages.append(" ").append(language);
                } else {
                    languages.replace(language, languages.get(language) + 1);
                    if (mostCommonLanguageIncidence < languages.get(language) + 1)
                        mostCommonLanguageIncidence = languages.get(language);
                }
                if (language.contains("de") && value.getLanguages().length > officialLanguagesWhitDe) {
                    officialLanguagesWhitDe = value.getLanguages().length;
                    mostOfficialLanguagesWhitDe = value.getCountry();
                }
            }
            for (Map.Entry<String, Integer> entry : languages.entrySet()) {
                if (Objects.equals(mostCommonLanguageIncidence, entry.getValue())) {
                    if (mostCommonLanguage.length() == 0) mostCommonLanguage = new StringBuilder(entry.getKey());
                    else if (!mostCommonLanguage.toString().contains(entry.getKey()))
                        mostCommonLanguage.append(" and ").append(entry.getKey());
                }
            }

        }
        retorno ="\n We have " + numberOfCountries + " countries in this world" +
                "\n the country with the most official languages, where they officially speak German (de) is " + mostOfficialLanguagesWhitDe +" and this country has " + officialLanguagesWhitDe+ " official languages"+
                "\n all the "+ languages.size() +" official languages spoken in the listed countries is :" + allLanguages +
                "\n the country with the highest number of official languages is " + countryWMostOfficialLanguages + " with your " + officialLanguages + " official languages" +
                "\n the most common official language(s) of all countries is " + mostCommonLanguage  + " with appear " + mostCommonLanguageIncidence + " times" ;

        log.info(retorno);
        return retorno;
    }
}
