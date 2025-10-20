import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HighScoreTable {
    private  Queue Q1;
    private  Queue Q2;
    
    public HighScoreTable() {
		Q1 = new Queue(100);
		Q2 = new Queue(100);
	}

	public void highScoreTableRead() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\MCB\\Desktop\\highscoretable.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String name = parts[0];
                int score = Integer.parseInt(parts[1]);
                Q1.enqueue(name);
                Q2.enqueue(score);
            }
            reader.close();
            
            sortQueues();
            
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
        
        Queue tempQ1 = new Queue(100);
        Queue tempQ2 = new Queue(100);
        
        while(!Q1.isEmpty()) {
        	tempQ1.enqueue(Q1.dequeue());
        }
        
        while(!Q2.isEmpty()) {
        	tempQ2.enqueue(Q2.dequeue());
        }
        
        for(int m=0;m<tempQ1.size(); m++) {
        	String Q1value = (String) tempQ1.dequeue();
        	int Q2value = (int) tempQ2.dequeue();
        	
        	System.out.println(Q1value +"    " + Q2value);
        	Q1.enqueue(Q1value);
        	Q2.enqueue(Q2value);
        }
    }
    
    private void sortQueues() {
        Queue tempQ1 = new Queue(12);
        Queue tempQ2 = new Queue(12);
        int size = Q1.size();
        for (int i = 0; i < size; i++) {
            String highestName = "";
            int highestScore = -1;
            int index = 0;
            int currentSize = Q1.size();
            for (int j = 0; j < currentSize; j++) {
                String name = (String) Q1.dequeue();
                int score = (int) Q2.dequeue();
                if (score > highestScore) {
                    highestName = name;
                    highestScore = score;
                    index = j;
                }
                Q1.enqueue(name);
                Q2.enqueue(score);
            }
            for (int j = 0; j < index; j++) {
                String name = (String) Q1.dequeue();
                int score = (int) Q2.dequeue();
                Q1.enqueue(name);
                Q2.enqueue(score);
            }
            tempQ1.enqueue(highestName);
            tempQ2.enqueue(highestScore);
        }
        Q1 = tempQ1;
        Q2 = tempQ2;
    }
    
}
