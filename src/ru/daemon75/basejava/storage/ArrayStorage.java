package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayClass {
    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        if (size >= STORAGE_LIMIT) {
            System.out.printf("Sorry, exceeded the limit of storage. Can't save the resume %s \n", uuid);
        } else if (findIndex(uuid) >= 0) {
            System.out.printf(" Sorry, resume %s already present in storage.\n", uuid);
        } else {
            storage[size] = resume;
            size++;
            System.out.printf("resume %s saved \n", uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            // replace deleted element by last value
            storage[index] = storage[size - 1];
            // last resume to null & reduce size
            storage[size - 1] = null;
            size--;
            System.out.printf("Resume %s deleted \n", uuid);
        } else {
            System.out.printf("Sorry, resume %s not present in storage. Nothing to delete \n", uuid);
        }
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

