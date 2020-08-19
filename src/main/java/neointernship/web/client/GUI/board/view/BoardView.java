package neointernship.web.client.GUI.board.view;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.GUI.board.labels.ChessLabel;
import neointernship.web.client.GUI.board.labels.labelsgetter.LabelsRepository;

import javax.swing.*;
import java.awt.*;

public class BoardView extends JFrame implements IBoardView {
    private final IBoard board;
    private final IMediator mediator;

    private final LabelsRepository labelsRepository;

    private final GridLayout gridLayout;

    private final ChessLabel[][] labels;
    Container contentPane = getContentPane();

    private final ChessLabel[] sideNumbers;
    private final ChessLabel[] sideLetters;
    private final ChessLabel lastLabel;

    public BoardView(final IMediator mediator, final IBoard board) {
        this.mediator = mediator;
        this.board = board;

        labelsRepository = new LabelsRepository();

        labels = new ChessLabel[board.getSize()][board.getSize()];
        sideLetters = new ChessLabel[board.getSize()];
        sideNumbers = new ChessLabel[board.getSize()];
        lastLabel = new ChessLabel(" ");

        gridLayout = new GridLayout(9, 9);
        setTitle("Chess board");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        startUpdate();
    }

    @Override
    public void display() {
        contentPane.setLayout(gridLayout);
        for (int i = 0; i < board.getSize(); i++) {
            contentPane.add(sideNumbers[i]);
            for (int j = 0; j < board.getSize(); j++) {
                contentPane.add(labels[i][j]);
            }
        }
        contentPane.add(lastLabel);
        for (int i = 0; i < board.getSize(); i++) {
            contentPane.add(sideLetters[i]);
        }


        setBounds(650, 50, 700, 700);
        setVisible(true);
    }

    @Override
    public void update() {
        for (int i = 0; i < board.getSize(); i++) {
            getContentPane().remove(sideLetters[i]);
            getContentPane().remove(sideNumbers[i]);
        }
        getContentPane().remove(lastLabel);

        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                getContentPane().remove(labels[i][j]);


                final IField field = board.getField(i, j);
                final Figure figure = mediator.getFigure(field);


                String label = " ";
                if (figure != null) {
                    label = labelsRepository.getLabel(figure.getColor(), figure.getGameSymbol());
                }
                labels[i][j] = new ChessLabel(label);


                labels[i][j].set(i, j);
            }
        }
    }

    private void startUpdate() {
        for (int i = 0; i < board.getSize(); i++) {
            final String labelNumber = String.valueOf(8 - i);
            sideNumbers[i] = new ChessLabel(labelNumber);
            sideNumbers[i].set(i, 8);

            final String labelLetter = String.valueOf((char) ((int) ('\u0041') + i));
            sideLetters[i] = new ChessLabel(labelLetter);
            sideLetters[i].set(8, i);
        }


        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                final IField field = board.getField(i, j);
                final Figure figure = mediator.getFigure(field);

                String label = " ";
                if (figure != null) {
                    label = labelsRepository.getLabel(figure.getColor(), figure.getGameSymbol());
                }
                labels[i][j] = new ChessLabel(label);


                labels[i][j].set(i, j);
                contentPane.add(labels[i][j]);
            }
        }
    }
}
