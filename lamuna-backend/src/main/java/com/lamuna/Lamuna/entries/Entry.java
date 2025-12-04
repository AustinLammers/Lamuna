package com.lamuna.Lamuna.entries;

import java.time.LocalDate;

public abstract class Entry {
    private String name;
    private String description;
    private int calories;
    private LocalDate createDate;

    public Entry(String name, String description, int calories) {
        this.name = name;
        this.description = description;
        this.calories = calories;
    }

    public Entry(String name, String description, int calories, LocalDate createDate) {
        this(name, description, calories);
        this.createDate = createDate;
    }

    public Entry() {
        name = "";
        description = "";
        calories = 0;
        createDate = LocalDate.now();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
    public int getCalories() {
        return calories;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }
}

