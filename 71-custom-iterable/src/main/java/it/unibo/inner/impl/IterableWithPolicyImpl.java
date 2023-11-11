package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    private final List<T> elements;
    private Predicate<T> filter;

    public IterableWithPolicyImpl(final T[] parameterArray){
        this(parameterArray, new Predicate<T>(){public boolean test(T elem){ return true;}});
    }

    public IterableWithPolicyImpl(final T[] parameterArray, final Predicate<T> filter){
        elements = List.of(parameterArray);
        this.filter = filter;
    }

    public void setIterationPolicy(final Predicate<T> filter) {
        this.filter = filter;
	}

    public class InnerIterableWithPolicyImpl implements Iterator<T>{
        private int currentIndex = 0;

		public boolean hasNext() {
			while(this.currentIndex < elements.size()){
                T elem = elements.get(currentIndex);
                if(filter.test(elem)){
                    return true;
                }
                this.currentIndex++;
            }
            return false;
		}

		public T next() {
            if(hasNext()){
			    return elements.get(this.currentIndex++);
            }
            throw new NoSuchElementException();
		}
    }

    public Iterator<T> iterator() {
        return new InnerIterableWithPolicyImpl();
    }
}