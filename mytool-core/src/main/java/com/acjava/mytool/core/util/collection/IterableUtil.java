package com.acjava.mytool.core.util.collection;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Description: 待补充
 * @author: loujm
 * @date: 2023/7/11
 */
public class IterableUtil {

    public static int size(final Iterable<?> iterable) {
        if (iterable instanceof Collection<?>) {
            return ((Collection<?>) iterable).size();
        }
        return IteratorUtil.size(getIterator(iterable));
    }

    private static <E> Iterator<E> getIterator(final Iterable<E> iterable) {
        return iterable == null ? null : iterable.iterator();
    }

}
