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
        "Clássico Escuro",
        new Color(40, 40, 55),
        new Color(20, 20, 30),
        new Color(50, 50, 70),
        new Color[] {
            new Color(0, 0, 0),       // NoShape
            new Color(204, 102, 102), // ZShape
            new Color(102, 204, 102), // SShape
            new Color(102, 102, 204), // LineShape
            new Color(204, 204, 102), // TShape
            new Color(204, 102, 204), // SquareShape
            new Color(102, 204, 204), // LShape
            new Color(218, 170, 0)    // MirroredLShape
        }
    );

    /**
     * Um tema claro, com cores vibrantes.
     */
  public static final Theme LIGHT = new Theme(
    "Claro",
    new Color(230, 220, 240),  // fundo principal - lavanda clara
    new Color(245, 240, 255),  // destaque - quase branco com toque lilás
    new Color(210, 200, 230),  // bordas - lilás suave
    new Color[] {
        new Color(90, 60, 120),    // roxo escuro
        new Color(130, 80, 160),   // violeta médio
        new Color(170, 110, 200),  // lilás
        new Color(200, 140, 220),  // lavanda viva
        new Color(220, 180, 240),  // lavanda clara
        new Color(150, 90, 180),   // púrpura médio
        new Color(180, 130, 210),  // ametista
        new Color(240, 200, 250)   // rosa arroxeado claro
    }
);
        
    

    // Array que contém todos os temas disponíveis para fácil acesso.
    public static final Theme[] AVAILABLE_THEMES = { CLASSIC_DARK, LIGHT, };
}
