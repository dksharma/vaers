package edu.mayo.vaccinedb.main;

public class SymptomUtils 
{
	public static boolean isNull(String str)
	{
		return ((str == null)||("".equalsIgnoreCase(str.trim()))||("null".equalsIgnoreCase(str.trim())));
	}
}
