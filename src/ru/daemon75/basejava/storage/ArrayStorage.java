package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayClass {

    @Override
    protected void doAddElement(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void doReplaceElement(int index) {
        // replace deleted element by last value
        storage[index] = storage[size - 1];
        // last resume to null
        storage[size - 1] = null;
    }

    @Override
    protected int doFindIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

