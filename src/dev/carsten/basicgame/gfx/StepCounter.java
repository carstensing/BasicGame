package dev.carsten.basicgame.gfx;

public class StepCounter {

    private int previousX, previousY;
    private int changeInX, changeInY;
    private int stepDistance, iteratorSize;
    private int index;

    public StepCounter(int startX, int startY, int stepDistance, int iteratorSize) {
        this.previousX = startX;
        this.previousY = startY;
        this.stepDistance = stepDistance;
        this.iteratorSize = iteratorSize;
        this.index = 0;
        this.changeInX = 0;
        this.changeInY = 0;
    }

    //Potential bug here is a -position is thrown into calculation.
    public void tick(int currentX, int currentY)
    {
        changeInX += Math.abs(this.previousX - currentX);
        changeInY += Math.abs(this.previousY - currentY);

        if(changeInX / stepDistance >= 1 || changeInY / stepDistance >= 1) {
            changeInX = 0;
            changeInY = 0;

            index++;
            if(index > iteratorSize-1) {
                index = 0;
            }
        }

        this.previousX = currentX;
        this.previousY = currentY;
    }

    public int getIndex() {
        return this.index;
    }
}
