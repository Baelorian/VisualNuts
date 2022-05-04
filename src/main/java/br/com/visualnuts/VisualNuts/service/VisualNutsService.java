package br.com.visualnuts.VisualNuts.service;


import br.com.visualnuts.VisualNuts.dto.World;

public interface VisualNutsService {

    boolean authenticate(String token);
    String[] exercise1();
    String exercise2(World[] world);

    }
