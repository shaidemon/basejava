package ru.daemon75.basejava.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.daemon75.basejava.exception.NotExistStorageException;
import ru.daemon75.basejava.model.Resume;

import java.beans.ExceptionListener;

public abstract class AbstractArrayStorageTest {
    protected Storage storage; //= new SortedArrayStorage();
    public static final String UUID_1 = "uuid1";
    public static final String UUID_2 = "uuid2";
    public static final String UUID_3 = "uuid3";

    public AbstractArrayStorageTest(Storage storage) {

        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(new Resume(UUID_1));
        storage.save(new Resume(UUID_2));
        storage.save(new Resume(UUID_3));

    }

    @Test
    void size() throws Exception{
        Assertions.assertEquals(3, storage.size());
    }

    @Test
    void save() {
    }

    @Test
    void get() {
    }

    @Test()
    public void getNotExist() {
        NotExistStorageException thrown = Assertions.assertThrows(NotExistStorageException.class, () -> {
            storage.get("dummy");
        });
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void clear() {
    }
}