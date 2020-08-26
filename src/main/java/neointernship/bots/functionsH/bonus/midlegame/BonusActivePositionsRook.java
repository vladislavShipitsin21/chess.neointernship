package neointernship.bots.functionsH.bonus.midlegame;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

public class BonusActivePositionsRook extends Bonus {

    protected BonusActivePositionsRook(final double price) {
        super(price);
    }
    // todo подумать об изменении в листе ( дабавление списка аттак, передвижений, защиты)

    // + ладьи соеденены
    // + ладья занимает открытую линию ( линия называется открытой, если на ней нет фигур моего цвета)
    @Override
    public double getBonus(final Position position, final Color playerColor) {
        return 0;
    }


    public boolean isTakeOpenLine(final Position position,final Color color){

        final IMediator mediator = position.getMediator();
        for(final Figure figure : mediator.getFigures(color)){
            if(figure.getClass() == Rook.class){
                final IField fieldRook = mediator.getField(figure);
                final int coordY = fieldRook.getYCoord();

                int coordX = fieldRook.getXCoord();


                while (coordX >= 0){
                    if(mediator.getFigure(BOARD.getField(coordX,coordY)) != null){

                    }
                    coordX--;
                }

            }
        }
        return true;
    }
}
