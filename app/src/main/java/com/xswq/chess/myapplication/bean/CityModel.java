package com.xswq.chess.myapplication.bean;

import java.util.List;

public class CityModel {

	private String name;

	private List<City> city;
	public CityModel() {

	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<City> getCity() {
		return city;
	}

	public void setCity(List<City> city) {
		this.city = city;
	}

	public static class City{


		public String name;
		public Area area;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Area getArea() {
			return area;
		}

		public void setArea(Area area) {
			this.area = area;
		}

		public City() {

		}

		public static class Area{


			public String name;

			public String getName() {
				return name;
			}

			public void setName(String name) {
				this.name = name;
			}
		}

	}


}
