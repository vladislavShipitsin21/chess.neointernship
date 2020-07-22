package neointernship.chess.game.model.enums;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public enum Color {
    BLACK,
    WHITE,
    BOTH;

    public static Color swapColor(Color color){
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    @JsonCreator
    public static Color parseColor(final String color) {
        switch (color) {
            case "BLACK":
                return BLACK;
            case "WHITE":
                return WHITE;
            case "BOTH":
                return BOTH;
        }
        return null;
    }
}
