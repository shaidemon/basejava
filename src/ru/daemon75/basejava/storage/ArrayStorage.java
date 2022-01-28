package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    //number of resumes;
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();
        if (size >= storage.length) {
            System.out.printf("Sorry, exceeded the limit of storage. Can't save the resume %s \n", uuid);
        } else if (findIndex(uuid) >= 0) {
            System.out.printf(" Sorry, resume %s already present in storage.\n", uuid);
        } else {
            storage[size] = resume;
            size++;
            System.out.printf("resume %s saved \n", uuid);
        }
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = findIndex(uuid);
        if (index >= 0) {
            storage[index] = resume;
            System.out.printf("Resume %s updated \n", uuid);
        } else {
            System.out.printf("Sorry, resume %s is not present on storage. Nothing to update \n", uuid);
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.printf("Sorry, resume %s not found! \n", uuid);
        return null;
    }

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

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] allResumes = new Resume[size];
        System.arraycopy(storage, 0, allResumes, 0, size);
        return allResumes;
    }

    public int size() {
        return size;
    }

    int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}

