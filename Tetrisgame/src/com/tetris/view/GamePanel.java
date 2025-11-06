package com.tetris.view;

import com.tetris.model.Theme;

import javax.swing.JPanel;
import java.awt.BorderLayout;

/**
 * Painel principal que contém e organiza os outros componentes da view.
 * Usa um BorderLayout para posicionar o tabuleiro e o painel de informações.
 */
public class GamePanel extends JPanel {

    private BoardPanel boardPanel;
    private InfoPanel infoPanel;

    public GamePanel() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        boardPanel = new BoardPanel();
        infoPanel = new InfoPanel();

        add(boardPanel, BorderLayout.CENTER);
        add(infoPanel, BorderLayout.EAST);
    }

    /**
     * Atualiza o tema de todos os componentes visuais filhos.
     */
    public void updateTheme(Theme theme) {
        infoPanel.updateTheme(theme);
        boardPanel.updateTheme(theme);
    }


    // --- Getters para o Controller ---

    public BoardPanel getBoardPanel() {
        return boardPanel;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }
}

