import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SmartSquare extends GameSquare
{

	private boolean thisSquareHasBomb = false;
	public static final int MINE_PROBABILITY = 10;
	private int bombNum;
	private boolean hasBeenClicked = false;

	public SmartSquare(int x, int y, GameBoard board)
	{
		super(x, y, "images/blank.png", board);

		Random r = new Random();
		thisSquareHasBomb = (r.nextInt(MINE_PROBABILITY) == 0);


	}

	public void clicked()
	{
		
		if(hasBeenClicked == false){
			
			if(thisSquareHasBomb == true)
			{

				this.setImage("images/bomb.png");

			}

			else{

				recurCheck();

			}
		}
	}		 
	
	public void recurCheck()
	{

		SmartSquare a;

		for(int i = -1; i < 2 ;i++)
		{

			for(int j = -1; j < 2 ;j++)
			{

				a = (SmartSquare)board.getSquareAt(xLocation + i, yLocation + j);

				if(a != null )
				{

					if(a.hasBomb() == true)
					{

						bombNum++;
						hasBeenClicked = true;

					}
				}
			}
		}
		
		this.setImage("images/" + bombNum + ".png");
		for(int i = -1; i < 2 ;i++)
		{

			for(int j = -1; j < 2 ;j++)
			{

				a = (SmartSquare)board.getSquareAt(xLocation + i, yLocation + j);
			
				if(a != null )
				{
				
					if(this.getBombNum() == 0  && a.getHasBeenClicked() == false)
					{
						
					a.setHasBeenClicked();
					a.recurCheck();
					
					}
				}
			}	
		
		}
	}

	public boolean hasBomb()
	{

		return thisSquareHasBomb;

	}


	public int getBombNum()
	{

		return bombNum;

	}
	

	public boolean getHasBeenClicked(){

		return hasBeenClicked;

	}

	public void setHasBeenClicked(){

		hasBeenClicked = true;

	}


}


