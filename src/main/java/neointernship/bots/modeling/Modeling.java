package neointernship.bots.modeling;

import javafx.geometry.Pos;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;
import java.util.HashSet;

public class Modeling {

    public static Collection<Position> modeling(final Position actualPosition,final Color activeColor){
        final Collection<Position> positions = new HashSet<>();
       /* final Collection<Figure> figures = actualPosition.getMediator().getFigures(activeColor);

        for(final Figure figure : figures){
            for(final IField field : actualPosition.getPossibleActionList().getRealList(figure)){

               // Position newPosition = new Position()


            }
        }*/
        return null;
    }
}
