package com.system559.algorithms;

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;

public class Sort {
    public static <T extends Comparable<T>> List<T> quickSort(List<T> objects) {
        if (objects.size() < 2) {
            return objects;
        }

        T pivot = objects.get(objects.size() - 1);
        int pivotIndex = 0;

        List<T> partitionedList = new ArrayList<>();

        partitionedList.add(pivot);

        for (int i = 0; i < objects.size() - 1; i++) {
            int comparison = objects.get(i).compareTo(pivot);
            if (comparison >= 0) {
                partitionedList.add(objects.get(i));
            } else {
                partitionedList.add(pivotIndex, objects.get(i));
                pivotIndex++;
            }
        }

        List<T> result = new ArrayList<>();

        if (pivotIndex == 0) {
            result.add(pivot);
            result.addAll(quickSort(partitionedList.subList(1, partitionedList.size())));
        } else if (pivotIndex == objects.size() - 1) {
            result.addAll(quickSort(partitionedList.subList(0, partitionedList.size()-1)));
            result.add(pivot);
        } else {
            result.addAll(quickSort(partitionedList.subList(0,pivotIndex)));
            result.add(pivot);
            result.addAll(quickSort(partitionedList.subList(pivotIndex+1, partitionedList.size())));
        }

        return result;
    }
}
