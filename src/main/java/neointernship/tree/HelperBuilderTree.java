package neointernship.tree;

import java.util.Collection;
import java.util.HashSet;

public class HelperBuilderTree {

    public static Collection<INode> getChildren(final INode root) {
        Collection<INode> result = new HashSet<>();
        for (IEdge edge : root.getEdges()) {
            result.add(edge.getChild());
        }
        return result;
    }

    public static Collection<INode> getAllChildren(final INode root) {
        Collection<INode> allChildren = new HashSet<>();
        getAllChildren(root, allChildren);
        return allChildren;
    }

    private static void getAllChildren(final INode root, final Collection<INode> allChildren) {
        for (IEdge edge : root.getEdges()) {
            INode child = edge.getChild();
            getAllChildren(child, allChildren);
        }
        if (root.isLeaf()) allChildren.add(root);
    }
}
