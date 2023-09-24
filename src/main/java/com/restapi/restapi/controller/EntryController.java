package com.restapi.restapi.controller;

import com.restapi.restapi.entity.EntryEntity;
import com.restapi.restapi.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/load")
public class EntryController {
    @Autowired
    private EntryService entryService;

    // Create a new Entry
    @PostMapping
    public String createEntry(@RequestBody EntryEntity entry) {
        EntryEntity entryDetails = entryService.createEntry(entry);
        return "Loads Details added successfully";
    }

    // Get Shipper Entries
    @GetMapping
    public List<EntryEntity> getAllEntryByShipperId(@RequestParam(value = "shipperId") Long id) {
        return entryService.getAllEntryByShipperId(id);
    }

    // Get Load Entry
    @GetMapping("/{loadId}")
    public Optional<EntryEntity> getEntryById(@PathVariable Long loadId) {
        return entryService.getEntryById(loadId);
    }

    // Update Entry
    @PutMapping("/{loadId}")
    public EntryEntity updateEntry(@PathVariable Long loadId, @RequestBody EntryEntity entryDetails) {
        return entryService.updateEntry(loadId, entryDetails);
    }


    // Delete Entry
    @DeleteMapping("/{loadId}")
    public void deleteEntry(@PathVariable Long loadId) {
        entryService.deleteEntry(loadId);
    }
}