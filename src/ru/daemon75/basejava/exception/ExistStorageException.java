package ru.daemon75.basejava.exception;

public class ExistStorageException extends StorageException {

    public ExistStorageException(String uuid) {
        super("Resume %s already present in storage.".formatted(uuid), uuid);
    }
}
