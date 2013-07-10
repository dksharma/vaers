package edu.mayo.vaccinedb.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SymptomsMapper implements RowMapper {

	public SymptomEntry mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		SymptomEntry symptom = new SymptomEntry();
		
		symptom.vax_id = rs.getLong("vaers_id");
		symptom.vax_type = rs.getString("vax_type");
		symptom.vax_manu = rs.getString("vax_manu");
		symptom.vax_dose = rs.getString("vax_dose");
		symptom.vax_route = rs.getString("vax_route");
		symptom.vax_site = rs.getString("vax_site");
		symptom.vax_name = rs.getString("vax_name");
		symptom.sym1 = rs.getString("symptom1");
		symptom.sym2 = rs.getString("symptom2");
		symptom.sym3 = rs.getString("symptom3");
		symptom.sym4 = rs.getString("symptom4");
		symptom.sym5 = rs.getString("symptom5");
		symptom.sex = rs.getString("sex");
		symptom.disable = rs.getString("disable");
		symptom.died = rs.getString("died");
		symptom.dateDied = rs.getString("datedied");
		symptom.ageYrs = rs.getString("age_yrs");
		symptom.hospDays = rs.getString("hospdays");
		symptom.L_Threat = rs.getString("l_threat");
		symptom.numDays = rs.getString("numdays");
		symptom.X_Stay = rs.getString("x_stay");
		symptom.vaccineLot = rs.getString("vax_lot");
		symptom.recvDate = rs.getString("recvdate");
		symptom.onsetDate = rs.getString("onset_date");
		symptom.reportDate = rs.getString("rpt_date");
		symptom.note = rs.getString("symptom_text");
		
		return symptom;
	}
}
