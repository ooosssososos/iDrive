package com.example.model.untitled4;

import com.example.service.untitled4.DrinkOrDriveWebService;

public class TestClass {
	
	public static void main(String[] args) {
		DrinkOrDriveWebService d = new DrinkOrDriveWebService();
		d.parseBarUsers();
		d.parsePartyUsers();
		d.parseParty();
		d.parsePromotion();
		for(BarUser b : d.getBarUsers()) {
			System.out.println(b.toString());
			
		};
		
		for(PartyUser p : d.getPartyUsers()) {
			System.out.println(p.toString());
			
		};
		
		for(Party p : d.getParties()) {
			System.out.println(p.toString());
			
		};
		
		for(Promotion p : d.getPromos()) {
			System.out.println(p.toString());
			
		};
	}
	
	

}
