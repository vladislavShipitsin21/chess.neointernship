package neointernship.chess.game.gameplay.gamestate.controller.draw;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.model.mediator.IMediator;

import java.util.Objects;

public class Position implements Comparable<Position> {
    private final IMediator mediator;
    private final PossibleActionList possibleActionList;

    public void setPrice(final double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    private double price;

    public Position(final IMediator mediator, final IPossibleActionList possibleActionList) {
        this.mediator = mediator;
        this.possibleActionList = (PossibleActionList) possibleActionList;
    }

    public IMediator getMediator() {
        return mediator;
    }

    public PossibleActionList getPossibleActionList() {
        return possibleActionList;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Position position = (Position) o;
        return this.mediator.equals(position.mediator) &&
                this.possibleActionList.equals(position.possibleActionList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediator, possibleActionList);
    }

    @Override
    public int compareTo(final Position o) {
        return Double.compare(price, o.price);
    }
}
