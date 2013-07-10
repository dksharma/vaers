package edu.mayo.vaccinedb.main;

import java.util.List;

public interface SymptomsDAO {

	public String getVaccineNameFromType(String vaccineType);
	public List<SymptomEntry> getSelectedNotes();
}
