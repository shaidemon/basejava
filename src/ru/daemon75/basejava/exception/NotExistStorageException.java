package ru.daemon75.basejava.exception;

public class NotExistStorageException extends StorageException {
    public NotExistStorageException(String uuid) {
        super("Resume %s is not present on storage.".formatted(uuid), uuid);
    }
}
