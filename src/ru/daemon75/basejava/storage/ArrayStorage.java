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
        if (size >= storage.length) {
            System.out.println("Sorry, exceeded the limit of storage. Can't save the resume " + resume.getUuid());
        } else if (check(resume.getUuid()) >= 0) {
            System.out.println(" Sorry, resume " + resume.getUuid() + " already present in storage.");
        } else {
            storage[size] = resume;
            size++;
            System.out.printf("resume %s saved \n", resume.getUuid());
        }
    }

    public void update(Resume resume) {
        int i = check(resume.getUuid());
        if (i >=0) {
            storage[i]=resume;
            System.out.printf("Resume %s updated \n", resume.getUuid());
        } else {
            System.out.println("Sorry, resume " + resume.getUuid() + " is not present on storage. Nothing to update");
        }
    }

     public Resume get(String uuid) {
         int i = check(uuid);
         if (i >=0) {
             return storage[i];
        } else {
        System.out.println("Sorry, resume " + uuid + " not found!");
        }
        return null;
    }

     public void delete(String uuid) {
       int i = check(uuid);
        if (i >=0) {
                   // replace deleted element by last value
                   storage[i] = storage[size - 1];
                   // last resume to null & reduce size
                   storage[size -1] = null;
                   size--;
            System.out.printf("Resume %s deleted \n", uuid);
       } else {
           System.out.println("Sorry, resume " + uuid + " not present in storage. Nothing to delete");
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

    int check(String uuid) {
        int check = -1;
        for (int i = 0; i <size ; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                check = i;
                break;
            }
        }
        return check;
    }
}

