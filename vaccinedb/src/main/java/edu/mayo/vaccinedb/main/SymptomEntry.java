package edu.mayo.vaccinedb.main;

public class SymptomEntry {
	public long vax_id;
	public String vax_type;
	public String vax_manu;
	public String vax_dose;
	public String vax_route;
	public String vax_site;
	public String vax_name;
	public String sym1;
	public String sym2;
	public String sym3;
	public String sym4;
	public String sym5;
	public String sex;
	public String disable;
	public String died;
	public String dateDied;
	public String ageYrs;
	public String hospDays;
	public String L_Threat;
	public String numDays;
	public String X_Stay;
	public String vaccineLot;
	public String recvDate;
	public String reportDate;
	public String onsetDate;
	public String note;
	
	public String toString()
	{
		return "\n************************************************\nVAX_ID=" + vax_id +
				"\nVAX_TYPE=" + vax_type + 
				"\nVAX_NAME=" + vax_name +
				"\nONSET DATE=" + onsetDate +
				"\nREPORT DATE=" + reportDate +
				"\n(VAX_LOT=" + vaccineLot + ")," + "(VAX_DOSE=" + vax_dose + ")," + "(VAX_ROUTE=" + vax_route + ")," + "(VAX_SITE=" + vax_site + ")," +
				"\n(SEX=" + sex + ")," + "(DISABLE=" + disable + ")," + "(DIED=" + died + ")," + "(DATE DIED=" + dateDied + ")," + "(AGE YRS=" + ageYrs + ")," +
				"\n(HOSPDAYS=" + hospDays + ")," + "(L_THREAT=" + L_Threat + ")," + "(X_STAY=" + X_Stay + ")," + "(NUM_DAYS=" + numDays + ")," +
				"\n(SYMPTOM1=" + sym1 + ")," + "(SYMPTOM2=" + sym2 + ")," +
				"\n(SYMPTOM3=" + sym3 + ")," + "(SYMPTOM4=" + sym4 + ")," +
				"\n(SYMPTOM5=" + sym5 + ")," + 
				"\nNOTE=[" + note + "]";
	}
}
