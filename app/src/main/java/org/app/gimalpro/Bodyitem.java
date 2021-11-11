package org.app.gimalpro;

public class Bodyitem {
    private int NUMBER;
    private String ID;
    private Double Height;
    private Double Weight;
    private Double Muscle;

    public int getNUMBER() {
        return NUMBER;
    }

    public void setNUMBER(int NUMBER) {
        this.NUMBER = NUMBER;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getHeight() {
        return Height;
    }

    public void setHeight(Double height) {
        Height = height;
    }

    public Double getWeight() {
        return Weight;
    }

    public void setWeight(Double weight) {
        Weight = weight;
    }

    public Double getMuscle() {
        return Muscle;
    }

    public void setMuscle(Double muscle) {
        Muscle = muscle;
    }

    public Double getFat() {
        return Fat;
    }

    public void setFat(Double fat) {
        Fat = fat;
    }

    private Double Fat;

    public Bodyitem() {
    }


}
