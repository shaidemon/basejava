package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveToArray(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteFromArray(int index) {
        // replace deleted element by last value
        storage[index] = storage[size - 1];
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

