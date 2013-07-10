package edu.mayo.vaccinedb.main;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

public class JDBCSymptomsDAO implements SymptomsDAO, InitializingBean 
{
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public void setDataSource(DataSource ds)
	{
		this.dataSource = ds;
		this.jdbcTemplate = new JdbcTemplate(this.dataSource);
	}
	
	public void afterPropertiesSet() throws Exception
	{
		if (dataSource == null)
		{
			throw new BeanCreationException("Must set dataSource on SymptomsDAO");
		}
	}

	@SuppressWarnings("unchecked")
	public List<SymptomEntry> getSelectedNotes() 
	{
		String query = "select vc.*, sy.`SYMPTOM1`, sy.`SYMPTOM2`, sy.`SYMPTOM3`, sy.`SYMPTOM4`, sy.`SYMPTOM5`, vd.`SYMPTOM_TEXT`, vd.`RECVDATE`, vd.`ONSET_DATE`, " +
						"vd.`SEX`, vd.`DISABLE`, vd.died, vd.`DATEDIED`, vd.`AGE_YRS`, vd.`HOSPDAYS`, vd.`L_THREAT`, vd.`NUMDAYS`, vd.`RPT_DATE`, " +
						"vd.`X_STAY` " +
						"from vaccine vc, symptoms sy, vaersdata vd " +
						"where " + 
						"(vc.VAX_TYPE = 'ROTHB5' " +
						"or vc.`VAX_TYPE` = 'RV') AND " +
						"(sy.`SYMPTOM1` like '%intussusception%' or " +
						"sy.`SYMPTOM2` like '%intussusception%' or " +
						"sy.`SYMPTOM3` like '%intussusception%' or " +
						"sy.`SYMPTOM4` like '%intussusception%' or " +
						"sy.`SYMPTOM5` like '%intussusception%') AND " +
						"vc.`VAERS_ID` = sy.`VAERS_ID` AND " +
						"vd.`VAERS_ID` = vc.`VAERS_ID` " +
						"order by vd.`RECVDATE` ";
		
		return jdbcTemplate.query(query, new SymptomsMapper());
	}

	public String getVaccineNameFromType(String vaccineType) {
		
		String query = "select distinct vax_name from vaccine where vax_type = ?";
		
		String vaccineName = (String) jdbcTemplate.queryForObject(
				query, new Object[]{vaccineType}, String.class);
		
		return vaccineName;
	}
}
