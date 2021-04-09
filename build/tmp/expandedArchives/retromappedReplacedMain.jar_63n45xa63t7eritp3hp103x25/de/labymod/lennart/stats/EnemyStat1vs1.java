package de.labymod.lennart.stats;

public class EnemyStat1vs1 {

    String name;
    String value;

    public EnemyStat1vs1(String name, String value) {

        this.name = name;
        this.value = value;

    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getFull() {
        return name + ": " + value;
    }

}
