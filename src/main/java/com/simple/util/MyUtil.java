package com.simple.util;

import java.util.ArrayList;
import java.util.List;

public class MyUtil {

	public static void main(String [] a){
		System.out.println(getRandomChar());
	}
	
	/**
	 * 这个方法是把1,2,5,3,2转成list<Integer>集合
	 * 因为mybatis查范围,要用的迭代
  	 */
	public static List<Integer> Str_ids_To_ListInteger_ids(String ids){
		List<Integer> ListInteger_ids = new ArrayList<Integer>();
		String[] arr = ids.split(",");
		for(String i:arr){
			ListInteger_ids.add(Integer.parseInt(i));
		}
		return ListInteger_ids;
	}

	
	public static String getFenYe(Integer curr_page, Integer totalNum, Integer rows,String projectContext){
		StringBuffer pageCode=new StringBuffer();
		//如果刚好被除尽  页数等于当前  否则多一页
		long totalPage = totalNum % rows == 0 ? totalNum / rows : totalNum / rows + 1;
		if(curr_page>1){
			pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(curr_page-1)+"'>上一页</a></li>");			
		}else{
			//如果不大于1就让上一页不可用
			pageCode.append("<li class='disabled'><a>上一页</a></li>");		
		}
		for(int i=curr_page-2;i<=curr_page+2;i++){
			if(i<1||i>totalPage){
				continue;
			}
			if(i==curr_page){
				pageCode.append("<li class='active'><a>"+i+"</a></li>");	
			}else{
				pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+i+"'>"+i+"</a></li>");	
			}
		}
		//操作总页数  把下一页不可用
		if(curr_page<totalPage){
			pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(curr_page+1)+"'>下一页</a></li>");		
		}else{
			pageCode.append("<li class='disabled'><a>下一页</a></li>");	
		}
		return pageCode.toString();
	}
	
	
	public static String getRandomChar() {
		int rand = (int) Math.round(Math.random() * 2);
		long itmp = 0;
		char ctmp = '\u0000';
		switch (rand) {
		case 1:
			itmp = Math.round(Math.random() * 25 + 65);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
		case 2:
			itmp = Math.round(Math.random() * 25 + 97);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
		default:
			itmp = Math.round(Math.random() * 9);
			return String.valueOf(itmp);
		}
	}
	
	
}
