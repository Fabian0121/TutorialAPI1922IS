package com.example.apptutorial.Pokemon;

import java.util.List;

public class Pokemon {
    private List<Abilities> abilities;
    private String height;
    private int id;
    private String name;
    private Species species;
    private List<Stats> stats;
    private List<Types> types;
    private String weight;

    public Pokemon(List<Abilities> abilities, String height, int id, String name, Species species, List<Stats> stats, List<Types> types, String weight) {
        this.abilities = abilities;
        this.height = height;
        this.id = id;
        this.name = name;
        this.species = species;
        this.stats = stats;
        this.types = types;
        this.weight = weight;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    public List<Types> getTypes() {
        return types;
    }

    public void setTypes(List<Types> types) {
        this.types = types;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
