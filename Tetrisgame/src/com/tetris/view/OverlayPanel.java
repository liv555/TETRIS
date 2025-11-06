package com.tetris.view;

import com.tetris.controller.GameController;
import com.tetris.model.Board;
import com.tetris.model.Theme;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * Um painel transparente que desenha os 'overlays' (telas por cima do jogo),
 * como a tela de início, pausa e game over.
 */
public class OverlayPanel extends JPanel {

    private Board board;
    private GameController controller;
    private JButton startButton;
    private Timer blinkTimer;
    private boolean showPress = true;

    public OverlayPanel() {
        setOpaque(false); // Torna o painel transparente
        initStartButton();
        // Ajusta posição do botão quando o painel muda de tamanho
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                layoutStartButton();
            }
        });
        initBlinker();
    }

    private void initBlinker() {
        blinkTimer = new Timer(600, e -> {
            showPress = !showPress;
            repaint();
        });
        blinkTimer.setInitialDelay(0);
        blinkTimer.start();
    }

    private void initStartButton() {
        startButton = new JButton("Start");
        startButton.setFocusable(true);
        startButton.setVisible(false);
        startButton.setFont(new Font("Consolas", Font.BOLD, 16));
        startButton.setBorderPainted(false);
        // cores default — serão atualizadas via updateTheme
        startButton.setBackground(new Color(50, 50, 50));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.startGameFromUI();
                }
            }
        });
        setLayout(null);
        add(startButton);
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    /**
     * Atualiza o estilo do overlay com base no tema atual.
     */
    public void updateTheme(Theme theme) {
        if (theme == null) return;
        // Usa cores do tema para ajustar o botão
        Color bg = theme.uiBackground().darker();
        Color fg = (bg.getRed() < 128) ? Color.WHITE : Color.BLACK;
        startButton.setBackground(bg);
        startButton.setForeground(fg);
        // força repintura
        startButton.repaint();
        repaint();
    }

    private void layoutStartButton() {
        if (startButton == null) return;
        int w = 180;
        int h = 48;
        int x = (getWidth() - w) / 2;
        // posiciona o botão na parte inferior, com um padding de 40px do fundo
        int y = Math.max(20, getHeight() - h - 40);
        startButton.setBounds(x, y, w, h);
    }

    public void updateBoard(Board board) {
        this.board = board;
        // Mostrar/ocultar o botão Start conforme o estado do jogo
        if (startButton != null) {
            startButton.setVisible(board != null && !board.isStarted());
            layoutStartButton();
        }
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
        // Pisca o texto de instrução quando o jogo ainda não iniciou
        if (showPress) {
            String text = "Pressione ENTER para Jogar";
            int stringWidth = g.getFontMetrics().stringWidth(text);
            g.drawString(text, (getWidth() - stringWidth) / 2, y);
        }
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

