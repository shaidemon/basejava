package ru.daemon75.basejava;

import ru.daemon75.basejava.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume resume = new Resume("test_resume");
        Method method = resume.getClass().getMethod("toString");
        System.out.println(method.invoke(resume));

    }
}