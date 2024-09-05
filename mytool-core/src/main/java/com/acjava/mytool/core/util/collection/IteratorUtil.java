package com.acjava.mytool.core.util.collection;

import java.util.Iterator;

/**
 * @Description: 待补充
 * @author: loujm
 * @date: 2023/7/11
 */
public class IteratorUtil {

    public static int size(final Iterator<?> iterator) {
        int size = 0;
        if (iterator != null) {
            while (iterator.hasNext()) {
                iterator.next();
                size++;
            }
        }
        return size;
    }


}
