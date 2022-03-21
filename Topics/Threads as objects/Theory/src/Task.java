// You can experiment here, it won’t be checked

public class Task {
  public static void main(String[] args) {
    // put your code here
    Thread t = Thread.currentThread(); // main thread


    t.setName("my-thread");
    t.setPriority(5);
    System.out.println("name: " + t.getName());
    System.out.println("priority: " + t.getPriority());
  }
  }

