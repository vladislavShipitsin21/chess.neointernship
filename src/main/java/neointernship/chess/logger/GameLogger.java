package neointernship.chess.logger;


import neointernship.chess.game.gameplay.gamestate.controller.draw.IDrawController;
import neointernship.chess.game.gameplay.moveaction.commands.allow.IAllowCommand;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.field.IField;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;


public class GameLogger implements IGameLogger{
    final Logger logger;

    public GameLogger(final int lobbyId) {
        this.logger = Logger.getLogger(lobbyId + "");

        try {
            final PatternLayout patternLayout = new PatternLayout();
            patternLayout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
            logger.addAppender(new FileAppender(patternLayout, "logs\\gameLog" + lobbyId + ".txt", false));

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logStartGame(final IPlayer playerOne, final IPlayer playerTwo) {
        logger.info("Игра между игроком " + playerOne.getName() + " и игроком " + playerTwo.getName() + " началась!");
    }

    @Override
    public void logPlayerMoveAction(final Color color, final Figure figure,
                                    final IField startField, final IField finalField, final IAllowCommand command) {
        logger.info(command.getNameCommand() + "- Игрок " + color + " сделал ход фигурой " + figure.getName() + " из клетки " +
                startField.toString() + " в клетку " + finalField.toString());
    }

    @Override
    public void logPlayerWrongAction(final Color color, final IField field) {
        logger.warn("Игрок " + color + " попыталя сделать ход из клетки " + field.toString() + ", но он невозможен");
    }

    @Override
    public void logPlayerWin(final Color color) {
        logger.info(color + " игрок победил!");
    }

    @Override
    public void logStalemate() {
        logger.info("Пат!");
    }

    @Override
    public void logDraw(final IDrawController drawController ) {
        logger.info("Ничья : " + drawController.getMessage());
    }
}
