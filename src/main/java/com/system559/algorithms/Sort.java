package com.system559.algorithms;

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sort {
    private final static String NULL_LIST_MESSAGE = "Cannot sort an uninitialized List";

    public static <T extends Comparable<T>> List<T> quickSort(List<T> objects) {
        if(Objects.isNull(objects)) {
            throw new NullPointerException(NULL_LIST_MESSAGE);
        }

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
            result.addAll(quickSort(partitionedList.subList(0, partitionedList.size() - 1)));
            result.add(pivot);
        } else {
            result.addAll(quickSort(partitionedList.subList(0, pivotIndex)));
            result.add(pivot);
            result.addAll(quickSort(partitionedList.subList(pivotIndex + 1, partitionedList.size())));
        }

        return result;
    }

    public static <T extends Comparable<T>> List<T> bubbleSort(List<T> objects) {
        if(Objects.isNull(objects)) {
            throw new NullPointerException(NULL_LIST_MESSAGE);
        }

        if(objects.size() < 2) {
            return objects;
        }

        int lowestSortedMember = objects.size() - 1;

        while (lowestSortedMember > 1) {
            for (int i = 0; i < lowestSortedMember; i++) {
                int comparison = objects.get(i).compareTo(objects.get(i + 1));
                if (comparison <= 0) {
                    continue;
                }
                T lowObject = objects.get(i + 1);
                objects.set(i + 1, objects.get(i));
                objects.set(i, lowObject);
            }
            lowestSortedMember--;
        }

        return objects;
    }

    public static <T extends Comparable<T>> List<T> insertionSort(List<T> objects) {
        if(Objects.isNull(objects)) {
            throw new NullPointerException(NULL_LIST_MESSAGE);
        }

        if(objects.size() < 2) {
            return objects;
        }

        List<T> sortedList = new ArrayList<>();

        for (T nextObject : objects) {
            if (sortedList.isEmpty()) {
                sortedList.add(nextObject);
                continue;
            }

            for (int j = 0; j <= sortedList.size(); j++) {
                if (j == sortedList.size()) {
                    sortedList.add(nextObject);
                    break;
                }
                if (sortedList.get(j).compareTo(nextObject) > 0) {
                    sortedList.add(j, nextObject);
                    break;
                }
            }
        }

        return sortedList;
    }
}
