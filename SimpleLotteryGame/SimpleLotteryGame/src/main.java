import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		String playagain = "Y";
	    String name1 = "Default";
	    int score =0;
		do {
			int number;
			  System.out.println("Welcome to the Lottery Game");
			  System.out.println(" ");
			  System.out.println(" ");
		      Scanner scan = new Scanner(System.in);

		      System.out.print("Please enter n: ");

		      do {
		         number = scan.nextInt();

		         if (number < 7 || number > 10) {
		            System.out.println("Please enter n: ");
		         }
		      } while (number < 7 || number > 10);
		      
		      
		      Stack S1 = new Stack(number);
		      Stack S2 = new Stack(number);
		      

		      Game lottery = new Game();	  
		      
		      S1 = lottery.createStacks(S1);
		      S2 = lottery.createStacks(S2);
		      
		      score = lottery.gamePlaying(S1,S2);
		      
		      
		      
			  System.out.println(" ");
		      Scanner scan2 = new Scanner(System.in);
		      System.out.print("What is your name: ");
		      name1 = scan2.nextLine();
			  System.out.println(" ");

		      
		      System.out.println("High Score Table");

		      //***ÖNEMLİ AÇIKLAMA: HİGH SCORE KISMINDA OVERFLOW, Empty, null gibi hatalar bulunmaktadır. 
		      //Tamamlanmadığı halde ne azından sort kısmı çalıştığı için eklenmiştir.
		      //HİGH SCORE
		      //HİGH SCORE
		      //HİGH SCORE

		      Queue q1=new Queue(1000);
		      Queue q1temp = new Queue(1000);
		      Queue q2=new Queue(1000);
		      Queue q2temp = new Queue(1000);
		      
		      try {
		            FileReader fReader = new FileReader("C:\\Users\\MCB\\Desktop\\highscoretable.txt");
		            BufferedReader bReader = new BufferedReader(fReader);
		            String line;
		            
		            while ((line = bReader.readLine()) != null) {
		                q1.enqueue(line.split(" ")[0]) ;
		                q2.enqueue(Integer.parseInt(line.split(" ")[1])) ;
		            	}
		        	}
		        	catch (IOException e){
		        		System.out.println("");
		        	}
		      q1.enqueue(name1);
		      q2.enqueue(score);
		      
		      sortQueues(q1,q2);
		      
		      try (BufferedWriter BufWri = new BufferedWriter(new FileWriter("C:\\Users\\MCB\\Desktop\\HighScoreTable.txt"))) {
					
		        	for(int k = 0;k<12;k++) {
		        		BufWri.write(q1.peek()+ " " + q2.peek());
						BufWri.newLine();
						q2temp.enqueue(q2.dequeue());
						q1temp.enqueue(q1.dequeue());
		        	}
		        	for(int j = 0 ;j< 12;j++) {
		    			if(q2temp.peek()==null)
		    				break;
		    			q2.enqueue(q2temp.dequeue());
		    			if(q1temp.peek()==null)
		    				break;
		    			q1.enqueue(q1temp.dequeue());
		    		}

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        

	
		      //HİGH SCORE
		      //HİGH SCORE
		      //HİGH SCORE

		      Scanner scan3 = new Scanner(System.in);

			  System.out.println(" ");
			  System.out.print("Play Again? Y/N: ");
		      playagain = scan3.nextLine();
		  
		      System.out.println(" ");

		} while(playagain.equalsIgnoreCase("Y"));
	
	}

	
	public static void sortQueues(Queue Q1, Queue Q2) {
		Queue temp1Queue = new Queue(1000);
		Queue temp2Queue = new Queue(1000);
		
	    List<Integer> numbers = new ArrayList<Integer>();

	    // Q2'deki sayıları numbers listesine ekle
	    while (!Q2.isEmpty()) {
	        int num = (int) Q2.dequeue();
	        numbers.add(num);
	    }

	    // Numbers listesini büyükten küçüğe sırala
	    Collections.sort(numbers, Collections.reverseOrder());

	    // Q1'deki elemanları numbers listesine göre yeniden düzenle
	    Queue tempQueue = new Queue(Q1.size());
	    while (!Q1.isEmpty()) {
	        String str = (String) Q1.dequeue();
	        int num = numbers.get(0);
	        numbers.remove(0);
	        tempQueue.enqueue(str);
	        Q2.enqueue(num);
	    }
	    Q1 = tempQueue;

	    // Q1 ve Q2 queue'lerini yazdır
	    System.out.print("Q1: ");
	    while (!Q1.isEmpty()) {
	    	String temp1 = (String) Q1.dequeue();
	    	temp1Queue.enqueue(temp1);
	        System.out.print(temp1 + " ");
	    }
	    System.out.println();
	    System.out.print("Q2: ");
	    while (!Q2.isEmpty()) {
	    	int temp2 = (int) Q2.dequeue();
	    	temp2Queue.enqueue(temp2);
	        System.out.print(temp2 + " ");
	    }
	    
	    while(!temp1Queue.isEmpty()) {
	    	Q1.enqueue(temp1Queue.dequeue());
	    }
	    while(!temp2Queue.isEmpty()) {
	    	Q2.enqueue(temp2Queue.dequeue());
	    }
	    
	    System.out.println();
	}

	
	
}

