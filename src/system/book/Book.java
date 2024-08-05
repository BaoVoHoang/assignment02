package system.book;

import system.user.User;
import system.user.UserPlan;

public class Book {
    private String name;
    private String author;
    private String language;
    private String genre;
    private int published;
    private float millionSales;

    public Book(String name, String author, String language, int published, float millionSales, String genre) {
        this.name = name;
        this.author = author;
        this.language = language;
        this.genre = genre;
        this.published = published;
        this.millionSales = millionSales;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public String getGenre() {
        return genre;
    }

    public int getPublished() {
        return published;
    }

    public float getMillionSales() {
        return millionSales;
    }

    public boolean read(User user) {
        return user.isActive();
    }

    public boolean download(User user) {
        return user.isActive() && user.getPlan().getPlanType() == UserPlan.PlanType.VIP;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Author: " + author + ", Language: " + language +
                ", Published: " + published + ", Million Sales: " + millionSales + ", Genre: " + genre;
    }
}
