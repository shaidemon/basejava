package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayClass {

    @Override
    protected void doAddElement(Resume resume, int index) {
        //transform index-value from binarySearch()
        index = -index - 1;
        // get place for added element by shifting elements from index to the right
        for (int i = size; i > index; i--) {
            System.arraycopy(storage, i - 1, storage, i, 1);
        }
        storage[index] = resume;
    }

    @Override
    protected void doReplaceElement(int index) {
        // shift righter elements to left for replace deleted element
        for (int i = index; i < size; i++) {
            System.arraycopy(storage, i + 1, storage, i, 1);
        }
    }

    @Override
    protected int doFindIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}