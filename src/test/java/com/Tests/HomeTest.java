package com.Tests;

import java.util.Arrays;

import org.testng.annotations.Test;

public class HomeTest {
	@Test
	public void homeTest() {
		int[]nput= {4,0,1,0,3,0,12,6,0};
        int[]copy=new int[nput.length];
        int count=1;
        int cop=0;
        for(int i=0;i<nput.length;i++){
            if(nput[i]==0){
                copy[nput.length-count]=nput[i];               
                count++;
            }else{
                copy[cop]=nput[i];
                cop++;
            }
        }
        for(int c:copy){
            System.out.print(c+" ");
        }
	}
}
