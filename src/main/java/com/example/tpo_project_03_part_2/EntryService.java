package com.example.tpo_project_03_part_2;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class EntryService {
    private final EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public void addEntry(Entry entry) {
        entryRepository.save(entry);
    }

    public List<Entry> getAllEntries() {
        return (List<Entry>) entryRepository.findAll();}

    public List<Entry> searchEntries(Long id) {
        return (List<Entry>) entryRepository.findAllById(Collections.singleton(id));
    }

    public void deleteEntry(Long id) {
        entryRepository.deleteById(id);
    }

    public void updateEntry(Long id, String polish, String german, String english) {
        Entry current = entryRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("Entry with ID " + id + " not found"));
        current.setPolish(polish);
        current.setGerman(german);
        current.setEnglish(english);
        entryRepository.save(current);
    }

    public List<Entry> sortEntries(String language, String order) {
        List<Entry> entries = (List<Entry>) entryRepository.findAll();

        boolean ascending = order.equalsIgnoreCase("asc");

        Comparator<Entry> comparator = null;

        if (language.equalsIgnoreCase("polish")) {
            comparator = Comparator.comparing(e -> e.getPolish().toLowerCase());
        } else if (language.equalsIgnoreCase("german")) {
            comparator = Comparator.comparing(e -> e.getGerman().toLowerCase());
        } else if (language.equalsIgnoreCase("english")) {
            comparator = Comparator.comparing(e -> e.getEnglish().toLowerCase());
        } else {
            throw new IllegalArgumentException("Invalid language: " + language);
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        entries.sort(comparator);

        return entries;
    }

}
