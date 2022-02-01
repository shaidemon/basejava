package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayClass {

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        int index = -findIndex(uuid) - 1;
        if (size >= STORAGE_LIMIT) {
            System.out.printf("Sorry, exceeded the limit of storage. Can't save the resume %s \n", uuid);
        } else {
            if (index < 0) {
                System.out.printf(" Sorry, resume %s already present in storage.\n", uuid);
            } else {
                for (int i = size; i > index; i--) {
                    System.arraycopy(storage, i - 1, storage, i, 1);
                }
                storage[index] = resume;
                size++;
                System.out.printf("resume %s saved \n", uuid);
            }
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.printf("Sorry, resume %s not present in storage. Nothing to delete \n", uuid);
        } else {
            // shift righter elements to left
            for (int i = index; i < size; i++) {
                System.arraycopy(storage, i + 1, storage, i, 1);
            }
            size--;
            System.out.printf("Resume %s deleted \n", uuid);
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}