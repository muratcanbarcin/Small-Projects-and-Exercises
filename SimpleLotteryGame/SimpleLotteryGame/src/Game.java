import java.util.Random;

public class Game {
	private  Queue Q3;
    
	public Game() {
		Q3 = new Queue(1000);
	}
	
	public Queue createQ3()
	{
	    String[] elements = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	    
	    for(int i =0; i<elements.length; i++) {
	    	//
	    	Q3.enqueue(elements[i]);
	    }
	    
	    return Q3;
	}
	
	
	public Stack createStacks(Stack S) {

		Random random = new Random();
	    String[] elements = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	    String[] temp = new String[13];
	    int index =0;
	    int randomIndex = random.nextInt(elements.length);
    	boolean flag = true;
    	
    	while(flag) {
    		flag = false;
    		for(int i=0; i<temp.length; i++) {
    			if(temp[i] != null && temp[i] == elements[randomIndex] ) {
    				flag= true;
    				break;
    			}
        	}
    		if(flag) {
    		    randomIndex = random.nextInt(elements.length);
    		}
    		else {
    			temp[index] = elements[randomIndex];
    			index++;
    			S.push(elements[randomIndex]);
    			if(!S.isFull()) {
    				flag = true;
    			}
    		}
    	}
    	return S;
	}

	
	public void gameWrite(Stack S1, Stack S2, Queue Q3, Queue Q4, int Score1, int Score2) {
		
	    Stack tempStack1 = new Stack(S1.size());
	    Stack tempStack2 = new Stack(S2.size());
	    
		System.out.print("Player1: ");
	    while(!S1.isEmpty()) {
	    	String temppop = (String) S1.pop();
	    	System.out.print(temppop + " ");
	    	tempStack1.push(temppop);
	    }
	    
	    while(!tempStack1.isEmpty()) {
	    	S1.push(tempStack1.pop());
	    }
	    
	    System.out.print("  Score:" + Score1 + "    ");
	    System.out.print("Bag1  ");
	    
	    for(int i =0; i<Q3.size(); i++) {
	    	String tempQValue = (String) Q3.dequeue();
	    	System.out.print(tempQValue + " ");
	    	Q3.enqueue(tempQValue);
	    }
	    
	    System.out.println(" ");
	    
		System.out.print("Player2: ");
		
	    while(!S2.isEmpty()) {
	    	String temppop = (String) S2.pop();
	    	System.out.print(temppop + " ");
	    	tempStack2.push(temppop);
	    }
	    
	    while(!tempStack2.isEmpty()) {
	    	S2.push(tempStack2.pop());
	    }
	    
	    System.out.print("  Score:" + Score2 + "    "); 
	    System.out.print("Bag2  ");
	    
	    for(int i =0; i<Q4.size(); i++) {
	    	String tempQValue1 = (String) Q4.dequeue();
	    	System.out.print(tempQValue1 + " ");
	    	Q4.enqueue(tempQValue1);
	    }
	    
	    System.out.println(" ");
	    System.out.println(" ");
	    System.out.println(" ");
	}
	
	public int gamePlaying(Stack S, Stack S2) {
	    Queue bag1 = new Queue(1000);
	    bag1 = createQ3();
	    Queue bag2 = new Queue(1000);
	    
	    int tombala_num1 =0 , tombala_num2=0;
	    int Score1 =0, Score2 =0, Tournament = 0;
	    gameWrite(S,S2, bag1,bag2,Score1,Score2);
	    int first =0;
	    
		while(!S.isEmpty() && !S2.isEmpty()) {
			Queue tempQueue = new Queue(100);
			Random random = new Random();
		    int randomIndex = random.nextInt(bag1.size());

		    for(int i=0; i< randomIndex; i++) {
		    	tempQueue.enqueue(bag1.dequeue());
		    }
		    String element = (String) bag1.dequeue();
		    bag2.enqueue(element);
		    
		    for(int i=0; i< randomIndex; i++) {
		    	bag1.enqueue(tempQueue.dequeue());
		    }
		    
		    Stack tempStack = new Stack(20);
		    
		    while(!S.isEmpty()) {
		    	String cont = (String) S.pop();	
		    	
		    	if(element.equalsIgnoreCase(cont) ) {
		    		while(!tempStack.isEmpty()) {
		    			S.push(tempStack.pop());
		    		}
		    		tombala_num1++;
	    			Score1 +=10;
		    		break;
		    	}
		    	else {
		    		tempStack.push(cont);
		    	}
		    	
		    	if(S.size() ==0) {
		    		while(!tempStack.isEmpty()) {
		    			S.push(tempStack.pop());
	
		    		}
	    			Score1-=5;
		    		break;
		    	}
		    }
		    
		    while(!S2.isEmpty()) {
		    	String cont = (String) S2.pop();	
		    	
		    	if(element.equalsIgnoreCase(cont) ) {
		    		while(!tempStack.isEmpty()) {
		    			S2.push(tempStack.pop());
		    	
		    		}
		    		tombala_num2++;
		    		Score2 +=10;
		    		break;
		    	}
		    	else {
		    		tempStack.push(cont);
		    	}
		    	
		    	if(S2.size() ==0) {
		    		while(!tempStack.isEmpty()) {
		    			S2.push(tempStack.pop());
		    
		    		}
					Score2 -=5;
		    		break;
		    	}
		    }

		
		    
		    if(tombala_num1 ==4 && tombala_num2 <4  && first ==0) {
		    	Score1 +=30;
		    	Tournament =1;
			    System.out.println(" ");
		    	first++;
		    }
		    else if(tombala_num2 == 4 && tombala_num1 <4  && first ==0) {
		    	Score2 +=30;
		    	Tournament =1;
			    System.out.println(" ");
		    	first++;
		    }
		    else if(tombala_num1 == 4 && tombala_num2 ==4 && first ==0) {
		    	Score1 +=15;
		    	Score2 +=15;
		    	Tournament =1;
		    	first++;
		    }
		    
		    gameWrite(S,S2, bag1,bag2,Score1,Score2);
		    
		 
		    if(Tournament == 1 && first == 1 ) {
		    	System.out.println("First tournament is completed");
			    System.out.println(" ");
			    first++;
		    }
		    
		    if(S.isEmpty() && !S2.isEmpty()) {
		    	Score1 +=50;
		    }
		    else if(S.isEmpty() && S2.isEmpty()) {
		    	Score1 +=25;
		    	Score2 +=50;
		    }
		    else if (S2.isEmpty() && !S.isEmpty()) {
		    	Score2 +=50;
		    }
		    
		}
		int Scoremax =0;
	    System.out.println(" ");
		System.out.println("Game over!");
	    System.out.println(" ");
	    if(Score1 > Score2) {
	    	System.out.println("Winner: Player1 with " + Score1 + " points");
	    	Scoremax = Score1;
	    }
	    else if(Score2>Score1) {
	    	System.out.println("Winner: Player2 with " + Score2 + " points");
	    	Scoremax = Score2;

	    }
	    else if(Score1 == Score2) {
	    	System.out.println("Winner: Player1 and Player2 (tie) with " + Score1 + " points");
	    	Scoremax = Score1;

	    }
	    return Scoremax;
	} 

}
