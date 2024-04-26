import java.util.Scanner;

public class BullyAlgorithm {
    static boolean[] state = new boolean[5];
    static int coordinator = 5;

    public static void up(int up) {
        if (state[up - 1]) {
            System.out.println("Process " + up + " is already up.");
        } else {
            state[up - 1] = true;
            System.out.println("Process " + up + " is up.");

            // If the newly up process has a higher ID than the current coordinator,
            // initiate an election
            if (up > coordinator) {
                System.out.println("Process " + up + " initiates an election.");
                election(up);
            }
        }
    }
    public static void down(int down) {
        if (!state[down - 1]) {
            System.out.println("Process " + down + " is already down.");
        } else {
            state[down - 1] = false;
            System.out.println("Process " + down + " is down.");
            if (down == coordinator) {
                System.out.println("Coordinator (Process " + down + ") is down.");
                election(down);
            }
        }
    }

    public static void mess(int mess) {
        if (!state[mess - 1]) {
            System.out.println("Process " + mess + " is down.");
        } else {
            if (mess == coordinator) {
                System.out.println("Coordinator (Process " + coordinator + ") received the message: OK");
            } else {
                System.out.println("Process " + mess + " sends a message.");
            }
        }
    }

    public static void election(int initiator) {
        System.out.println("Election initiated by Process " + initiator);
        for (int i = 5; i >= 1; i--) {
            if (state[i - 1]) {
                System.out.println("Election message sent from Process " + initiator + " to Process " + i);
                coordinator = i;
                break;
            }
        }
        // If no higher priority process responds, declare the initiator as the new
        // coordinator

        System.out.println("Process " + coordinator + " becomes the new coordinator.");
        System.out.println("Coordinator message sent from Process " + coordinator + " to all.");
    }

    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; ++i) {
            state[i] = true;
        }
        System.out.println("5 active processes are:");
        System.out.println("Process up  = p1 p2 p3 p4 p5");
        System.out.println("Process 5 is coordinator");
        do {
            System.out.println(".........");
            System.out.println("1. Up a process.");
            System.out.println("2. Down a process.");
            System.out.println("3. Send a message.");
            System.out.println("4. Exit.");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("Bring process up:");
                    int up = sc.nextInt();
                    if (up > 5) {
                        System.out.println("Invalid process number.");
                        break;
                    }
                    up(up);
                    break;
                }
                case 2: {
                    System.out.println("Bring down any process:");
                    int down = sc.nextInt();
                    if (down > 5) {
                        System.out.println("Invalid process number.");
                        break;
                    }
                    down(down);
                    break;
                }
                case 3: {
                    System.out.println("Which process will send message:");
                    int mess = sc.nextInt();
                    if (mess > 5) {
                        System.out.println("Invalid process number.");
                        break;
                    }
                    mess(mess);
                    break;
                }
            }
        } while (choice != 4);
    }
}