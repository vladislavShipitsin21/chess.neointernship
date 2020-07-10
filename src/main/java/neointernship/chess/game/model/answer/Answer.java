package neointernship.chess.game.model.answer;

import neointernship.chess.game.model.playmap.field.IField;

public class Answer implements IAnswer{

    IField out;
    IField in;

    public Answer(final IField out, final IField in) {
        this.out = out;
        this.in = in;
    }

    public IField getIn() {
        return in;
    }

    public void setIn(final IField in) {
        this.in = in;
    }

    public IField getOut() {
        return out;
    }

    public void setOut(final IField out) {
        this.out = out;
    }
}
