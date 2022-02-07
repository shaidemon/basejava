package ru.daemon75.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.daemon75.basejava.exception.ExistStorageException;
import ru.daemon75.basejava.exception.NotExistStorageException;
import ru.daemon75.basejava.exception.StorageException;
import ru.daemon75.basejava.model.Resume;

import static ru.daemon75.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {

    protected Storage storage;
    protected int initSize;

    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void init() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));
        initSize = storage.size();
    }

    @Test
    void size() {
        Assertions.assertEquals(3, storage.size());
        //monitoring
        System.out.println("size: " + storage.size());
    }

    @Test
    void save() {
        Resume r = new Resume("uuid0_save_test");
        storage.save(r);
        Assertions.assertEquals(r, storage.get("uuid0_save_test"));
        // different insert monitoring
        monitoring();
    }

    @Test
    void get() {
        Assertions.assertEquals(new Resume(UUID_1), storage.get(UUID_1));
        // monitoring
        System.out.println("Get resume UUID: " + storage.get(UUID_1).toString());
    }

    @Test
    public void getNotExist() {
        Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.get("dummy"));
        Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.update(new Resume("dummy")));
        Assertions.assertThrows(NotExistStorageException.class, () ->
                storage.delete("dummy"));


    }

    @Test
    public void getExist() {
        Assertions.assertThrows(ExistStorageException.class, () ->
                storage.save(new Resume(UUID_1)));
    }

    @Test
    public void getOverflow() {
        try {
            fillStorage();
        } catch (StorageException e) {
            Assertions.fail("Unexpected storage overflow while filling");
        }
        Assertions.assertThrows(StorageException.class, () -> storage.save(new Resume(UUID_1)));
    }

    @Test
    void getAll() {
        Resume[] expected = new Resume[3];
        expected[0] = new Resume(UUID_1);
        expected[1] = new Resume(UUID_2);
        expected[2] = new Resume(UUID_3);
        Assertions.assertArrayEquals(expected, storage.getAll());
        // monitoring
        monitoring();
    }

    @Test
    void update() {
        Resume r = new Resume(UUID_3);
        storage.update(r);
        Assertions.assertEquals(r, storage.get(UUID_3));
    }

    @Test
    void delete() {
        Resume[] expected = new Resume[2];
        expected[0] = new Resume(UUID_1);
        expected[1] = new Resume(UUID_2);
        storage.delete(UUID_3);
        Assertions.assertArrayEquals(expected, storage.getAll());
        // monitoring
        monitoring();
    }

    @Test
    void clear() {
        storage.clear();
        Assertions.assertArrayEquals(new Resume[2], storage.getAll());
    }

    void monitoring() {
        System.out.println("\nGet All");
        for (Resume r : storage.getAll()) {
            System.out.println(r);
        }
        System.out.println("Size: " + storage.size() + "\n");
    }

    void fillStorage() throws StorageException {
        for (int i = initSize; i < STORAGE_LIMIT; i++) {
            storage.save(new Resume());
        }
        System.out.println("size: " + storage.size());
    }
}