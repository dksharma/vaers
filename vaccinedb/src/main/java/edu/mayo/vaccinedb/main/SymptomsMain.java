package edu.mayo.vaccinedb.main;

import java.util.Date;
import java.text.ParsePosition;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.mayo.informatics.cntro.model.Event;
import edu.mayo.informatics.cntro.model.Time;
import edu.mayo.informatics.cntro.model.TimeAssemblyMethod;
import edu.mayo.informatics.cntro.model.TimeInstant;
import edu.mayo.informatics.cntro.queryIF.Granularity;


public class SymptomsMain 
{
	public DateParser dp = new DateParser();
	
	public static void main(String[] args) 
	{
		ApplicationContext ctx = new ClassPathXmlApplicationContext("vaccinedb.xml");
		
		SymptomsDAO  sdao = (SymptomsDAO) ctx.getBean("symptomsDao");
		String vname = sdao.getVaccineNameFromType("RV");
		System.out.println("Vaccine Name:" + vname);
		
		List<SymptomEntry> symTexts = sdao.getSelectedNotes();
		System.out.println("Records found=" + symTexts.size());
		
		SymptomsMain main = new SymptomsMain();
		
		for (SymptomEntry entry : symTexts)
		{
			if (SymptomUtils.isNull(entry.note))
				continue;
			
			List<Event> events = main.getEventsFromEntry(entry);
			
			System.out.println("Text=" + entry.note);
			System.out.println(events);
		}
	}
	
	public List<Event> getEventsFromEntry(SymptomEntry entry)
	{
		String[] delimiters = {",", ";", "."};
		
		List<Event> allEvents = new ArrayList<Event>();
		
		for (String delim : delimiters)
		{
			allEvents = createEventUsingDelimiters(entry.note, delim);
			
			if ((allEvents == null) || (allEvents.isEmpty()))
					continue;
			else
				break;
		}
		
		try
		{
			if ((allEvents == null) || (allEvents.isEmpty()))
			{
				Event evt = new Event(entry.note, entry.note, null, null, null);
				allEvents.add(evt);
			}

			for (Event e : allEvents)
			{
				TimeInstant ti = new TimeInstant();
				ti.label = entry.onsetDate;
				ti.setNormalizedTime(dp.parse(entry.onsetDate, new ParsePosition(0)));
				ti.granularity = Granularity.DAY;
				ti.assemblyMethod = TimeAssemblyMethod.ASSERTED;
				e.noteTime = ti;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return allEvents;
	}
	
	public List<Event> createEventUsingDelimiters(String note, String delim)
	{
		List<Event> events = new ArrayList<Event>();
		
		String[] parts = note.split(delim);
		
		for (String part : parts)
		{
			List<Time> times = getTimesFromText(part);
			
			if ((times != null)&&(!times.isEmpty()))
			{
				Event evt = new Event(part, part, times.get(0), times.get(0), null);
				events.add(evt);
			}
		}
		
		return events;
	}
	
	public List<Time> getTimesFromText(String text)
	{
		List<Time> allTimes = new ArrayList<Time>();
		
		if (SymptomUtils.isNull(text))
			return null;
		
		text = text.replaceAll("(may|May)(\\s)*(be|have)", "");
		
		String[] tokens = text.split(" ");
		for (String token : tokens)
		{
			if (SymptomUtils.isNull(token))
				continue;
			
			Date dt = dp.parse(token, new ParsePosition(0));
			
			if (dt == null)
				continue;
			
			TimeInstant ti = new TimeInstant();
			ti.label = text;
			ti.setNormalizedTime(dt);
			ti.granularity = Granularity.DAY;
			ti.assemblyMethod = TimeAssemblyMethod.ASSERTED;
			allTimes.add(ti);
		}
		
		return allTimes;
	}
}
