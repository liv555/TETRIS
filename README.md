🎮 Tetrisgame

Um clone clássico do jogo Tetris, desenvolvido em Java, com arquitetura modular e suporte a temas personalizados. O projeto segue o padrão MVC (Model-View-Controller) e inclui recursos de pontuação, interface gráfica e diferentes estilos visuais.

🧩 Funcionalidades

Interface gráfica interativa em Java Swing.

Sistema de pontuação com registro em arquivo (highscore.txt).

Controle de peças com movimentação e rotação suaves.

Gerador aleatório de formas clássicas do Tetris.

Tema visual personalizável (exemplo: tons de roxo no modo claro).

Tela de informações com pontuação e próximo bloco.

🧱 Estrutura do Projeto

Tetrisgame/
├── run.sh                     # Script para executar o jogo no Linux
├── highscore.txt              # Registro de pontuações
└── src/
    └── com/tetris/
        ├── Main.java          # Classe principal (ponto de entrada)
        ├── controller/
        │   └── GameController.java  # Lógica principal do jogo
        ├── model/
        │   ├── Board.java     # Representação do tabuleiro
        │   ├── Piece.java     # Representação das peças
        │   ├── Shape.java     # Definição das formas
        │   └── Theme.java     # Cores e estilos visuais
        └── view/
            ├── GameFrame.java # Janela principal
            ├── GamePanel.java # Área do jogo
            ├── BoardPanel.java# Painel do tabuleiro

🎨 Personalização de Tema

É possível alterar as cores do tema diretamente na classe Theme.java.
Exemplo de paleta em tons de roxo (modo claro):

public static final Theme LIGHT = new Theme(
    "Claro",
    new Color(230, 220, 240),
    new Color(245, 240, 255),
    new Color(210, 200, 230),
    new Color[] {
        new Color(90, 60, 120),
        new Color(130, 80, 160),
        new Color(170, 110, 200),
        new Color(200, 140, 220),
        new Color(220, 180, 240),
        new Color(150, 90, 180),
        new Color(180, 130, 210),
        new Color(240, 200, 250)
    }
);

🏆 Pontuação

O jogo salva automaticamente a maior pontuação no arquivo:

highscore.txt

O valor é atualizado sempre que o jogador supera o recorde atual.

🧠 Estrutura MVC

Model: Gerencia dados e regras do jogo.

View: Exibe os elementos gráficos e informações.

Controller: Coordena interações e lógica entre modelo e interface.

📜 Licença

Este projeto é de uso livre para fins educacionais.
Sinta-se à vontade para modificar, estudar e aprimorar o código.
            ├── InfoPanel.java # Exibe pontuação e próxima peça
            └── OverlayPanel.java # Tela de pausa/fim de jogo
