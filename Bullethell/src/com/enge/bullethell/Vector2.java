package com.enge.bullethell;

/**
 * This class handles the vector system used by several components in the game.
 * @version 29.04.2013
 */
public class Vector2 {
	//Initial vectors
    public static final Vector2 Zero = new Vector2(0, 0);
    public static final Vector2 One = new Vector2(1, 1);

    //Vectors that are set once and once only
    public final float x;
    public final float y;

    /**
     * Initializes the vector.
     * @param x The x-vector.
     * @param y The y-vector.
     */
    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Initializes the vector from another vector.
     * @param v The vector to get the information from.
     */
    public Vector2(Vector2 v) {
        this.x = v.x;
        this.y = v.y;
    }

    /**
     * Multiplies the vector by a scalar.
     * @param scalar The scale to multiply by
     * @return The new vector
     */
    public Vector2 mul(float scalar) {
        return new Vector2(x * scalar, y * scalar);
    }

    /**
     * Adds two vectors together.
     * @param v The vector to add
     * @return The added vectors
     */
    public Vector2 add(Vector2 v) {
        return new Vector2(x + v.x, y + v.y);
    }

    /**
     * Performs addition via floats.
     * @param x The x-direction float
     * @param y The y-direction float
     * @return The final vector
     */
    public Vector2 add(float x, float y) {
        return new Vector2(this.x + x, this.y + y);
    }

    /**
     * Subtracts one vector from another
     * @param v The vector to subtract
     * @return The final vectors
     */
    public Vector2 sub(Vector2 v) {
        return new Vector2(x - v.x, y - v.y);
    }

    /**
     * Performs subtraction via floats.
     * @param x The x-direction float
     * @param y The y-direction float
     * @return The final vector
     */
    public Vector2 sub(float x, float y) {
        return new Vector2(this.x - x, this.y - y);
    }

    /**
     * Gets the diagonal of the vector.
     * @return The length.
     */
    public float len() {
        return (float)Math.sqrt(x * x + y * y);
    }

    /**
     * Returns a vector over the length of the vector.
     * @return The final vector.
     */
    public Vector2 unit() {
        float len = len();
        if (len != 0) {
            return new Vector2(x / len, y / len);
        } else {
            return this;
        }
    }

    /**
     * Returns the minimum of two vectors.
     * @param v1 The first vector.
     * @param v2 The second vector.
     * @return The minimum of both values of both vectors.
     */
    public static Vector2 min(Vector2 v1, Vector2 v2) {
        return new Vector2(Math.min(v1.x, v2.x), Math.min(v1.y, v2.y));
    }

    /**
     * Returns the maximum of two vectors.
     * @param v1 The first vector.
     * @param v2 The second vector.
     * @return The maximum of both values of both vectors.
     */
    public static Vector2 max(Vector2 v1, Vector2 v2) {
        return new Vector2(Math.max(v1.x, v2.x), Math.max(v1.y, v2.y));
    }
}
