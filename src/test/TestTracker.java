package test;

import entities.Tracker;
import service.TrackerService;

public class TestTracker {
	
	public static void main(String[] args) {
		TrackerService ts = new TrackerService();
		ts.create(new Tracker("1452"));
		ts.create(new Tracker("7452"));
		ts.create(new Tracker("5452"));
		
		//ts.delete(ts.findById(1));
		Tracker tt = ts.findById(2);
		tt.setSimNumber("00000000");
		ts.update(tt);
		for(Tracker t : ts.findAll()) {
			System.out.println(t);
		}
	}

}
