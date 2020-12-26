package com.launcher.dwo.Quest;

public class Quest {
    private String name;
    private String description;
    private String history;

    private Quest(){
        name = "";
        description = "";
        history = "";
    }

    public Quest(String name, String description, String history) {
        this.name = name;
        this.description = description;
        this.history = history;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
