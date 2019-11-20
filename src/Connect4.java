import java.util.Scanner;

class connect4 {
    public static void main(String[] args) {
        boolean end_game = false;
        Scanner input_reader = new Scanner(System.in);

        System.out.println("Welcome to connect 4.");
        while (!end_game) {
            boolean players_set = false;
            boolean versus_ai = false;
            while (!players_set) {
                System.out.println("Enter number of human players(1-2):");
                int number_of_players = input_reader.nextInt();
                if (0 < number_of_players && number_of_players < 3) {
                    players_set = true;
                    versus_ai = number_of_players == 1;
                } else {
                    System.out.println("Number not recognized. Must be between 1 or 2.");
                }
            }

            GameHandler game = new GameHandler(versus_ai);
            while (game.isNotOver()) {
                try {
                    System.out.println(game.toString());
                    game.makeMove(input_reader);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            try {
                System.out.println(game.toString());
                String winner = game.getWinner();
                System.out.println(winner);
            } catch (Exception e) {
                e.printStackTrace();
            }

            boolean play_again = false;
            while (!play_again) {
                System.out.println("Start a new round?(y/n):");
                String answer = input_reader.next();
                if (answer.equalsIgnoreCase("y")) {
                    play_again = true;
                } else if (answer.equalsIgnoreCase("n")) {
                    play_again = true;
                    end_game = true;
                } else {
                    System.out.println("Input not recognized. Try again.");
                }
            }

        }
    }
}
