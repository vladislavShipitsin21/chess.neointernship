package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Knight;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class DrawFewFigure implements IDrawController {

    // Невозможно поставить мат, если на доске остались только короли, либо,
    // кроме двух королей, один конь или произвольное количество однопольных слонов
    @Override
    public boolean isDraw(final IMediator mediator) {
        final Collection<Figure> figures = mediator.getFigures();
        if(figures.size() == 3) {
            for (Figure figure : figures) {
                if (figure.getClass() == Knight.class) return true;
            }
        }
        return isBishops(figures, mediator);
    }
    private boolean isBishops(final Collection<Figure> figures,final IMediator mediator){
        Set<Color> colors = new HashSet<>();
        for(Figure figure : figures) {
            if (figure.getClass() != King.class) {
                final IField field = mediator.getField(figure);
                colors.add(field.getColor());
                if (figure.getClass() != Bishop.class) return false;
            }
        }
        return colors.size() == 1;
    }
}
