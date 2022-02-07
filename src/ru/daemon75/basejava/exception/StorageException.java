package ru.daemon75.basejava.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
        System.out.println(message);
    }

    public String getUuid() {
        return uuid;
    }
}
