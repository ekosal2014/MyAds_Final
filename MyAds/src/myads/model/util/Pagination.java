package myads.model.util;

public class Pagination {
	public static int startpage=0;
	public static int currentpage=1;
	public static int rowperpage=5;
	public static int totalpage=0;
	public static String category="";
	public static String subcategory="";
	public static void countPage(int totalrow){
		if (totalrow % rowperpage == 0){
			totalpage=totalrow/rowperpage;
		}else{
			totalpage=(totalrow/rowperpage)+1;
		}
	}
	
	
}
