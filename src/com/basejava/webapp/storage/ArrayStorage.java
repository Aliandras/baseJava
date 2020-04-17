package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based com.basejava.webapp.storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    // clear используем Arrays.fill для заполнения null массива Resume[] com.basejava.webapp.storage где
    // 1парам массив 2 парам начало, 3 парам конец, 4 чем заполняется(val)
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    //проверка на существование
    // апдейт
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Resume " + resume.getUuid() + " not exist");
        } else {
            storage[index] = resume;
        }
    }
    // проверки на существование и переполнение
    // сохраняем и увеличиваем size
    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Resume " + resume.getUuid() + " already exist");
        } else if (size >= storage.length) {
            System.out.println("Storage overflow");
        } else {
            storage[size] = resume;
            size++;
        }
    }
    //если индекс не существует(-1) message после возвращаем com.basejava.webapp.storage индекс
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    // задаем перемееную index равную индексу по uuid
    // если индекс = -1(не существут) вывод message
    // в противном случае ...
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            storage[index] = storage[size() - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in com.basejava.webapp.storage (without null)
     */
    // создаем новый массив resumes где длинна массива значение size
    // после копируем из массива com.basejava.webapp.storage c 0 позиции в массив resumes c 0 позиции, указзаное количество элементов
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        System.arraycopy(storage, 0, resumes, 0, size);
        return resumes;
    }

    // метод size() возвращает значение size
    public int size() {
        return size;
    }

    // получаем индекс проходим массив и если uuid эквивалентно uuid в ячейкее массива то возвращаем индекс(порядковый номер)
    // если нет то -1 (не существует)
    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}