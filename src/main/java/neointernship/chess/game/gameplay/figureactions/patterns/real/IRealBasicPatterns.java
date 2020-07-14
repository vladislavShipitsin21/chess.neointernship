package neointernship.chess.game.gameplay.figureactions.patterns.real;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.playmap.field.IField;

import java.util.Collection;

public interface IRealBasicPatterns {
    Collection<IField> getRealMoveList(final Figure figure, final Collection<IField> potentialMoveList);
}
