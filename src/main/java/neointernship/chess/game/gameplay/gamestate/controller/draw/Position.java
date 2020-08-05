package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;

import java.util.Objects;

public class Position implements Comparable<Position> {
    private final IMediator mediator;
    private final PossibleActionList possibleActionList;

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    private double price;

    public Position(final IMediator mediator, final IPossibleActionList possibleActionList) {
        this.mediator = new Mediator(mediator);
//        this.possibleActionList = new PossibleActionList((PossibleActionList) possibleActionList);
        this.possibleActionList = (PossibleActionList) possibleActionList;
    }

    public IMediator getMediator() {
        return mediator;
    }

    public PossibleActionList getPossibleActionList() {
        return possibleActionList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return this.mediator.equals(position.mediator) &&
                this.possibleActionList.equals(position.possibleActionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediator, possibleActionList);
    }

    @Override
    public int compareTo(Position o) {
        return Double.compare(price,o.price);
    }
}
