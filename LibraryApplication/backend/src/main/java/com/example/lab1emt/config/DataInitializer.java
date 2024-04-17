package com.example.lab1emt.config;

import com.example.lab1emt.models.Category;
import com.example.lab1emt.service.AuthorService;
import com.example.lab1emt.service.BookService;
import com.example.lab1emt.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CountryService countryService;

    public DataInitializer(AuthorService authorService, BookService bookService, CountryService countryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.countryService = countryService;
    }

    private Category randomizePosition(int i) {
        if(i % 7 == 0) return Category.NOVEL;
        else if(i % 7 == 1) return Category.THRILER;
        else if(i % 7 == 2) return Category.HISTORY;
        else if(i % 7 == 3) return Category.FANTASY;
        else if(i % 7 == 4) return Category.BIOGRAPHY;
        else if(i % 7 == 5) return Category.CLASSICS;
        return Category.DRAMA;
    }

    // region Data
    private final String[] bookTitles = {
            "Meditations",
            "Atomic Habits",
            "Subtitle of not giving a f",
            "Pride and Prejudice",
            "The Catcher in the Rye",
            "The Hobbit",
            "Fahrenheit 451",
            "Moby-Dick",
            "Brave New World",
            "The Lord of the Rings"
    };

    private final String[] authorFirstNames = {
            "Marcus",
            "George",
            "Donnie",
            "Jane",
            "Michael.",
            "Stefan.",
            "Ray",
            "Herman",
            "Aldous",
            "John"
    };

    private final String[] authorLastNames = {
            "Aurelius",
            "Orwell",
            "Fitzgerald",
            "Austen",
            "Salinger",
            "Tolkien",
            "Bradbury",
            "Melville",
            "Huxley",
            "Tolkien"
    };

    private final String[] authorCountries = {
            "Greece",
            "Macedonia",
            "United States",
            "Japan",
            "United States",
            "Canada",
            "Japan",
            "United States",
            "United Kingdom",
            "China"
    };

    private final String[] authorContinents = {
            "Europe",
            "Europe",
            "North America",
            "Asia",
            "North America",
            "North America",
            "Asia",
            "North America",
            "Europe",
            "Asia"
    };

    @PostConstruct
    public void initData() {
        for (int i = 1; i < authorCountries.length; i++) {
            this.countryService.create(authorCountries[i], authorContinents[i]);
        }

        for (int i = 1; i < authorFirstNames.length; i++) {
            this.authorService.create(authorFirstNames[i], authorLastNames[i], this.countryService.listAll().get((i-1)%5).getId());
        }

        for (int i = 1; i < bookTitles.length; i++) {
            this.bookService.create(bookTitles[i], this.randomizePosition(i) , this.authorService.listAll().get((i-1)%10).getId(), i);
        }
    }
}
