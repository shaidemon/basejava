package ru.daemon75.basejava.storage;

import ru.daemon75.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    //last-resume position in storage;
    int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

     public void save(Resume resume) {
        if (!isPresent(resume.getUuid()) && size < storage.length) {
            storage[size] = resume;
            size++;
        } else if (isPresent(resume.getUuid())) {
            System.out.println(" Sorry, this resume already present in storage. Can't save the resume");
        } else if (size >= storage.length) {
            System.out.println("Sorry, exceeded the limit of storage. Can't save the resume");
        }
    }

    public void update(Resume resume) {
        if (isPresent(resume.getUuid())) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {
                    storage[i]=resume;
                }
            }
        } else {
            System.out.println("Sorry, this resume is not present on storage. Nothing to update");
        }
    }

     public Resume get(String uuid) {
        if (isPresent(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        } else {
        System.out.println("Sorry, resume not found!");
        }
        return null;
    }

     public void delete(String uuid) {
       if (isPresent(uuid)) {
           for (int i = 0; i < size; i++) {
               if (storage[i].getUuid().equals(uuid)) {
                   // replace deleted element by last value
                   storage[i] = storage[size - 1];
                   // last resume to null & reduce size
                   storage[size -1] = null;
                   size--;
               }
           }
       } else {
           System.out.println("Sorry, this resume not present in storage. Nothing to delete");
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

    boolean isPresent(String uuid) {
        boolean check = false;
        for (int i = 0; i <size ; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                check = true;
                break;
            }
        }
        return check;
    }
}

