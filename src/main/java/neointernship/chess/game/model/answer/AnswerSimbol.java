package neointernship.chess.game.model.answer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public class AnswerSimbol implements IAnswer {
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

    private static final RepositiryChar repositiryChar = new RepositiryChar();

    @JsonCreator
    public AnswerSimbol(@JsonProperty("startC") final char startC,
                        @JsonProperty("startI") final char startI,
                        @JsonProperty("finishC") final char finishC,
                        @JsonProperty("finishI") final char finishI,
                        @JsonProperty("simbol") final char simbol) {
        this.startX  = repositiryChar.getX(startI);
        this.startY = repositiryChar.getY(startC);

        this.finalX = repositiryChar.getX(finishI);
        this.finalY = repositiryChar.getY(finishC);

        this.simbol = simbol;
    }

    public AnswerSimbol(final char startC, final char startI, final char finishC, final char finishI) {
        this.startX  = repositiryChar.getX(startI);
        this.startY = repositiryChar.getY(startC);

        this.finalX = repositiryChar.getX(finishI);
        this.finalY = repositiryChar.getY(finishC);

        this.simbol = 'Q';
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
}
