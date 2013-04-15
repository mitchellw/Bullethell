package com.enge.bullethell.Components;
import com.artemis.Component;

/**
 * The score component.  Each entity will have a score.  This score is added to the total 
 * score every time the entity is destroyed.
 * 
 * @author SFenton
 * @version 15.04.2013
 */
public class Score_Component extends Component
{
	public int score;

	/**
	 * Empty constructor for the class.
	 */
	public Score_Component()
	{
		this.score = 0;
	}
	
	/**
	 * Sets the score for the component in the constructor.
	 * @param score The new score.
	 */
	public Score_Component(int score)
	{
		this.score = score;
	}
}
