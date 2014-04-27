package com.vub.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.model.Entry;
import com.vub.repository.EntryRepository;

/**
 * 
 * @author Nicolas
 *
 */
@Service("entryService")
public class EntryService {
	@Autowired
	EntryRepository entryRepository;
	
	/**
	 * Create (persist) a entry in the database
	 * @param entry
	 */
	@Transactional
	public Entry createEntry(Entry entry) {
		return entryRepository.save(entry);
	}
	
	/**
	 * Update (persist) a entry in the database
	 * @param entry
	 */
	@Transactional
	public Entry updateEntry(Entry entry) {
		return entryRepository.save(entry);
	}
	

	/**
	 * Find a Entry object in the database.
	 * @param id	The ID of the Entry which needs to be fetched
	 * @return	A Entry object fetched from the database
	 */
	@Transactional
	public Entry findEntryById(int id) {
		return entryRepository.findOne(id);
	}

	/**
	 * Delete a Entry object from the database
	 * @param entry	The Entry object one wishes to delete
	 */
	@Transactional
	public void deleteEntry(Entry entry) {
		entryRepository.delete(entry);
	}
	
	/**
	 * List all of the entries currently present in the database	
	 * @return	List of Entry objects in the database
	 */
	@Transactional
	public Set<Entry> getEntrys() {
		Set<Entry> result = new HashSet<Entry>();
		result.addAll(entryRepository.findAll());
		return result;
	}
}
