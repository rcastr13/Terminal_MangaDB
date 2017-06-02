package MangaList;

import java.util.Date;

public class MangaListObjectPropertiesVer3 extends MangaListSystemVer3{
	private int id;
	private String tag;
	private String mangaName;
	private String mangakaName;
	private String genre;
	private int yearReleased;
	private String readingStatus;
	private String currentDate;
	
	public MangaListObjectPropertiesVer3(){
	}
	
	public MangaListObjectPropertiesVer3(int id, String tag, String mangaName, String mangakaName, String genre, int yearReleased, String readingStatus, String currentDate){
		this.id = id;
		this.tag = tag.toUpperCase();
		this.mangaName = mangaName;
		this.mangakaName = mangakaName;
		this.genre = genre;
		this.yearReleased = yearReleased;
		this.readingStatus = readingStatus;
		this.currentDate = currentDate;
	}
	
	public int getID(){
		return id;
	}
	
	public String getTag(){
		return tag;
	}
	
	public String getMangaName(){
		return mangaName;
	}
	
	public String getMangakaName(){
		return mangakaName;
	}
	
	public String getGenre(){
		return genre;
	}
	
	public int getYearReleased(){
		return yearReleased;
	}
	
	public String getReadingStatus(){
		return readingStatus;
	}
	
	public String getCurrentDate(){
		return currentDate;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public void setTeg(String tag){
		this.tag = tag;
	}
	
	public void setMangaName(String mangaName){
		this.mangaName = mangaName;
	}
	
	public void setMangakaName(String mangakaName){
		this.mangakaName = mangakaName;
	}
	
	public void setGenre(String genre){
		this.genre = genre;
	}
	
	public void setYearReleased(int yearRelease){
		yearReleased = yearRelease;
	}
	
	public void setReadingStatus(String readingStatus){
	  this.readingStatus = readingStatus;
	}
	
	public void setCurrentDate(){
		currentDate = new Date().toString();
	}

	public String toString(){
		String mangaInfo = "\nID: " + getID() + 
			"\nTag(s): " + getTag() + 
			"\nManga: " + getMangaName() + 
			"\nAuthor(s): " + getMangakaName() + 
			"\nGenre(s): " + getGenre() + 
			"\nYear released: " + getYearReleased() + 
			"\nReading status: " + getReadingStatus() + 
			"\nRevised: " + getCurrentDate();
		
		return mangaInfo;
	}

	public String writeToFile(){
		String mangaInfoWrite = getID() + "\t" + getTag() + "\t" + 
		getMangaName() + "\t" + getMangakaName() + "\t" + 
		getGenre() + "\t" + getYearReleased() + "\t" + 
		getReadingStatus() + "\t" + getCurrentDate();

		return mangaInfoWrite;
	}
}
