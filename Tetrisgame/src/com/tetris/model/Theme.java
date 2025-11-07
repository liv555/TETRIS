package com.tetris.model;

import java.awt.Color;

/**
 * Representa um tema visual para o jogo, contendo todas as cores necessárias.
 * Usamos um 'record' para uma definição concisa e imutável de um tema.
 */
public record Theme(
    String name,
    Color uiBackground,
    Color boardBackground,
    Color grid,
    Color[] pieceColors // Array com 8 cores: a primeira é para 'NoShape', as outras 7 para as peças.
) {
    // --- Temas Pré-definidos ---

    /**
     * O tema escuro original do jogo.
     */
    public static final Theme CLASSIC_DARK = new Theme(
        "Roxo Escuro",
        new Color(45, 25, 65),      // fundo UI - roxo escuro profundo
        new Color(30, 15, 45),      // fundo tabuleiro - roxo mais escuro ainda
        new Color(60, 40, 80),      // grid - roxo médio-escuro para linhas
        new Color[] {
            new Color(20, 10, 30),      // NoShape - quase preto com tom roxo
            new Color(140, 60, 180),    // ZShape - roxo médio vibrante
            new Color(100, 40, 150),    // SShape - roxo profundo
            new Color(80, 30, 120),     // LineShape - roxo escuro rico
            new Color(120, 50, 160),    // TShape - roxo médio-escuro
            new Color(160, 80, 200),    // SquareShape - roxo mais claro
            new Color(90, 35, 130),     // LShape - roxo profundo médio
            new Color(130, 55, 170)     // MirroredLShape - roxo vibrante escuro
        }
    );

    /**
     * Um tema claro, com cores vibrantes.
     */
  public static final Theme LIGHT = new Theme(
    "Claro",
    new Color(230, 210, 255),  // fundo principal - lilás claro suave
    new Color(235, 225, 245),  // fundo do tabuleiro - roxo suave
    new Color(205, 195, 225),  // grid - roxo médio para linhas
    new Color[] {
        new Color(90, 60, 120),    // NoShape - roxo escuro
        new Color(186, 104, 200),  // Z - roxo-rosa vibrante
        new Color(149, 102, 230),  // S - roxo médio-claro
        new Color(140, 90, 210),   // Line - roxo médio
        new Color(170, 120, 240),  // T - roxo-azulado
        new Color(190, 150, 250),  // Square - lavanda clara
        new Color(160, 100, 220),  // L - roxo médio-escuro
        new Color(200, 160, 255)   // MirroredL - lilás claro
    }
);
        
    

    // Array que contém todos os temas disponíveis para fácil acesso.
    public static final Theme[] AVAILABLE_THEMES = { CLASSIC_DARK, LIGHT, };
}
