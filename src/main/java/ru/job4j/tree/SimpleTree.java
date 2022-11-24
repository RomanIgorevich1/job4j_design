package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        Optional<Node<E>> newParent = findBy(parent);
        if (newParent.isPresent() && findBy(child).isEmpty()) {
            newParent.get().children.add(new Node<>(child));
           result = true;
       }
        return result;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate((find) -> find.value.equals(value));
    }

    @Override
    public boolean isBinary() {
        return findByPredicate((value) -> value.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (condition.test(element)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }
}