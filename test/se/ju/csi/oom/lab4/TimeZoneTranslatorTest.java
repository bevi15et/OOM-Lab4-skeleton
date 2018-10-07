package se.ju.csi.oom.lab4;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class TimeZoneTranslatorTest {
	DateTime testObject;
	DateTime testObject2;
	DateTime bugTestObject;
	
	Person guy;
	Person gal;
	
	Place jonkoping;
	
	Event testEvent;
	
	@Before
	public void setUp() throws Exception {
		//Test
		testObject = new DateTime(2018, 11, 11, 11, 11, 11);
		testObject2 = new DateTime(2018, 11, 11, 11, 22, 11);

		guy = new Person("Victor");
		gal = new Person("Kim");
		
		jonkoping = new Place("Jonkoping",57.777961,14.154818,20.0);
		
		testEvent = new Event("Something happens", testObject, testObject2, new HashSet<>(Arrays.asList(gal, guy)), jonkoping);
		
		//Bug fix test
		bugTestObject = new DateTime(2016, 01, 01, 06, 00, 00);

	}

	@Test
	public void testShiftTimeZone() {
		assertEquals("2018-11-11 16:11:11", TimeZoneTranslator.shiftTimeZone(testObject, -1, 4).toString());	

	}

	@Test
	public void testShiftEventTimeZone() {
		assertEquals("2018-11-11 15:11:11", TimeZoneTranslator.shiftEventTimeZone(testEvent, TimeZone.CENTRAL_EUROPEAN_TIME, TimeZone.PAKISTAN).getStartDate().toString());
		assertEquals("2018-11-11 15:22:11", TimeZoneTranslator.shiftEventTimeZone(testEvent, TimeZone.CENTRAL_EUROPEAN_TIME, TimeZone.PAKISTAN).getEndDate().toString());

	}
	
	@Test
	public void testShiftTimeZone2() {
		assertEquals("2015-12-31 21:00:00", TimeZoneTranslator.shiftTimeZone(bugTestObject, 1, -8).toString());
		
	}
	
	@Test
	public void testShiftTimeZone3() {
		assertEquals("2016-01-01 00:00:00", TimeZoneTranslator.shiftTimeZone(bugTestObject, 1, -5).toString());
		
	}
	
}
