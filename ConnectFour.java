
import java.util.Scanner;
public class ConnectFour {
	
	final static int row = 6;
	final static int column = 7;
	static char[][] board;
	final static char emptyToken = '_';
	
	public static void main(String args[]) {
	newBoard();
	int playerCounter = 1;
	char token;
	Scanner input = new Scanner(System.in);

	
	System.out.print("Player 1, please choose a token:");
	char p1 = input.next().charAt(0);
	while(p1 == emptyToken)
	{
		System.out.println("Please choose another token.");
		p1 = input.next().charAt(0);
	}
	
	System.out.print("Player 2, please choose a token:");
	char p2 = input.next().charAt(0);
	while(p2 == emptyToken || p2 == p1)
	{
		System.out.println("Please choose another token.");
		p2 = input.next().charAt(0);
	}
	
		while(true)
		{
			showBoard();
		
			if(playerCounter % 2 != 0)
			{
				System.out.println("Your turn Player 1, choose a column:");
				token = p1;
			}
			else
			{
				System.out.println("Your turn Player 2, choose a column:");
				token = p2;
			}
			int col = input.nextInt() - 1;
			do {
				if(col >= 0 && col <= board.length)
				{
					if(board[0][col] != emptyToken)
					{
						System.out.println("Column is full.");
						showBoard();
					}
					else
					{
						break;
					}
				}
				System.out.println("Try again.");
				System.out.println("Choose a column.");
				col = input.nextInt() - 1;
			} while(true);
					
			int availableRow = tokenDrop(col,token);
			if(checkWin(availableRow, col, token))
            {
                showBoard();
                if (playerCounter % 2 != 0)
                {
                    System.out.println("Congrats Player 1 you are the winner!");
                }
                else
                {
                    System.out.println("Congrats Player 2 you are the winner!");
                }
                System.out.println();
                break;
            }
			if(checkDraw())
			{
				System.out.println("This game ended in a draw.");
				showBoard();
				break;
			}
            playerCounter++;
		}
	}
	
	public static void showBoard()
	{
		for(int r = 0; r < board.length; r++)
		{
			for(int c = 0; c < board[r].length; c++)
			{
				System.out.print("| " + board[r][c] + " |");
			}
			System.out.println();
		}
	}
	
	public static void newBoard()
	{
		board = new char[row][column];
		for(int r = 0; r < row; r++)
		{
			for(int c = 0; c < column; c++)
			{
				board[r][c] = emptyToken;
			}
		}
	}
	
	public static int tokenDrop(int column,char currentPlayer)
	{	
		for(int row = board.length - 1; row >= 0 ; row--)
		{
			if(board[row][column] == emptyToken)
			{
				board[row][column] = currentPlayer;
				return row;
			}
		}	
		return -1;
	}
	
	public static boolean checkWin(int row, int col,char token) {
		
		boolean result = false;

        // Check one side first and maintain a count of matching tokens, If no
        // match, then move towards the opposite direction
        //
        // Check left and right sides (left side first)
        int count = 1;
        for (int c = col-1; c >= 0; --c)
        {
            // We found a matching token
            if (board[row][c] == token)
            {
                count++; 
            }
            // If we do not find a matching token, then search the other side
            else
            {
                break;
            }
        } 
        for (int c = col+1; c < board[row].length; ++c)
        {
            if (board[row][c] == token)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        // We had at least a match of 4
        if (count >= 4)
        {
            return true;
        }
        
        // Search vertically
        count = 1;
        for (int r = row-1; r >= 0; --r) // searching up
        {
            // We found a matching token
            if (board[r][col] == token)
            {
                count++; 
            }
            // If we do not find a matching token, then search the other side
            else
            {
                break;
            }
        } 

        for (int r = row+1; r < board.length; ++r) // searching down
        {
            if (board[r][col] == token)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        // We had at least a match of 4
        if (count >= 4)
        {
            return true;
        }
        
        count = 1;
        //Search diagonally top left
        for(int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--)
        {
        	if(board[r][c] == token)
        	{
        		count++;
        	}
        	else
        	{
        		break;
        	}
        }
        for(int r = row + 1, c = col + 1; r < board.length  && c < board[row].length; r++, c++)
        {
        	if(board[r][c] == token) 
        	{
        		count++;
        	}
        	else
        	{
        		break;
        	}
        }
        // We had at least a match of 4
        if (count >= 4)
        {
            return true;
        }
        count = 1;
        //search diagonally top right
        for(int r = row + 1, c = col - 1; r < board.length && c >= 0; r++, c--)
        {
        	if(board[r][c] == token)
        	{
        		count++;
        	}
        	else
        	{
        		break;
        	}
        }
        for(int r = row - 1, c = col + 1; r >= 0  && c < board[row].length; r--, c++)
        {
        	if(board[r][c] == token) 
        	{
        		count++;
        	}
        	else
        	{
        		break;
        	}
        }
        // We had at least a match of 4
        if (count >= 4)
        {
            return true;
        }
       
		
		return result;
	}

	public static boolean checkDraw()
	{

		int rowCount = 0;
		for(int c = 0; c < board[rowCount].length; c++)
		{
			if(board[rowCount][c] == emptyToken)
			{
				return false;
			}
		}
		return true;
	}
}
