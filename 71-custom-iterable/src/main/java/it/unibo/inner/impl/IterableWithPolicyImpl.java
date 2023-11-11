package it.unibo.inner.impl;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{
    private final List<T> elements;

    public IterableWithPolicyImpl(final T[] parameterArray){
        this.elements.copyOf(parameterArray);
    }

    public void setIterationPolicy(final Predicate<T> filter) {

	}

    public class InnerIterableWithPolicyImpl implements Iterator<T>{
        private int currentIndex = 0;

		public boolean hasNext() {
			if(this.currentIndex < elements.size()){
                return true;
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
}