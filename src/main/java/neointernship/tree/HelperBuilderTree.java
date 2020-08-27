package neointernship.tree;

import neointernship.chess.game.model.answer.IAnswer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class HelperBuilderTree {

    public static Collection<INode> getChildren(final INode root) {
        final Collection<INode> result = new ArrayList<>();
        for (final IEdge edge : root.getEdges()) {
            result.add(edge.getChild());
        }
        return result;
    }

    public static Collection<INode> getAllChildren(final INode root) {
        final Collection<INode> allChildren = new ArrayList<>();
        getAllAllChildren(root, allChildren);
        return allChildren;
    }

    private static void getAllChildren(final INode root, final Collection<INode> allChildren) {
        for (final IEdge edge : root.getEdges()) {
            final INode child = edge.getChild();
            getAllChildren(child, allChildren);
        }
        if (root.isLeaf()) allChildren.add(root);
    }
    private static void getAllAllChildren(final INode root, final Collection<INode> allChildren) {
        for (final IEdge edge : root.getEdges()) {
            final INode child = edge.getChild();
            getAllChildren(child, allChildren);
        }
        allChildren.add(root);
    }

    public static IAnswer getAnswer(final INode root) {
        final double actualPrice = root.getCore().getPrice();
        for (final IEdge edge : root.getEdges()) {
            if (actualPrice == edge.getChild().getCore().getPrice()) {
                return edge.getAction();
            }
        }
        return null;
    }
}
