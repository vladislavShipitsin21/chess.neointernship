package neointernship.chess.game.model.answer;

import neointernship.chess.game.model.playmap.field.IField;

public class Answer implements IAnswer{
    IField in;
    IField out;


    public Answer(IField in, IField out) {
        this.in = in;
        this.out = out;
    }

    public IField getIn() {
        return in;
    }

    public void setIn(IField in) {
        this.in = in;
    }

    public IField getOut() {
        return out;
    }

    public void setOut(IField out) {
        this.out = out;
    }
}
