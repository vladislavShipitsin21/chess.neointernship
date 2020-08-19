package neointernship.chess.game.model.answer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class Answer implements IAnswer {
    @JsonProperty
    final int startX;
    @JsonProperty
    final int startY;
    @JsonProperty
    final int finalX;
    @JsonProperty
    final int finalY;
    @JsonProperty
    char simbol;

    @JsonCreator
    public Answer(@JsonProperty("startX") final int startX,
                  @JsonProperty("startY") final int startY,
                  @JsonProperty("finalX") final int finalX,
                  @JsonProperty("finalY") final int finalY,
                  @JsonProperty("simbol") final char simbol) {
        this.startX = startX;
        this.startY = startY;
        this.finalX = finalX;
        this.finalY = finalY;

        this.simbol = simbol;
    }

    @Override
    public int getStartX() {
        return startX;
    }

    @Override
    public int getStartY() {
        return startY;
    }

    @Override
    public int getFinalX() {
        return finalX;
    }

    @Override
    public int getFinalY() {
        return finalY;
    }

    @Override
    public char getSimbol() {
        return simbol;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Answer answer = (Answer) o;
        return startX == answer.startX &&
                startY == answer.startY &&
                finalX == answer.finalX &&
                finalY == answer.finalY &&
                simbol == answer.simbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startX, startY, finalX, finalY, simbol);
    }

}
