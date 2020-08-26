package neointernship.bots.functionsH.bonus.debut;

import neointernship.bots.functionsH.bonus.Bonus;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Bishop;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.Knight;
import neointernship.chess.game.model.figure.piece.Queen;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.story.IStoryGame;

import java.util.Collection;

/**
 * развитие фигур
 */
public class BonusEvolutionFigure extends Bonus {

    public BonusEvolutionFigure(final double price) {
        super(price);
    }

    @Override
    public double getBonus(final Position position, final Color playerColor) {
        double result = 0;

        final IMediator mediator = position.getMediator();
        final Collection<Figure> figures = mediator.getFigures();
        final PossibleActionList possibleActionList = position.getPossibleActionList();
        final IStoryGame storyGame = possibleActionList.getStoryGame();

        for (final Figure figure : figures) {
            final int offset = figure.getColor() == playerColor ? 1 : -1;

            switch (figure.getGameSymbol()){

                // не развивать тяжелые фигуры в дебюте 
                case 'Q' :
                case 'R' : {
                    if(storyGame.isMove(figure)){
                        result = -offset * 2 * priceOnePawn;
                    }
                    break;
                }

                // чем больше ходов у легкой фигуры тем лучше
                case 'B' : {
                    result += offset * possibleActionList.getPotentialList(figure).size() * (priceOnePawn / 13);
                    break;
                }
                case 'N' : {
                    result += offset * possibleActionList.getPotentialList(figure).size() * (priceOnePawn / 8);
                    break;
                }
            }


        }
        return result;
    }
}

