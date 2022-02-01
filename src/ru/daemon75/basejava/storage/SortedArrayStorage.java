package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayClass {
    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    public void clear() {

    }

    @Override
    public void save(Resume resume) {

    }

    @Override
    public void update(Resume resume) {

    }

    @Override
    public void delete(String uuid) {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }
}
