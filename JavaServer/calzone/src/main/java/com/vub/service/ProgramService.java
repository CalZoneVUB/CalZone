package com.vub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vub.model.Program;
import com.vub.repository.ProgramRepository;

/**
 * 
 * @author Nicolas
 *
 */
@Service("programService")
public class ProgramService {
	@Autowired
	ProgramRepository programRepository;
	
	/**
	 * Create (persist) a program in the database
	 * @param program
	 */
	@Transactional
	public Program createProgram(Program program) {
		return programRepository.save(program);
	}
	
	/**
	 * Update (persist) a program in the database
	 * @param program
	 */
	@Transactional
	public Program updateProgram(Program program) {
		return programRepository.save(program);
	}
	

	/**
	 * Find a Program object in the database.
	 * @param id	The ID of the Program which needs to be fetched
	 * @return	A Program object fetched from the database
	 */
	@Transactional
	public Program findProgramById(int id) {
		return programRepository.findOne(id);
	}

	/**
	 * Delete a Program object from the database
	 * @param program	The Program object one wishes to delete
	 */
	@Transactional
	public void deleteProgram(Program program) {
		programRepository.delete(program);
	}
	
	/**
	 * List all of the programs currently present in the database	
	 * @return	List of Program objects in the database
	 */
	@Transactional
	public List<Program> getPrograms() {
		return programRepository.findAll();
	}
}
