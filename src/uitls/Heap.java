package uitls;

import java.util.Scanner;

import model.Cos;

public class Heap {
	Cos heap[];//={0,13,38,27,49,76,65,49};//0ºÅÎ»Îª»º´æÎ»
	public void setCos(Cos heap[]){
		this.heap=heap;
	}
	public void swap(Cos a,Cos b){
		double value=a.getValue();
		int id=a.getId();		
		a.setId(b.getId());
		a.setValue(b.getValue());		
		b.setId(id);
		b.setValue(value);
	}
	public void heapAdjust(int s,int m){
		heap[0]=heap[s];
		for(int i=2*s;i<=m;i*=2){
			//System.out.println(i);
			if(i<m&&less(heap[i].getValue(),heap[i+1].getValue())) i++;
			if(!less(heap[0].getValue(),heap[i].getValue())) break;
			heap[s]=heap[i];	
			s=i;	
		}
		heap[s]=heap[0];
	}
	public int[] topK(int k){
		int []r=new int[k];
		int len=heap.length-1;
		for(int i=0;i<k;i++){
			/*double value=heap[len-i].getValue();
			int id=heap[len-i].getId();
			//Cos temp=heap[len-i];		
			heap[len-i].setId(heap[1].getId());
			heap[len-i].setValue(heap[1].getValue());		
			//heap[len-i]=heap[1];		
			heap[1].setId(id);
			heap[1].setValue(value);*/
			swap(heap[1],heap[len-i]);
			
			heapAdjust(1, len-i-2);			
		}
		travel();
		for(int i=0;i<k;i++){
			r[i]=heap[len-k+i+1].getId();
		}
		return r;
		
	}
	public void heapSort(){
		createHeap();
	}
	public void  createHeap(){
		for(int i=heap.length/2;i>0;i--){
			heapAdjust(i,heap.length-1);
		}
	}
	public boolean great(double a,double b){
		if(a>=b) return true;
		else return false;
	}
	public boolean less(double a,double b){
		if(a<=b) return true;
		else return false;
	}
	public void travel(){
		for(int i=1;i<heap.length;i++){
			System.out.println(heap[i].getValue()+" "+heap[i].getId());
		}
	}
}
