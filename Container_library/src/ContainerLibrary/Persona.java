package ContainerLibrary;

import static java.lang.Math.*;


public class Persona implements Comparable<Persona> {

    private String name;
    private String surname;
    private int age;
    private double height;

    public Persona () {
        this("", "", 0, 0);
    }

    public Persona (String name, String surname, int age, double height) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public boolean equals (Object var1) {
        if(var1 != null && getClass() == var1.getClass()) {
            Persona supp = (Persona) var1;
            return this.name.equals(supp.name) && this.surname.equals(supp.surname) && age == supp.age && height == supp.height;
        } else return false;
    }

    public int hashCode () {
        int HASH_MULTIPLIER = 31;
        return (int) (pow(HASH_MULTIPLIER, 3.0) * (new Double(height)).hashCode() + pow(HASH_MULTIPLIER, 2.0) * (new Integer(age)).hashCode() + HASH_MULTIPLIER * surname.hashCode() + name.hashCode());
    }

    public String toString () {
        StringBuilder res = new StringBuilder();
        res.append("Nome: " + this.name + ", Cognome: " + this.surname + ", Età: " + this.age + ", Altezza: " + this.height);
        return res.toString();
    }

    @Override
    public int compareTo(Persona persona) {
        return (abs(this.height - persona.height) < 10E-14) ? 0 : (this.height > persona.height) ? 1 : -1;
    }
}
