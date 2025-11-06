package com.tetris.view;

import com.tetris.model.Board;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Um painel transparente que desenha os 'overlays' (telas por cima do jogo),
 * como a tela de início, pausa e game over.
 */
public class OverlayPanel extends JPanel {

    private Board board;

    public OverlayPanel() {
        setOpaque(false); // Torna o painel transparente
    }

    public void updateBoard(Board board) {
        this.board = board;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (board == null) {
            return;
        }

        // Decide qual overlay desenhar com base no estado do jogo
        if (!board.isStarted()) {
            drawStartScreen(g);
        } else if (board.isGameOver()) {
            drawGameOver(g);
        } else if (board.isPaused()) {
            drawPaused(g);
        }
    }

    private void drawStartScreen(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 36));
        g.drawString("TETRIS", getWidth() / 2 - 60, getHeight() / 2 - 150);

        g.setFont(new Font("Consolas", Font.PLAIN, 14));
        int y = getHeight() / 2 - 80;
        int x = getWidth() / 2 - 110;

        drawControls(g, x, y);
        
        g.setFont(new Font("Consolas", Font.BOLD, 18));
        y = getHeight() - 150;
        g.drawString("Pressione ENTER para Jogar", getWidth() / 2 - 130, y);
    }

    private void drawGameOver(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 36));
        g.drawString("GAME OVER", getWidth()/2 - 80, getHeight() / 2);
        g.setFont(new Font("Consolas", Font.PLAIN, 18));
        g.drawString("ENTER para reiniciar", getWidth()/2 - 110, getHeight() / 2 + 40);
    }
    
    private void drawPaused(Graphics g) {
        g.setColor(new Color(0, 0, 0, 180));
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.setColor(Color.WHITE);
        g.setFont(new Font("Consolas", Font.BOLD, 36));
        g.drawString("PAUSADO", getWidth() / 2 - 70, getHeight() / 2 - 150);

        g.setFont(new Font("Consolas", Font.PLAIN, 14));
        int y = getHeight() / 2 - 80;
        int x = getWidth() / 2 - 110;
        
        drawControls(g, x, y);

        g.setFont(new Font("Consolas", Font.BOLD, 18));
        y = getHeight() - 150;
        g.drawString("Pressione P para continuar", getWidth() / 2 - 125, y);
    }

    /**
     * Novo método auxiliar para desenhar a lista de controlos.
     * É reutilizado pela tela de início e pela tela de pausa.
     */
    private void drawControls(Graphics g, int x, int y) {
        g.drawString("MANUAL DE CONTROLES", x, y);
        y += 30;
        g.drawString("←   Mover Esquerda", x, y);
        y += 20;
        g.drawString("→   Mover Direita", x, y);
        y += 20;
        g.drawString("↑   Girar (Horário)", x, y);
        y += 20;
        g.drawString("Z   Girar (Anti-horário)", x, y);
        y += 20;
        g.drawString("↓   Acelerar Queda", x, y);
        y += 20;
        g.drawString("Espaço   Cair Imediatamente", x, y);
        y += 20;
        g.drawString("P   Pausar Jogo", x, y);
        y += 20;
        g.drawString("T   Mudar Tema Visual", x, y);
        y += 20;
        g.drawString("G   Ativar/Desativar Prévia", x, y);
    }
}

